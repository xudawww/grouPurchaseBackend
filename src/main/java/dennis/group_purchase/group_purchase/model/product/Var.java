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

    @Column(name = "var_answer_txt")
    private int varAnswerTxt;

    @OneToMany(mappedBy = "var", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> options = new HashSet<>();

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


    public int getVarAnswerTxt() {
        return varAnswerTxt;
    }

    public void setVarAnswerTxt(int varAnswerTxt) {
        this.varAnswerTxt = varAnswerTxt;
    }

    public Var() {
        // default constructor
    }
}
