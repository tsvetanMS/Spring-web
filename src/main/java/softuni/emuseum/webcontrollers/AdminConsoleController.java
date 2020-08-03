package softuni.emuseum.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminConsoleController {

    @GetMapping("/admin")
    public String admin(){
        return "admin-console";
    }
}
