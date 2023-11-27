package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrinho_produto")
public class CarrinhoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho_produto")
    private Integer idCarrinhoProduto;

    @Column(name = "quantidade",nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;


}
