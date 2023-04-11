package dennis.group_purchase.group_purchase.model.Jwt;

import dennis.group_purchase.group_purchase.model.user.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	private final UserDetails user;
	public JwtResponse(String jwttoken, UserDetails user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public UserDetails getUserDetails() {
		return this.user;
	}
}