package morango_esmeralda.controllers;

import ch.qos.logback.core.model.Model;
import morango_esmeralda.domain.Produto;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.repository.ProdutoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("api/produtos")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;


    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPeloId(@PathVariable("id") Integer idProduto) {
        return produtoService.buscarPeloId(idProduto);
    }


    @PostMapping("/salvar")
    public ProdutoResponseDTO salvar(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.salvar(produtoRequestDTO);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Void> salvarImagem(
            @PathVariable("id")  Integer id,
            @RequestParam("fileImage") MultipartFile imagem) throws IOException {
        produtoService.salvarImagem(imagem,id);
        return ResponseEntity.ok().build();
    }
}
