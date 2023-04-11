package dennis.group_purchase.group_purchase.repository;

import dennis.group_purchase.group_purchase.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
