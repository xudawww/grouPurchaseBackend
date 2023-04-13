package dennis.group_purchase.group_purchase.service.product;

import dennis.group_purchase.group_purchase.model.product.Product;
import dennis.group_purchase.group_purchase.repository.ProductRepository;
import dennis.group_purchase.group_purchase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

   public Page<Product> allProductFirst(int pageNum){
       Page<Product> page = productRepository.findAll(PageRequest.of(pageNum, 10));
       return page;
   }


}
