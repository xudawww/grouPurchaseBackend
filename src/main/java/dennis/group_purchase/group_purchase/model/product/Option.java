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
    private String optionName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id")
    private Var var;
    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
    public Option() {
        // default constructor
    }

}
