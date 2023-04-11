package dennis.group_purchase.group_purchase.service.user;

import dennis.group_purchase.group_purchase.constant.user.ERole;
import dennis.group_purchase.group_purchase.model.user.Users;
import dennis.group_purchase.group_purchase.repository.RoleRepository;
import dennis.group_purchase.group_purchase.repository.UserRepository;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import dennis.group_purchase.group_purchase.model.user.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Service
public class UserRelatedService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }
    public Users loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(email);
    }
    public Users saveUsers(Users user){
        Set<Role> hashSet = new HashSet<>();
        Role role = new Role(ERole.ROLE_USER);
        roleRepository.save(role);
        hashSet.add(role);
        user.setRoles(hashSet);
        user.setAva("");
        return  userRepository.save(user);

    }
    public Users upateUsers(Users user){
        Users findeduser = userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getUsername() != null) {
            findeduser.setName(user.getUsername());
        }
        if (user.getEmail() != null ) {
            findeduser.setEmail(user.getEmail());
        }
        if (user.getNickname() != null ) {
            findeduser.setNickname(user.getNickname());
        }
        // add other fields to update as necessary

        return userRepository.save(findeduser);

    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
