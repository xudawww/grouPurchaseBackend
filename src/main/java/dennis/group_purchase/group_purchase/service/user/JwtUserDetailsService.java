package dennis.group_purchase.group_purchase.service.user;

import dennis.group_purchase.group_purchase.model.user.Users;
import dennis.group_purchase.group_purchase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findOneByUsername(username);

		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}



}