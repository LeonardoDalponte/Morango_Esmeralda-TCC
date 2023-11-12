package morango_esmeralda.controllers;

import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.requests.ProdutoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.dtos.responses.ProdutoResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.repository.CarrinhoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/carrinho")
public class CarrinhoController {
    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("")
    public CarrinhoResponseDTO salvar(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {
        return carrinhoService.salvar(carrinhoRequestDTO);
    }

    @PostMapping("/adcionarProduto")
    public CarrinhoResponseDTO adcionarProduto(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {
        return carrinhoService.adcionarProduto(carrinhoRequestDTO);
    }
}
