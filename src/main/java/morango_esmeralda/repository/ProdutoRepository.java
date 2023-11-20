package morango_esmeralda.repository;

import morango_esmeralda.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
   Optional <Produto> findById(Integer id);
}
