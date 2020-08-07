package softuni.emuseum.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.models.service.UserServiceModel;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.repositories.UserRepository;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.api.UserService;
import softuni.emuseum.services.impl.RoleServiceImpl;
import softuni.emuseum.services.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService;
    private UserService userService;

    private ModelMapper modelMapper;
    private BCryptPasswordEncoder encoder;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.encoder = new BCryptPasswordEncoder();
        this.roleService = new RoleServiceImpl(roleRepository, modelMapper);
        this.userService = new UserServiceImpl(userRepository, roleService, modelMapper, encoder);
    }
//----------------------------------------------------------------------------------------------------------------------
    /*
     @Override
    public UserServiceModel findUserById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }
     */

    // връща ми null от мокнатото User Repository ?!
    @Test
    public void findUserByIdTest() {
        User user = new User();
        user.setUsername("usernameOne");
        user.setEmail("usernameOne@email");
        user.setAuthorities(new HashSet<>());
        user.setPassword(this.encoder.encode("123"));
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserServiceModel actualUserSM = this.userService.findUserById(1L);
        User expectedUser = user;

        Assert.assertEquals(expectedUser.getUsername(), actualUserSM.getUsername());
    }
//----------------------------------------------------------------------------------------------------------------------
}
