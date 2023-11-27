package morango_esmeralda.service;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;
import morango_esmeralda.domain.Produto;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoProdutoResponseDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.excepition.UsuarioException;
import morango_esmeralda.repository.CarrinhoProdutoRepository;
import morango_esmeralda.repository.CarrinhoRepository;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoProdutoRepository carrinhoProdutoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CarrinhoResponseDTO mostrarCarrinho(Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Email ja foi cadastrado"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());
        if (carrinho != null) {
            return preencherCarrinhoResponse(carrinho);
        }
        return null;
    }

    public void adicionarProduto(CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Email ja foi cadastrado"));

        Produto produto = produtoRepository.findById(carrinhoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto not found"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());

        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();
        carrinhoResponseDTO.setUsuario(mapearUsuarioParaDTO(usuario));

        if (carrinho == null) {
            carrinho = criarNovoCarrinho(usuario);

            adicionarProdutoAoCarrinho(carrinho, produto);
        } else {
            atualizarCarrinhoComProdutoExistente(carrinho, produto);
        }
    }

    private Carrinho criarNovoCarrinho(Usuario usuario) {
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        return carrinhoRepository.save(carrinho);
    }


    private void adicionarProdutoAoCarrinho(Carrinho carrinho, Produto produto) {
        CarrinhoProduto carrinhoProduto = new CarrinhoProduto();

        carrinhoProduto.setCarrinho(carrinho);
        carrinhoProduto.setProduto(produto);
        carrinhoProduto.setQuantidade(1);
        carrinhoProdutoRepository.save(carrinhoProduto);
    }

    private void atualizarCarrinhoComProdutoExistente(Carrinho carrinho, Produto produto) {
        CarrinhoProduto carrinhoProduto = carrinhoProdutoRepository.findByProdutoIdProduto(produto.getIdProduto());
        if (carrinhoProduto == null) {
            adicionarProdutoAoCarrinho(carrinho, produto);
        } else {
            carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + 1);
            carrinhoProdutoRepository.save(carrinhoProduto);
        }
    }

    public void aumentarQuantidadeProduto(CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Email ja foi cadastrado"));

        Produto produto = produtoRepository.findById(carrinhoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto not found"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());
        int quantidade = carrinhoRequestDTO.getQuantidade();

        validarQuantidade(quantidade);

        CarrinhoProduto carrinhoProduto = carrinhoProdutoRepository.findByCarrinhoAndProduto(carrinho, produto);
        carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + quantidade);
        carrinhoProdutoRepository.save(carrinhoProduto);
    }

    public void diminuirQuantidadeProduto(CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Email ja foi cadastrado"));

        Produto produto = produtoRepository.findById(carrinhoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());
        int quantidade = carrinhoRequestDTO.getQuantidade();

        validarQuantidade(quantidade);

        CarrinhoProduto carrinhoProduto = carrinhoProdutoRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (carrinhoProduto != null && carrinhoProduto.getQuantidade() > quantidade) {
            carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() - quantidade);
            carrinhoProdutoRepository.save(carrinhoProduto);
        } else if (carrinhoProduto != null && carrinhoProduto.getQuantidade() <= quantidade) {
            carrinhoProdutoRepository.delete(carrinhoProduto);
        }
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero!");
        }
    }

    private UsuarioResponseDTO mapearUsuarioParaDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nome(usuario.getNome())
                .build();
    }

    private CarrinhoResponseDTO preencherCarrinhoResponse(Carrinho carrinho) {

        double totalCarrinho = calcularTotalCarrinho(carrinho);

        carrinho.setTotal(totalCarrinho);

        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

        List<CarrinhoProdutoResponseDTO> produtoResponseDTOS = carrinhoSalvo.getCarrinhoProduto().stream()
                .map(this::mapearCarrinhoProdutoParaDTO)
                .toList();

        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();
        carrinhoResponseDTO.setUsuario(mapearUsuarioParaDTO(carrinhoSalvo.getUsuario()));
        carrinhoResponseDTO.setProdutos(produtoResponseDTOS);
        carrinhoResponseDTO.setTotal(carrinhoSalvo.getTotal());
        return carrinhoResponseDTO;
    }

    private CarrinhoProdutoResponseDTO mapearCarrinhoProdutoParaDTO(CarrinhoProduto carrinhoProduto) {
        return CarrinhoProdutoResponseDTO.builder()
                .idCarrinhoProduto(carrinhoProduto.getIdCarrinhoProduto())
                .idProduto(carrinhoProduto.getProduto().getIdProduto())
                .nome(carrinhoProduto.getProduto().getNome())
                .preco(carrinhoProduto.getProduto().getPreco())
                .quantidade(carrinhoProduto.getQuantidade())
                .build();
    }

    private double calcularTotalCarrinho(Carrinho carrinho) {
        return carrinho.getCarrinhoProduto().stream()
                .mapToDouble(carrinhoProduto -> carrinhoProduto.getProduto().getPreco() * carrinhoProduto.getQuantidade())
                .sum();
    }

    public void deletarCarrinho(Integer id) {
        carrinhoRepository.deleteById(id);
    }

    public void deletarCarrinhoproduto(Integer idProduto) {
        carrinhoProdutoRepository.deleteByProdutoIdProduto(idProduto);
    }
}

