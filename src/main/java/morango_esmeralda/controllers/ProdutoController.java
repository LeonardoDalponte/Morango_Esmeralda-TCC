package morango_esmeralda.controllers;

import morango_esmeralda.domain.Produto;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
}
