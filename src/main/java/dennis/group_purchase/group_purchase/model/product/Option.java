package dennis.group_purchase.group_purchase.model.product;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "option")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_name")
    private String val;



    @Column(name = "option_price")
    private String price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id")
    private Var var;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVal() {
        return val;
    }

    public void setOptionName(String val) {
        this.val = val;
    }
    public Option() {
        // default constructor
    }
    public Option(Var var,String val, String price ) {
         this.var = var;
         this.val = val;
         this.price = price;
    }
}
