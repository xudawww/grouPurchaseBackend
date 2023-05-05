package dennis.group_purchase.group_purchase.model.product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category")
    private String productCategory;

    @Lob
    @Column(name = "product_description")
    private String productDesc;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Img> imgs = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Var> vars = new HashSet<>();



    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDescription) {
        this.productDesc = productDescription;
    }

    public Set<Img> getImgs() {
        return imgs;
    }

    public void setImgs(Set<Img> imgs) {
        this.imgs = imgs;
    }

    public Set<Var> getVars() {
        return vars;
    }

    public void setVars(Set<Var> vars) {
        vars = vars;
    }



    public Product() {
        // default constructor
    }
}
