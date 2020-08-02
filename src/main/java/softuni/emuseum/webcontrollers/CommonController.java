package softuni.emuseum.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/under-construction")
    public String under(){
        return "under-construction";
    }
}
