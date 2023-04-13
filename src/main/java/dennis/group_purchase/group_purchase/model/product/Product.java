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
    private String productDescription;


    @ManyToMany
    @JoinTable(
            name = "product_img",
            joinColumns = @JoinColumn(name = "img_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Img> imgs = new HashSet<>();



    @ManyToMany
    @JoinTable(
            name = "product_var",
            joinColumns = @JoinColumn(name = "var_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Var> Vars = new HashSet<>();


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Set<Img> getImgs() {
        return imgs;
    }

    public void setImgs(Set<Img> imgs) {
        this.imgs = imgs;
    }

    public Set<Var> getVars() {
        return Vars;
    }

    public void setVars(Set<Var> vars) {
        Vars = vars;
    }
    public Product() {
        // default constructor
    }
}
