package morango_esmeralda.controllers;

import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/carrinhos")
public class CarrinhoController {
    @Autowired
    CarrinhoService carrinhoService;


        @GetMapping("/mostrar-carrinho")
        public CarrinhoResponseDTO mostrarCarrinho(Principal principal) {
            return carrinhoService.mostrarCarrinho(principal);
        }
        @PostMapping("/adicionar-produto")
        public ResponseEntity<Void> adicionarProduto(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
            carrinhoService.adicionarProduto(carrinhoRequestDTO,principal);
            return ResponseEntity.noContent().build();
        }
        @PostMapping("/aumentar-quantidade")
        public ResponseEntity<Void> aumentarQuantidadeProduto(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
            carrinhoService.aumentarQuantidadeProduto(carrinhoRequestDTO,principal);
            return ResponseEntity.noContent().build();
        }
        @PostMapping("/diminuir-quantidade")
        public ResponseEntity<Void> diminuirQuantidadeProduto(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO, Principal principal) {
            carrinhoService.diminuirQuantidadeProduto(carrinhoRequestDTO,principal);
            return ResponseEntity.noContent().build();
        }
        @DeleteMapping("/apagar/{id}")
        public void deletarCarrinho(@PathVariable("id") Integer id) {
            carrinhoService.deletarCarrinho(id);
        }

    @DeleteMapping("/carrinho-produto/{idProduto}")
    public void deletarCarrinhoProdutoId(@PathVariable("idProduto") Integer idProduto) {
        carrinhoService.deletarCarrinhoproduto(idProduto);
    }
}
