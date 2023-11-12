package morango_esmeralda.service;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.domain.Produto;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void salvarImagem(MultipartFile imagem, Integer id) throws IOException {
        Path caminho = Path.of("C:\\Users\\Leo\\Desktop\\morango_esmeralda\\src\\imagens");
        Path caminhoImagem = Paths.get(caminho.toString(), imagem.getOriginalFilename());

        imagem.transferTo(caminhoImagem);

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não foi encontrado"));

        produto.setImagem(caminhoImagem.toString());
        produtoRepository.save(produto);
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoParaSerSalvo = new Produto();

        produtoParaSerSalvo.setNome(produtoRequestDTO.getNome());
        produtoParaSerSalvo.setDescricao(produtoRequestDTO.getDescricao());
        produtoParaSerSalvo.setQuant(produtoRequestDTO.getQuant());
        produtoParaSerSalvo.setPreco(produtoRequestDTO.getPreco());


        Produto produtoSalvo = produtoRepository.save(produtoParaSerSalvo);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();

        produtoResponseDTO.setIdProduto(produtoSalvo.getIdProduto());
        produtoResponseDTO.setNome(produtoSalvo.getNome());
        produtoResponseDTO.setDescricao(produtoSalvo.getDescricao());
        produtoResponseDTO.setQuant(produtoSalvo.getQuant());
        produtoResponseDTO.setPreco(produtoSalvo.getPreco());

        return produtoResponseDTO;
    }

    public ProdutoResponseDTO buscarPeloId(Integer id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setIdProduto(id);
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setDescricao(produto.getDescricao());
        produtoResponseDTO.setQuant(produto.getQuant());
        produtoResponseDTO.setPreco(produto.getPreco());

        return produtoResponseDTO;
    }

    public ProdutoResponseDTO alterar(ProdutoRequestDTO produtoRequestDTO, Integer idProduto) {
        Produto produtoParaSerAlterado = produtoRepository.findById(idProduto).get();

        produtoParaSerAlterado.setNome(produtoRequestDTO.getNome());
        produtoParaSerAlterado.setDescricao(produtoRequestDTO.getDescricao());
        produtoParaSerAlterado.setQuant(produtoRequestDTO.getQuant());
        produtoParaSerAlterado.setPreco(produtoRequestDTO.getPreco());
        Produto produtoAlterado = produtoRepository.save(produtoParaSerAlterado);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setIdProduto(produtoAlterado.getIdProduto());
        produtoResponseDTO.setNome(produtoAlterado.getNome());
        produtoResponseDTO.setDescricao(produtoAlterado.getDescricao());
        produtoResponseDTO.setQuant(produtoAlterado.getQuant());
        produtoResponseDTO.setPreco(produtoAlterado.getPreco());

        return produtoResponseDTO;
    }


    public List<Produto> buscarProdutos() {
        return produtoRepository.findAll();
    }


    public void deletarProduto(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }
}


