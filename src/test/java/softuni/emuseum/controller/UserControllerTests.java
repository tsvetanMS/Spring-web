package softuni.emuseum.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.emuseum.entities.PageStat;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.entities.User;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.repositories.UserRepository;
import softuni.emuseum.services.api.UserService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;


//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void usersEditTest() throws Exception {
        this.mockMvc.perform(get("/users/edit"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getAllUsersForEditApiTest() throws Exception {
        this.userRepository.deleteAll();
        User user = new User();
        Role role = new Role();

        role.setAuthority("ROLE_ADMIN");
        roleRepository.saveAndFlush(role);

        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);
        user.getAuthorities().add(role);
        user.setUsername("admin");
        user.setEmail("mail@mail.bg");
        user.setPassword("123");

        userRepository.saveAndFlush(user);

        this.mockMvc.
                perform(get("/users/edit/api"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(1)));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    // тук потребителя не е логнат все още
    //@WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getUserLoginTest() throws Exception {
        this.mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    // тук потребителя не е логнат все още
    //@WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getUserRegisterTest() throws Exception {
        this.mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    // тук потребителя не е логнат все още
    //@WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getUserRegisterCreateTest() throws Exception {
        this.mockMvc.perform(post("/users/register"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void promoteUserToAdminTest() throws Exception {
        this.userRepository.deleteAll();
        User user = new User();
        Role role = new Role();

        role.setAuthority("ROLE_USER");
        roleRepository.saveAndFlush(role);

        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);
        user.getAuthorities().add(role);
        user.setUsername("userOne");
        user.setEmail("mail@mail.bg");
        user.setPassword("123");

        userRepository.saveAndFlush(user);
        User userFromDB = this.userRepository.findAll().get(0);
        long id = userFromDB.getId();

        this.mockMvc.perform(get("/users/promote/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void deleteUserTest() throws Exception {
        this.userRepository.deleteAll();
        User user = new User();
        Role role = new Role();

        role.setAuthority("ROLE_USER");
        roleRepository.saveAndFlush(role);

        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);
        user.getAuthorities().add(role);
        user.setUsername("userOne");
        user.setEmail("mail@mail.bg");
        user.setPassword("123");

        userRepository.saveAndFlush(user);
        User userFromDB = this.userRepository.findAll().get(0);
        long id = userFromDB.getId();

        this.mockMvc.perform(get("/users/delete/{id}",id ))
                .andExpect(status().is(302));
    }
//----------------------------------------------------------------------------------------------------------------------
}
