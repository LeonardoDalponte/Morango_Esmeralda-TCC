package morango_esmeralda.repository;

import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.CarrinhoProduto;
import morango_esmeralda.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, Integer> {
    

    CarrinhoProduto findByProdutoIdProduto(Integer idProduto);

    CarrinhoProduto findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);
    @Transactional
    void deleteByProdutoIdProduto(Integer idProduto);
}

