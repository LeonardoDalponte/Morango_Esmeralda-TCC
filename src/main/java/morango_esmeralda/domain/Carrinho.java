package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Integer idCarrinho;

    @Column(name = "rua", length = 245, nullable = true)
    private String rua;

    @Column(name = "bairro", length = 245, nullable = true)
    private String bairro;

    @Column(name = "cidade", length = 245, nullable = true)
    private String cidade;

    @Column(name = "CEP", length = 245, nullable = true)
    private String CEP;

    @Column(name = "total", length = 245, nullable = true)
    private Double total;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho")
    private List<CarrinhoProduto> carrinhoProduto;

}
