package morango_esmeralda.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;
import morango_esmeralda.domain.Produto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarrinhoResponseDTO {
   private UsuarioResponseDTO usuario;
   private List<CarrinhoProdutoResponseDTO> produtos;
   private CarrinhoProduto carrinhoProduto;
   private double Total;
}
