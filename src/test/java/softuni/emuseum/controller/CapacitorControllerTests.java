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
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.repositories.CapacitorRepository;
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
public class CapacitorControllerTests {

    @Autowired
    CapacitorRepository capacitorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        capacitorRepository.deleteAll();

        Capacitor capacitorOne = new Capacitor("1P66qO_nXkcjQ0pTJUd6PL6R7tT_XOFGw",
                "Produced in Bulgaria. Capacitor model ККрД.", "Ceramic disk", "4.7nF", "100V");

        Capacitor capacitorTwo = new Capacitor("1nnF7BGdLVXkXs97_Uh8T34mDbByDNs21",
                "Produced in Bulgaria. Capacitor model MPT-221.", "Polyester Multilayer", "6.8nF", "630V");

        capacitorRepository.saveAndFlush(capacitorOne);
        capacitorRepository.saveAndFlush(capacitorTwo);
    }
    @AfterEach
    public void after(){
        capacitorRepository.deleteAll();
    }


//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void allCapacitorsTest() throws Exception {
        this.mockMvc.perform(get("/all-capacitors"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchAllCapacitorsTest() throws Exception {

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


        this.mockMvc.perform(get("/fetch/capacitors"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(2)));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorEditTest() throws Exception {
        Capacitor capacitorFromDB = this.capacitorRepository.findAll().get(0);
        long id = capacitorFromDB.getId();
        this.mockMvc.perform(get("/capacitors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorDeleteTest() throws Exception {
        Capacitor capacitorFromDB = this.capacitorRepository.findAll().get(0);
        long id = capacitorFromDB.getId();
        this.mockMvc.perform(get("/capacitors/delete/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorParamsTest() throws Exception {
        Capacitor capacitorFromDB = this.capacitorRepository.findAll().get(0);
        long id = capacitorFromDB.getId();
        this.mockMvc.perform(get("/capacitors/params/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void fetchCapacitorTest() throws Exception {
        Capacitor capacitorFromDB = this.capacitorRepository.findAll().get(0);
        long id = capacitorFromDB.getId();
        this.mockMvc.
                perform(get("/fetch/capacitor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(capacitorFromDB.getType())))
                .andExpect(jsonPath("$.capacitance", is(capacitorFromDB.getCapacitance())))
                .andExpect(jsonPath("$.maxVoltage", is(capacitorFromDB.getMaxVoltage())));
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorEditCreateTest() throws Exception {
        Capacitor capacitorFromDB = this.capacitorRepository.findAll().get(0);
        long id = capacitorFromDB.getId();
        this.mockMvc.perform(post("/capacitors/edit/{id}",id ))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorAddTest() throws Exception {
        this.mockMvc.perform(get("/capacitors/add"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void capacitorAddCreateTest() throws Exception {
        this.mockMvc.perform(post("/capacitors/add"))
                .andExpect(status().isOk());
}
//----------------------------------------------------------------------------------------------------------------------


}
