package softuni.emuseum.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.emuseum.entities.Diode;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.repositories.DiodeRepository;
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
public class DiodeControllerTests {

    @Autowired
    DiodeRepository diodeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        diodeRepository.deleteAll();

        Diode diodeOne = new Diode("1k2OU3IxYfGEFt8r1aRY_v67EHdQuGDvn",
                "Produced in Bulgaria. Diode model 2Д2402.", "Rectifier.", "100V", "1A");
        Diode diodeTwo = new Diode("1Gs8cDnZ8P2uTcJmxI3HsWPy8VOjH8I4Q",
                "Produced in Bulgaria. Diode model 2Д5607.", "Fast switch.", "60V", "50mA");

        diodeRepository.saveAndFlush(diodeOne);
        diodeRepository.saveAndFlush(diodeTwo);
    }
    @AfterEach
    public void after(){
        diodeRepository.deleteAll();
    }


//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void allDiodesTest() throws Exception {
        this.mockMvc.perform(get("/all-diodes"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchAllDiodesTest() throws Exception {

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


        this.mockMvc.perform(get("/fetch/diodes"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(2)));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeEditTest() throws Exception {
        Diode diodeFromDB = this.diodeRepository.findAll().get(0);
        long id = diodeFromDB.getId();
        this.mockMvc.perform(get("/diodes/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeDeleteTest() throws Exception {
        Diode diodeFromDB = this.diodeRepository.findAll().get(0);
        long id = diodeFromDB.getId();
        this.mockMvc.perform(get("/diodes/delete/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeParamsTest() throws Exception {
        Diode diodeFromDB = this.diodeRepository.findAll().get(0);
        long id = diodeFromDB.getId();
        this.mockMvc.perform(get("/diodes/params/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchCapacitorTest() throws Exception {
        Diode diodeFromDB = this.diodeRepository.findAll().get(0);
        long id = diodeFromDB.getId();
        this.mockMvc.
                perform(get("/fetch/diode/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(diodeFromDB.getType())))
                .andExpect(jsonPath("$.maxCurrent", is(diodeFromDB.getMaxCurrent())))
                .andExpect(jsonPath("$.maxVoltage", is(diodeFromDB.getMaxVoltage())));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeEditCreateTest() throws Exception {
        Diode diodeFromDB = this.diodeRepository.findAll().get(0);
        long id = diodeFromDB.getId();
        this.mockMvc.perform(post("/diodes/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeAddTest() throws Exception {
        this.mockMvc.perform(get("/diodes/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void diodeAddCreateTest() throws Exception {
        this.mockMvc.perform(post("/diodes/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------

}
