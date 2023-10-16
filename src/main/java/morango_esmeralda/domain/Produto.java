package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "nome", nullable = false, length = 40)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "quant", nullable = false)
    private Integer quant;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @OneToMany(mappedBy = "produto")
    private List<CarrinhoProduto> carrinhoProduto;


}

