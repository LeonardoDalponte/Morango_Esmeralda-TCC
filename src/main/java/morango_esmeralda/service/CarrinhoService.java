package morango_esmeralda.service;

import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;
import morango_esmeralda.domain.Produto;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.repository.CarrinhoProdutoRepository;
import morango_esmeralda.repository.CarrinhoRepository;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CarrinhoResponseDTO salvar(CarrinhoRequestDTO carrinhoRequestDTO) {
        Carrinho carrinhoParaSerSalvo = new Carrinho();
        Usuario usuario = usuarioRepository.findById(carrinhoRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        carrinhoParaSerSalvo.setUsuario(usuario);

        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoParaSerSalvo);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(carrinhoSalvo.getUsuario().getIdUsuario());
        usuarioResponseDTO.setNome(carrinhoSalvo.getUsuario().getNome());

        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();

        carrinhoResponseDTO.setUsuarioResponseDTO(usuarioResponseDTO);

        return carrinhoResponseDTO;
    }

    public CarrinhoResponseDTO adcionarProduto(CarrinhoRequestDTO carrinhoRequestDTO) {
        Usuario usuario = usuarioRepository.findById(carrinhoRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        Produto produto = produtoRepository.findById(carrinhoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario());
        if (carrinho == null) {
            criarCarrinho(carrinho, usuario, produto);
        }
        return null;
    }

    public void criarCarrinho(Carrinho carrinho, Usuario usuario, Produto produto) {
        carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

        CarrinhoProduto carrinhoProduto = new CarrinhoProduto();
        carrinhoProduto.setCarrinho(carrinhoSalvo);
        carrinhoProduto.setProduto(produto);

        carrinhoProdutoRepository.save(carrinhoProduto);
    }
}

