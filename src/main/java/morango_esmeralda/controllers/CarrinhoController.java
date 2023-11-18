package morango_esmeralda.controllers;

import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/carrinhos")
public class CarrinhoController {
    @Autowired
    CarrinhoService carrinhoService;


    @PostMapping("/adcionarProduto")
    public CarrinhoResponseDTO adcionarProduto(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {
        return carrinhoService.adcionarProduto(carrinhoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCarrinho(@PathVariable("id") Integer id) {
        carrinhoService.deletarCarrinho(id);
    }

}
