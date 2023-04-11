package dennis.group_purchase.group_purchase.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dennis.group_purchase.group_purchase.model.Jwt.JwtError;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		String errMsg = "";
		if (authException instanceof BadCredentialsException) {
			errMsg = "账号密码错误，无法登录。";
		}
		else if(authException instanceof BadCredentialsException){
			errMsg = "用户被禁止登录。";
		}
		else{
			errMsg = "无法验证用户。";
		}
		Gson gson = new Gson();
		String jsonErrMsg = gson.toJson(new JwtError(errMsg));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter out = response.getWriter();
		out.print(jsonErrMsg);
	}
}