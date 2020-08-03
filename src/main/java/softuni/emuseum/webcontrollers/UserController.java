package softuni.emuseum.webcontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.emuseum.entities.Role;
import softuni.emuseum.entities.User;
import softuni.emuseum.models.binding.UserRegisterBindingModel;
import softuni.emuseum.models.responce.PageStatResponseModel;
import softuni.emuseum.models.responce.UserEditResponseModel;
import softuni.emuseum.models.responce.UserResponseModel;
import softuni.emuseum.models.service.PageStatServiceModel;
import softuni.emuseum.models.service.RoleServiceModel;
import softuni.emuseum.models.service.UserActivityServiceModel;
import softuni.emuseum.models.service.UserServiceModel;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.api.UserActivityStatService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final UserActivityStatService userActivityStatService;



    public UserController(UserService userService, ModelMapper modelMapper, RoleService roleService, UserActivityStatService userActivityStatService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.userActivityStatService = userActivityStatService;
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/users/edit")
    public String edit() {

        return "all-users";
    }

    @GetMapping("/users/edit/api")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserEditResponseModel>> getAllUsersForEdit() {

        List<UserServiceModel> users = userService.findAllUsers();
        List<UserEditResponseModel>  result = new ArrayList<>();

        users.forEach(user -> {
            UserEditResponseModel userEditRM = new UserEditResponseModel();
            userEditRM.setId(user.getId());
            userEditRM.setUsername(user.getUsername());
            userEditRM.setEmail(user.getEmail());
            Set<RoleServiceModel> roles = user.getAuthorities();

            for (var role : roles) {
                if(role.getAuthority().equals("ROLE_ADMIN")){
                    userEditRM.setUserRole("ADMIN");
                }
            }

            if(userEditRM.getUserRole() == null){
               userEditRM.setUserRole("USER");
            }

            result.add(userEditRM);
        });

        return new ResponseEntity<>(result, HttpStatus.OK) ;
    }
//----------------------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/about")
    public String about() {

        return "about";
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
@GetMapping("/users/all")
public String users() {

    return "users";
}

    @GetMapping("/fetch/users")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {

        List<UserActivityServiceModel> users = userActivityStatService.findAllUsersActivity();
        List<UserResponseModel> result = new ArrayList<>();

        users.forEach(user -> {
            UserResponseModel userRM = new UserResponseModel();
            userRM.setUsername(user.getUsername());
            userRM.setRoute(user.getRoute());
            userRM.setDateTime(user.getDateTime().toString());
            result.add(userRM);
        });


        return new ResponseEntity<>(result, HttpStatus.OK) ;
    }
//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/users/promote/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String promoteUser(@PathVariable("id") Long id){
        this.userService.promoteUserToAdmin(id);
        return "redirect:/users/edit";
    }
//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/users/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return "redirect:/users/edit";
    }
//----------------------------------------------------------------------------------------------------------------------
}
