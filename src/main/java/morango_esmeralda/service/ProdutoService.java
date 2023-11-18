package morango_esmeralda.service;

import morango_esmeralda.domain.Produto;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

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

    public List<ProdutoResponseDTO> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtoResponseDTOSArrayList = new ArrayList<>();
        for (Produto produto : produtos) {

            ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(
                    produto.getIdProduto(), produto.getNome(), produto.getDescricao(),
                    produto.getQuant(), produto.getPreco());

            produtoResponseDTOSArrayList.add(produtoResponseDTO);
        }
        return produtoResponseDTOSArrayList;
    }

    public void deletar(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }
}


