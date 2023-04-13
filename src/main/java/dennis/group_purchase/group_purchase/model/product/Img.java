package dennis.group_purchase.group_purchase.model.product;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "img")
public class Img implements Serializable {
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "img_url")
    private String imgUrl;


    public Img() {
        // default constructor
    }
}
