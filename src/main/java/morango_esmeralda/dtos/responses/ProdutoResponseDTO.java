package morango_esmeralda.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Integer idProduto;

    private  String nome;

    private String descricao;

    private Integer quant;

    private Double preco;

    public ProdutoResponseDTO( String nome, String descricao, Integer quant, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.quant = quant;
        this.preco = preco;
    }
}
