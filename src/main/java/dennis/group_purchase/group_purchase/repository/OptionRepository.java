package dennis.group_purchase.group_purchase.repository;

import dennis.group_purchase.group_purchase.model.product.Option;
import dennis.group_purchase.group_purchase.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

}

