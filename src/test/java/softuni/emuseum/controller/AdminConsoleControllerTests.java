package softuni.emuseum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.entities.PageStat;
import softuni.emuseum.repositories.PageStatRepository;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminConsoleControllerTests {


    @Autowired
    PageStatRepository pageStatRepository;

    @Autowired
    private MockMvc mockMvc;

//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void adminTest() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void pagesAllTest() throws Exception {
        this.mockMvc.perform(get("/pages/all"))
                .andExpect(status().isOk());
    }
//----------------------------------------------------------------------------------------------------------------------
/*
    @GetMapping("/pages/api")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PageStatResponseModel>> getAllPages() {

        List<PageStatServiceModel> pages = pageStatService.findAllPages();
        List<PageStatResponseModel> result = new ArrayList<>();

        pages.forEach(page -> {
            PageStatResponseModel pageStatRM = new PageStatResponseModel();
            pageStatRM.setRoute(page.getRoute());
            pageStatRM.setVisits(page.getVisits());
            pageStatRM.setLastVisit(page.getLastVisit().toString());
            result.add(pageStatRM);
        });


        return new ResponseEntity<>(result, HttpStatus.OK) ;
    }
 */

    @Test
    @WithMockUser(username = "admin",roles = {"USER","ADMIN"})
    public void pagesApiTest() throws Exception {

        this.pageStatRepository.deleteAll();

        PageStat pageStatOne = new PageStat("/index", 10L, LocalDateTime.now());
        PageStat pageStatTwo = new PageStat("/about", 20L, LocalDateTime.now());

        this.pageStatRepository.saveAndFlush(pageStatOne);
        this.pageStatRepository.saveAndFlush(pageStatTwo);

        // размера е три, защото Spring-а хваща и заявката на теста към контролера
        this.mockMvc.
                perform(get("/pages/api"))
                .andExpect(status().isOk())
                . andExpect(jsonPath("$", hasSize(3)));
    }
//----------------------------------------------------------------------------------------------------------------------

}
