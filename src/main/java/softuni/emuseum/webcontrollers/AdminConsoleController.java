package softuni.emuseum.webcontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import softuni.emuseum.models.responce.PageStatResponseModel;
import softuni.emuseum.models.responce.UserResponseModel;
import softuni.emuseum.models.service.PageStatServiceModel;
import softuni.emuseum.services.api.PageStatService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminConsoleController {

    private final PageStatService pageStatService;

    public AdminConsoleController(PageStatService pageStatService) {
        this.pageStatService = pageStatService;
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/admin")
    public String admin(){
        return "admin-console";
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/pages/all")
    public String api() {

        return "pages";
    }

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
//----------------------------------------------------------------------------------------------------------------------
}
