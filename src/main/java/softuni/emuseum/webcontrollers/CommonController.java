package softuni.emuseum.webcontrollers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/under-construction")
    @PreAuthorize("isAuthenticated()")
    public String under(){
        return "under-construction";
    }

    @GetMapping("/index")
    public String index() {
        return "home";
    }

    @GetMapping("/")
    public String indexSlash() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() { return "about"; }

/*
    в началото мислех home да е за логнати потребители, а index за нелогнати
    сега home е за всички потребители и проверката за логнат или не се
    прави от Thymeleaf в HTML-а
 */
}
