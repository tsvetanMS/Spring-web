package softuni.emuseum.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.emuseum.constants.Constants;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.models.service.RoleServiceModel;
import softuni.emuseum.models.service.UserServiceModel;
import softuni.emuseum.repositories.UserRepository;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.api.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND));
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllAuthorities());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);

        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));


        this.userRepository.save(user);

    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND));
        return this.modelMapper.map(user, UserServiceModel.class);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class)).collect(Collectors.toList());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public Set<String> findUserRolesByUsername(String username) {

        UserServiceModel userSM = findUserByUsername(username);
        Set<RoleServiceModel> rolesSM = userSM.getAuthorities();
        Set<String> roles = new HashSet<>();

        rolesSM.forEach((role -> roles.add(role.getAuthority())));

        return roles;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void promoteUserToAdmin(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

       if(user != null){
            Role role = this.modelMapper.map(this.roleService.findByAuthority("ROLE_ADMIN"), Role.class);
            user.getAuthorities().add(role);
            this.userRepository.saveAndFlush(user);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if(user != null){
           this.userRepository.deleteById(userId);
        }

    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public UserServiceModel findUserById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }
//----------------------------------------------------------------------------------------------------------------------

}
