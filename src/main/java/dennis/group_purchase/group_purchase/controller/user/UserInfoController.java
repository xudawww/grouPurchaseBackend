package dennis.group_purchase.group_purchase.controller.user;

import dennis.group_purchase.group_purchase.config.jwt.JwtTokenUtil;
import dennis.group_purchase.group_purchase.model.Jwt.JwtRequest;
import dennis.group_purchase.group_purchase.model.user.Users;
import dennis.group_purchase.group_purchase.model.user.requests.RequestUserByEmail;
import dennis.group_purchase.group_purchase.service.user.UserRelatedService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

@RestController
@CrossOrigin
public class UserInfoController {
    @Autowired
    private UserRelatedService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @RequestMapping(value = "/email/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> getUser(@RequestBody RequestUserByEmail requestUserByEmail) {
        return new ResponseEntity<>(userDetailsService.loadUserByEmail(requestUserByEmail.getEmail()), HttpStatus.OK);
    }

    @RequestMapping(value = "/userUpdate", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody Users user) {
        return new ResponseEntity<>(userDetailsService.upateUsers(user), HttpStatus.OK);
    }
}
