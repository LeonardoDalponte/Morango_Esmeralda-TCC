package morango_esmeralda.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoRequestDTO {
    private List<Integer> idProdutos;
    private Integer idUsuario;
}
