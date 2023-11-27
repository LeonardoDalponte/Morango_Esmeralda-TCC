package morango_esmeralda.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarrinhoProdutoResponseDTO {
    private Integer idCarrinhoProduto;
    private Integer idProduto;
    private String nome;
    private Integer quantidade;
    private Double preco;

}
