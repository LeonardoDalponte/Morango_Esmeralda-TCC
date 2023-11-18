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
import morango_esmeralda.repository.CarrinhoProdutoRepository;
import morango_esmeralda.repository.CarrinhoRepository;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public CarrinhoResponseDTO salvar(CarrinhoRequestDTO carrinhoRequestDTO) {
//        Carrinho carrinhoParaSerSalvo = new Carrinho();
//        Usuario usuario = usuarioRepository.findById(carrinhoRequestDTO.getIdUsuario())
//                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
//        carrinhoParaSerSalvo.setUsuario(usuario);
//
//        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoParaSerSalvo);
//
//        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
//
//        usuarioResponseDTO.setIdUsuario(carrinhoSalvo.getUsuario().getIdUsuario());
//        usuarioResponseDTO.setNome(carrinhoSalvo.getUsuario().getNome());
//
//        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();
//
//        carrinhoResponseDTO.setUsuarioResponseDTO(usuarioResponseDTO);
//
//        return carrinhoResponseDTO;
//    }

    public CarrinhoResponseDTO adcionarProduto(CarrinhoRequestDTO carrinhoRequestDTO) {

        Usuario usuario = usuarioRepository.findById(carrinhoRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        Produto produto = produtoRepository.findById(carrinhoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());

        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();

        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setUsuario(usuario);

            Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

            CarrinhoProduto carrinhoProduto = new CarrinhoProduto();

            carrinhoProduto.setCarrinho(carrinhoSalvo);
            carrinhoProduto.setProduto(produto);

            CarrinhoProduto carrinhoProdutoSalvo = carrinhoProdutoRepository.save(carrinhoProduto);

            List<CarrinhoProdutoResponseDTO> produtoResponseDTOList = new ArrayList<>();
            CarrinhoProdutoResponseDTO produtoResponseDTO = CarrinhoProdutoResponseDTO.builder()
                    .idProduto(carrinhoProdutoSalvo.getProduto().getIdProduto())
                    .nome(carrinhoProdutoSalvo.getProduto().getNome())
                    .preco(carrinhoProdutoSalvo.getProduto().getPreco())
                    .build();

            produtoResponseDTOList.add(produtoResponseDTO);
            UsuarioResponseDTO usuarioResponseDTO = UsuarioResponseDTO.builder()
                    .idUsuario(usuario.getIdUsuario())
                    .nome(usuario.getNome())
                    .build();
            carrinhoResponseDTO.setUsuario(usuarioResponseDTO);

            carrinhoResponseDTO.setProdutos(produtoResponseDTOList);
        } else {
            CarrinhoProduto carrinhoProduto = new CarrinhoProduto();
            carrinhoProduto.setCarrinho(carrinho);
            carrinhoProduto.setProduto(produto);

            carrinhoProdutoRepository.save(carrinhoProduto);

            Carrinho carrinhoAtualizado = carrinhoRepository.findById(carrinho.getIdCarrinho()).orElseThrow();

            carrinhoAtualizado.setTotal(carrinhoAtualizado.getCarrinhoProduto().stream().mapToDouble(x -> x.getProduto().getPreco()).sum());
            Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoAtualizado);
            List<CarrinhoProdutoResponseDTO> produtoResponseDTOS = carrinhoSalvo.getCarrinhoProduto().stream().map(carrinhoProdutoMap ->
                    CarrinhoProdutoResponseDTO.builder()
                            .idProduto(carrinhoProdutoMap.getProduto().getIdProduto())
                            .nome(carrinhoProdutoMap.getProduto().getNome())
                            .preco(carrinhoProdutoMap.getProduto().getPreco())
                            .build()).toList();

            UsuarioResponseDTO usuarioResponseDTO = UsuarioResponseDTO.builder()
                    .idUsuario(usuario.getIdUsuario())
                    .nome(usuario.getNome())
                    .build();
            carrinhoResponseDTO.setUsuario(usuarioResponseDTO);

            carrinhoResponseDTO.setProdutos(produtoResponseDTOS);

            carrinhoResponseDTO.setTotal(carrinhoSalvo.getTotal());

        }
        return carrinhoResponseDTO;
    }


    public CarrinhoProduto criarCarrinho(Carrinho carrinho, Usuario usuario, Produto produto) {
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setUsuario(usuario);


            Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

            CarrinhoProduto carrinhoProduto = new CarrinhoProduto();
            carrinhoProduto.setCarrinho(carrinhoSalvo);
            carrinhoProduto.setProduto(produto);

            return carrinhoProdutoRepository.save(carrinhoProduto);

        } else {

            CarrinhoProduto carrinhoProduto = new CarrinhoProduto();

            carrinhoProduto.setCarrinho(carrinho);
            carrinhoProduto.setProduto(produto);

            return carrinhoProdutoRepository.save(carrinhoProduto);
        }
    }

    public void deletarCarrinho(Integer id) {
        carrinhoRepository.deleteById(id);
    }

    public void removerProdutoCarrinho(Integer idCarrinhoProduto) {
        carrinhoRepository.deleteById(idCarrinhoProduto);
    }
}

