package dennis.group_purchase.group_purchase.repository;

import dennis.group_purchase.group_purchase.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findOneByUsername(String username);
    Users findOneByEmail(String email);

    Users findOneById(Long id);
}
