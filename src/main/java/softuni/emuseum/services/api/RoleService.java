package softuni.emuseum.services.api;

import softuni.emuseum.entities.Role;
import softuni.emuseum.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDatabase();

    Set<RoleServiceModel> findAllAuthorities();

    RoleServiceModel findByAuthority(String name);


}

