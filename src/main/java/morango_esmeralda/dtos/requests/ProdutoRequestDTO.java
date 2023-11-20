package morango_esmeralda.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoRequestDTO {
    private Integer idProduto;
    private String nome;

    private String descricao;

    private Integer quant;

    private Double preco;

}
