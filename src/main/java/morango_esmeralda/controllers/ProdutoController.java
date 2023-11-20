package morango_esmeralda.controllers;

import morango_esmeralda.domain.Produto;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;


    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPeloId(@PathVariable("id") Integer idProduto) {
        return produtoService.buscarPeloId(idProduto);
    }

    @PostMapping("")
    public ProdutoResponseDTO salvar(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.salvar(produtoRequestDTO);
    }

    @PutMapping("/alterar/{id}")
    public ProdutoResponseDTO alterarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO,@PathVariable("id")Integer idProduto) {
        return produtoService.alterarProduto(produtoRequestDTO,idProduto);
    }

    @GetMapping(path = "/buscar-todos")
    public List<ProdutoResponseDTO> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable("id") Integer idProduto) {
        produtoService.deletar(idProduto);
    }

}
