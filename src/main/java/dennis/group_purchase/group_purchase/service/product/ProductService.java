package dennis.group_purchase.group_purchase.service.product;

import dennis.group_purchase.group_purchase.model.product.Product;
import dennis.group_purchase.group_purchase.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private VarRepository varRepository;
    @Autowired
    private OptionRepository optionRepository;
   public Page<Product> allProductPaginate(int pageNum){
       Page<Product> page = productRepository.findAll(PageRequest.of(pageNum, 10));
       return null;
   }
//    public Page<Product> insertProduct(Product product){
//        Page<Product> page = productRepository.findAll(PageRequest.of(pageNum, 10));
//        return null;
//    }
   public Product save (Product product){
       return productRepository.save(product);


}


}
