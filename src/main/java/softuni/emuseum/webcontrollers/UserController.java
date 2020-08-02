package softuni.emuseum.webcontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.emuseum.models.binding.UserRegisterBindingModel;
import softuni.emuseum.models.responce.UserResponseModel;
import softuni.emuseum.models.service.UserServiceModel;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;



    public UserController(UserService userService, ModelMapper modelMapper, RoleService roleService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }


    @GetMapping("/users/all")
    public String api() {

        return "users";
    }

    @GetMapping("/users/api")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {

        List<UserResponseModel> result = userService.findAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK) ;
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
    // @PreAuthorize("isAnonymous()")
    public String about() {

        return "about";
    }
    @GetMapping("/admin")
    // @PreAuthorize("isAnonymous()")
    public String admin() {

        return "admin";
    }

//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/users/login")
    @PreAuthorize("isAnonymous()")
    public String login() {

        return "login";
    }




//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/users/register")
    @PreAuthorize("isAnonymous()")
    public String register(Model model) {

        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/users/register")
    @PreAuthorize("isAnonymous()")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        roleService.seedRolesInDatabase();

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";

        } else {
            UserServiceModel userSM = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
            this.userService.registerUser(userSM);
        }

        return "redirect:/index";
    }
//----------------------------------------------------------------------------------------------------------------------
}
