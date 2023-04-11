package dennis.group_purchase.group_purchase.model.user.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class RegisterUser {
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() { return password; }
    @NotNull(message = "用户名不可以放空。")
    @Length(min = 4, max = 19, message = "用户名必须多于4位数，少于19位数。")
    String username;
    @NotBlank(message = "邮箱不可以放空。")
    @NotNull(message = "邮箱不可以放空。")
    @Email(message = "邮箱必须符合格式！")
    String email;

    @NotNull(message = "昵称不可以放空。")
    @Length(min = 5, max = 20, message = "昵称必须多于5位数，少于20位数。")
    String nickname;

    @NotNull(message = "密码不可以放空。")
    @Length(min = 5, max = 20, message = "密码必须d多于5位数，少于20位数。")
    String password;
}
