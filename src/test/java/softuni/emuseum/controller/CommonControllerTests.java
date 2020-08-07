package softuni.emuseum.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void underConstructionTest() throws Exception {
        this.mockMvc.perform(get("/under-construction"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getIndexTest() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getSlashTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getHomeTest() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void getAboutTest() throws Exception {
        this.mockMvc.perform(get("/about"))
                .andExpect(status().isOk());
    }

}
