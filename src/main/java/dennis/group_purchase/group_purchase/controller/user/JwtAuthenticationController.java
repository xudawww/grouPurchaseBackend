package dennis.group_purchase.group_purchase.controller.user;

import dennis.group_purchase.group_purchase.model.Jwt.JwtError;
import dennis.group_purchase.group_purchase.model.user.requests.RegisterUser;
import dennis.group_purchase.group_purchase.model.user.Users;
import dennis.group_purchase.group_purchase.service.user.UserRelatedService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import dennis.group_purchase.group_purchase.config.jwt.JwtTokenUtil;
import dennis.group_purchase.group_purchase.model.Jwt.JwtRequest;
import dennis.group_purchase.group_purchase.model.Jwt.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private UserRelatedService userRelatedService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.set(HttpHeaders.CONTENT_ENCODING, "UTF-8");
					final UserDetails userDetails = jwtInMemoryUserDetailsService
							.loadUserByUsername(authenticationRequest.getUsername());
					final String token = jwtTokenUtil.generateToken(userDetails);
					return new ResponseEntity<>(new JwtResponse(token, userDetails), headers, HttpStatus.OK);




	}
	@RequestMapping(value = "/register", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> regsiterUser(@Valid @RequestBody RegisterUser registerUser){
          try {
			  if (userRelatedService.loadUserByUsername(registerUser.getUsername()) != null || userRelatedService.loadUserByEmail(registerUser.getEmail()) != null) {
				  return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
			  }
			  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			  Users regsiterUser = userRelatedService.saveUsers(new Users(registerUser.getUsername(), registerUser.getEmail(), registerUser.getNickname(), passwordEncoder.encode(registerUser.getPassword())));
			  return ResponseEntity.ok(regsiterUser);
		  }
		  catch (Exception e){
			  return ResponseEntity
					  .status(HttpStatus.SERVICE_UNAVAILABLE)
					  .body(new JwtError("Server Error"));
		  }

	}
	@RequestMapping(value = "/refresh/token", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.CONTENT_ENCODING, "UTF-8");
		Map<String, Object> expectedMap = userRelatedService.getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return new ResponseEntity<>(new JwtResponse(token, null), headers, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String fieldName = error.getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}


}
