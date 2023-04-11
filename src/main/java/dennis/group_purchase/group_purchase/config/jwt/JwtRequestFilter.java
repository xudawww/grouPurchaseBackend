package dennis.group_purchase.group_purchase.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dennis.group_purchase.group_purchase.model.Jwt.JwtError;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import dennis.group_purchase.group_purchase.service.user.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private RequestMatcher excludeUrlsLogin = new AntPathRequestMatcher("/login");
	private RequestMatcher excludeUrlsRegister = new AntPathRequestMatcher("/register");


	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

        if(!excludeUrlsLogin.matches(request) && !excludeUrlsRegister.matches(request) ) {
			final String requestTokenHeader = request.getHeader("Authorization");

			String username = null;
			String jwtToken = null;
			String errorMsg = "";
			// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7,requestTokenHeader.length());
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					errorMsg = "无法获取验证令牌。";
				} catch (ExpiredJwtException e) {
					errorMsg = "令牌已经过期。";
					String isRefreshToken = request.getHeader("isRefreshToken");
					String requestURL = request.getRequestURL().toString();
					// allow for Refresh Token creation if following conditions are true.
					if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("/refresh/token")) {
						allowForRefreshToken(e, request);
						errorMsg = "";
					}
				} catch (Exception e) {
					errorMsg = "无法获取验证令牌。";
				}
			} else {
				errorMsg = "令牌格式错误。";
			}
			if (!errorMsg.equals("")) {
				Gson gson = new Gson();
				String jsonErrMsg = gson.toJson(new JwtError(errorMsg));
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter out = response.getWriter();
				out.print(jsonErrMsg);
				return;

			}
			//Once we get the token validate it.
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

				// if token is valid configure Spring Security to manually set authentication
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		chain.doFilter(request, response);
	}
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		request.setAttribute("claims", ex.getClaims());

	}
}
