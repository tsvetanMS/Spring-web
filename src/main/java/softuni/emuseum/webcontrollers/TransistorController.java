package softuni.emuseum.webcontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.emuseum.models.binding.TransistorBindingModel;
import softuni.emuseum.models.responce.CommonResponseModel;
import softuni.emuseum.models.responce.TransistorParamsResponseModel;
import softuni.emuseum.models.service.TransistorServiceModel;
import softuni.emuseum.services.api.TransistorService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class TransistorController {

    private final TransistorService transistorService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public TransistorController(TransistorService transistorService, ModelMapper modelMapper, UserService userService) {
        this.transistorService = transistorService;
        this.modelMapper = modelMapper;
        this.userService = userService;

    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/all-transistors")
    @PreAuthorize("isAuthenticated()")
    public String getAllTransistors() {

        return "all-transistors";
    }

    @GetMapping(value = "/fetch/transistors")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommonResponseModel>> getAllTransistors(Principal principal) {

        String username = principal.getName();
        Set<String> roles = this.userService.findUserRolesByUsername(username);
        String role;

        if (roles.contains("ROLE_ADMIN")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }

        List<CommonResponseModel> result = transistorService.findAllTransistors()
                .stream()
                .map(transistor -> modelMapper.map(transistor, CommonResponseModel.class))
                .collect(Collectors.toList());

        result.forEach(transistor -> transistor.setUserRole(role));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("transistors/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTransistor(@PathVariable("id") Long id) {
        this.transistorService.deleteTransistorById(id);

        return "redirect:/all-transistors";
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/transistors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable("id") Long id, Model model) {

        if(!model.containsAttribute("transistorBindingModel")){
            TransistorServiceModel transistorSM = this.transistorService.findTransistorById(id);

            model.addAttribute("transistorBindingModel", this.modelMapper.map(transistorSM, TransistorBindingModel.class));
        }

        return "edit-transistor";
    }

//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/transistors/params/{id}")
    @PreAuthorize("isAuthenticated()")
    public String transistorParams(@PathVariable("id") Long id, Model model) {

        model.addAttribute("transistorID", id);


        return "params-transistor";

    }
//----------------------------------------------------------------------------------------------------------------------
        @GetMapping("/fetch/transistor/{id}")
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<TransistorParamsResponseModel> showParams(@PathVariable("id") Long id) {
            TransistorServiceModel transistorSM = this.transistorService.findTransistorById(id);
            TransistorParamsResponseModel result = this.modelMapper.map(transistorSM, TransistorParamsResponseModel.class);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
//----------------------------------------------------------------------------------------------------------------------

        @PostMapping("/transistors/edit/{id}")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public String editConfirm(@Valid @ModelAttribute("transistorBindingModel") TransistorBindingModel transistorBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("transistorBindingModel", transistorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.transistorBindingModel", bindingResult);

            String redirect = String.format("redirect:/transistors/edit/%d", id);

            return redirect;

        } else {

            TransistorServiceModel transistorSM = this.modelMapper.map(transistorBindingModel, TransistorServiceModel.class);
            transistorSM.setId(id);

            this.transistorService.saveTransistorInDatabase(transistorSM);

            return "redirect:/all-transistors";
        }


        }
//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/transistors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTransistor(Model model) {

        if(!model.containsAttribute("transistorBindingModel")){

            model.addAttribute("transistorBindingModel", new TransistorBindingModel());
        }

        return "add-transistor";
    }

    @PostMapping("/transistors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTransistorConfirm(@Valid @ModelAttribute("transistorBindingModel") TransistorBindingModel transistorBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("transistorBindingModel", transistorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.transistorBindingModel", bindingResult);

            return "redirect:/transistors/add";

        } else {

            TransistorServiceModel transistorSM = this.modelMapper.map(transistorBindingModel, TransistorServiceModel.class);

            this.transistorService.saveTransistorInDatabase(transistorSM);

            return "redirect:/all-transistors";
        }


    }

}
