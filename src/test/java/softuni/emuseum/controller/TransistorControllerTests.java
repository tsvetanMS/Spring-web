package softuni.emuseum.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.emuseum.entities.*;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.repositories.TransistorRepository;
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
public class TransistorControllerTests {
    @Autowired
    TransistorRepository transistorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        transistorRepository.deleteAll();

        Transistor transistorOne = new Transistor("1hUfxKmAPlHPGRCue_DbzMLFqeCYTPTYW",
                "Transistor 2T3169. Produced in Bulgaria. Low noise, general purpose, low power.",
                "NPN", "25V", "100mA", "150MHz", "180-850");

        Transistor transistorTwo = new Transistor("1zJkg-Il0Xfau0OCC5l46Ifij2t4UtUkN",
                "Transistor 2T6551. Produced in Bulgaria. Low frequency, general purpose, low power.",
                "NPN", "75V", "500mA", "200MHz", "26-470");

        transistorRepository.saveAndFlush(transistorOne);
        transistorRepository.saveAndFlush(transistorTwo);
    }
    @AfterEach
    public void after(){
        transistorRepository.deleteAll();
    }


//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void allTransistorsTest() throws Exception {
        this.mockMvc.perform(get("/all-transistors"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchAllTransistorsTest() throws Exception {

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


        this.mockMvc.perform(get("/fetch/transistors"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(2)));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorEditTest() throws Exception {
        Transistor transistorFromDB = this.transistorRepository.findAll().get(0);
        long id = transistorFromDB.getId();
        this.mockMvc.perform(get("/transistors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorDeleteTest() throws Exception {
        Transistor transistorFromDB = this.transistorRepository.findAll().get(0);
        long id = transistorFromDB.getId();
        this.mockMvc.perform(get("/transistors/delete/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorParamsTest() throws Exception {
        Transistor transistorFromDB = this.transistorRepository.findAll().get(0);
        long id = transistorFromDB.getId();
        this.mockMvc.perform(get("/transistors/params/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchTransistorTest() throws Exception {
        Transistor transistorFromDB = this.transistorRepository.findAll().get(0);
        long id = transistorFromDB.getId();
        this.mockMvc.
                perform(get("/fetch/transistor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(transistorFromDB.getType())))
                .andExpect(jsonPath("$.maxFrequency", is(transistorFromDB.getMaxFrequency())))
                .andExpect(jsonPath("$.gain", is(transistorFromDB.getGain())));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorEditCreateTest() throws Exception {
        Transistor transistorFromDB = this.transistorRepository.findAll().get(0);
        long id = transistorFromDB.getId();
        this.mockMvc.perform(post("/transistors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorAddTest() throws Exception {
        this.mockMvc.perform(get("/transistors/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void transistorAddCreateTest() throws Exception {
        this.mockMvc.perform(post("/transistors/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------

}
