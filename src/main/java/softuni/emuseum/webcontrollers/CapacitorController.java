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
import softuni.emuseum.models.binding.CapacitorBindingModel;
import softuni.emuseum.models.responce.CapacitorParamsResponseModel;
import softuni.emuseum.models.responce.CommonResponseModel;
import softuni.emuseum.models.service.CapacitorServiceModel;
import softuni.emuseum.services.api.CapacitorService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CapacitorController {

    private final CapacitorService capacitorService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public CapacitorController(CapacitorService capacitorService, ModelMapper modelMapper, UserService userService) {
        this.capacitorService = capacitorService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/all-capacitors")
    @PreAuthorize("isAuthenticated()")
    public String getAllCapacitors() {

        return "all-capacitors";
    }

    @GetMapping(value = "/fetch/capacitors")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommonResponseModel>> getAllCapacitors(Principal principal) {

        String username = principal.getName();
        Set<String> roles = this.userService.findUserRolesByUsername(username);
        String role;

        if (roles.contains("ROLE_ADMIN")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }

        List<CommonResponseModel> result = capacitorService.findAllCapacitors()
                .stream()
                .map(capacitor -> modelMapper.map(capacitor, CommonResponseModel.class))
                .collect(Collectors.toList());

        result.forEach(capacitor -> capacitor.setUserRole(role));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("capacitors/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCapacitor(@PathVariable("id") Long id) {
        this.capacitorService.deleteCapacitorById(id);

        return "redirect:/all-capacitors";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/capacitors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable("id") Long id, Model model) {

        if(!model.containsAttribute("capacitorBindingModel")) {
            CapacitorServiceModel capacitorSM = this.capacitorService.findCapacitorById(id);

            model.addAttribute("capacitorBindingModel", this.modelMapper.map(capacitorSM, CapacitorBindingModel.class));
        }

        return "edit-capacitor";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/capacitors/params/{id}")
    @PreAuthorize("isAuthenticated()")
    public String capacitorParams(@PathVariable("id") Long id, Model model) {

        model.addAttribute("capacitorID", id);


        return "params-capacitor";

    }
    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/fetch/capacitor/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CapacitorParamsResponseModel> showParams(@PathVariable("id") Long id) {
        CapacitorServiceModel capacitorSM = this.capacitorService.findCapacitorById(id);
        CapacitorParamsResponseModel result = this.modelMapper.map(capacitorSM, CapacitorParamsResponseModel.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//----------------------------------------------------------------------------------------------------------------------
    @PostMapping("/capacitors/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editConfirm(@Valid @ModelAttribute("capacitorBindingModel") CapacitorBindingModel capacitorBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("capacitorBindingModel", capacitorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.capacitorBindingModel", bindingResult);

            String redirect = String.format("redirect:/capacitors/edit/%d", id);

            return redirect;

        } else {

            CapacitorServiceModel capacitorSM = this.modelMapper.map(capacitorBindingModel, CapacitorServiceModel.class);
            capacitorSM.setId(id);

            this.capacitorService.saveCapacitorInDatabase(capacitorSM);

            return "redirect:/all-capacitors";
        }


}


//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/capacitors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCapacitor(Model model) {

        if(!model.containsAttribute("capacitorBindingModel")){

            model.addAttribute("capacitorBindingModel", new CapacitorBindingModel());
        }

        return "add-capacitor";
    }

    @PostMapping("/capacitors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCapacitorConfirm(@Valid @ModelAttribute("capacitorBindingModel") CapacitorBindingModel capacitorBindingModel,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("capacitorBindingModel", capacitorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.capacitorBindingModel", bindingResult);

            return "redirect:/capacitors/add";

        } else {

            CapacitorServiceModel capacitorSM = this.modelMapper.map(capacitorBindingModel, CapacitorServiceModel.class);

            this.capacitorService.saveCapacitorInDatabase(capacitorSM);

            return "redirect:/all-capacitors";
        }


    }

}


