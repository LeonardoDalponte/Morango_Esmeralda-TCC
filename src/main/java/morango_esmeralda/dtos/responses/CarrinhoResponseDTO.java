package morango_esmeralda.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarrinhoResponseDTO {
   private UsuarioResponseDTO usuario;
   private CarrinhoProduto carrinhoProduto;
}
