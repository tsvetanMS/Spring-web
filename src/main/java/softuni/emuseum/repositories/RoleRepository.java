package softuni.emuseum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.emuseum.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByAuthority(String name);

}
