package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.emuseum.entities.Role;
import softuni.emuseum.models.service.RoleServiceModel;
import softuni.emuseum.models.service.UserServiceModel;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.api.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;




    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public void seedRolesInDatabase() {
        if (roleRepository.count() == 0){
            roleRepository.save(new Role("ROLE_ROOT"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_MODERATOR"));
            roleRepository.save(new Role("ROLE_USER"));
        }
    }


//----------------------------------------------------------------------------------------------------------------------
    @Override
    public Set<RoleServiceModel> findAllAuthorities() {

        return this.roleRepository.findAll().stream()
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String roleName) {

        Role role = this.roleRepository.findRoleByAuthority(roleName);

        return this.modelMapper.map(role, RoleServiceModel.class);
    }
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------
}
