package br.com.helton.estoque.repository;
import br.com.helton.estoque.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    
}
