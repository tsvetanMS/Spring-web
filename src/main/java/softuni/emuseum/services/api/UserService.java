package softuni.emuseum.services.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.models.service.RoleServiceModel;
import softuni.emuseum.models.service.UserServiceModel;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    List<User> findAllUsers();

    Set<String> findUserRolesByUsername(String username);

}