package morango_esmeralda.repository;

import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, Integer> {

}

