package morango_esmeralda.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoRequestDTO {

    private Integer idProduto;

    private Integer idCarrinho;

    private Integer idUsuario;
}
