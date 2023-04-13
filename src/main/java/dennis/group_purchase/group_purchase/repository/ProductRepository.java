package dennis.group_purchase.group_purchase.repository;

import dennis.group_purchase.group_purchase.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findAll(Pageable pageable);
}
