package dennis.group_purchase.group_purchase.model.product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "var")
public class Var implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "var_name")
    private String varName;

    @Column(name = "var_type")
    private int varType;

    @Column(name = "var_desc")
    private int varDesc;

    @OneToMany(mappedBy = "var", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> options = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public int getVarType() {
        return varType;
    }

    public void setVarType(int varType) {
        this.varType = varType;
    }


    public int getVarDesc() {
        return varDesc;
    }

    public void setVarAnswerTxt(int varAnswerTxt) {
        this.varDesc = varAnswerTxt;
    }

    public Var() {
        // default constructor
    }
    public Var(Product product,String varName,int varType, String varDesc ) {
        // default constructor
    }
}
