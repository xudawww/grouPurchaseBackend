package dennis.group_purchase.group_purchase.model.Jwt;

import java.io.Serializable;

public class JwtError implements Serializable {
    private String errorMsg;
    public JwtError(){}
    public  JwtError(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
