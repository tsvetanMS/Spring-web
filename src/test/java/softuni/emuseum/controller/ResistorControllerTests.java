package softuni.emuseum.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.repositories.ResistorRepository;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResistorControllerTests {

    @Autowired
    ResistorRepository resistorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        resistorRepository.deleteAll();

        Resistor resistorOne = new Resistor("1ZsQ-f8hBKkpiZ9WmSLP2bskk0rK_xvE-",
                "Produced in USSR. Resistor model С5-5В-1В.", "Wired", "3.3 Om", "1W", "5 percent" );
        Resistor resistorTwo = new Resistor("1HeUFXBBRfPWkSamGYA2iJE-75m06pobG",
                "Produced in Bulgaria. Resistor model РПМ2.", "Metal film", "22 Om", "0.25W", "5 percent" );
        resistorRepository.saveAndFlush(resistorOne);
        resistorRepository.saveAndFlush(resistorTwo);
    }
    @AfterEach
    public void after(){
        resistorRepository.deleteAll();
    }


//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void allResistorsTest() throws Exception {
        this.mockMvc.perform(get("/all-resistors"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchAllResistorsTest() throws Exception {

//        User user = new User();
//        Role role = new Role();
//
//        role.setAuthority("ROLE_ADMIN");
//        roleRepository.saveAndFlush(role);
//
//        Set<Role> authorities = new HashSet<>();
//        user.setAuthorities(authorities);
//        user.getAuthorities().add(role);
//        user.setUsername("admin");
//        user.setEmail("mail@mail.bg");
//        user.setPassword("123");
//
//        userRepository.saveAndFlush(user);


        this.mockMvc.perform(get("/fetch/resistors"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(2)));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorEditTest() throws Exception {
        Resistor resistorFromDB = this.resistorRepository.findAll().get(0);
        long id = resistorFromDB.getId();
        this.mockMvc.perform(get("/resistors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorDeleteTest() throws Exception {
        Resistor resistorFromDB = this.resistorRepository.findAll().get(0);
        long id = resistorFromDB.getId();
        this.mockMvc.perform(get("/resistors/delete/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorParamsTest() throws Exception {
        Resistor resistorFromDB = this.resistorRepository.findAll().get(0);
        long id = resistorFromDB.getId();
        this.mockMvc.perform(get("/resistors/params/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchResistorTest() throws Exception {
        Resistor resistorFromDB = this.resistorRepository.findAll().get(0);
        long id = resistorFromDB.getId();
        this.mockMvc.
                perform(get("/fetch/resistor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(resistorFromDB.getType())))
                .andExpect(jsonPath("$.resistance", is(resistorFromDB.getResistance())))
                .andExpect(jsonPath("$.maxPower", is(resistorFromDB.getMaxPower())));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorEditCreateTest() throws Exception {
        Resistor resistorFromDB = this.resistorRepository.findAll().get(0);
        long id = resistorFromDB.getId();
        this.mockMvc.perform(post("/resistors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorAddTest() throws Exception {
        this.mockMvc.perform(get("/resistors/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void resistorAddCreateTest() throws Exception {
        this.mockMvc.perform(post("/resistors/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------

}
