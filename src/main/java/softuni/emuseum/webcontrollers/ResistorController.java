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
import softuni.emuseum.models.binding.ResistorBindingModel;
import softuni.emuseum.models.responce.CommonResponseModel;
import softuni.emuseum.models.responce.ResistorParamsResponseModel;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.services.api.ResistorService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ResistorController {

    private final ResistorService resistorService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public ResistorController(ResistorService resistorService, ModelMapper modelMapper, UserService userService) {
        this.resistorService = resistorService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/all-resistors")
    @PreAuthorize("isAuthenticated()")
    public String getAllResistors() {

        return "all-resistors";
    }

    @GetMapping(value = "/fetch/resistors")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommonResponseModel>> getAllResistors(Principal principal) {

        String username = principal.getName();
        Set<String> roles = this.userService.findUserRolesByUsername(username);
        String role;

        if (roles.contains("ROLE_ADMIN")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }

        List<CommonResponseModel> result = resistorService.findAllResistors()
                .stream()
                .map(resistor -> modelMapper.map(resistor, CommonResponseModel.class))
                .collect(Collectors.toList());

        result.forEach(resistor -> resistor.setUserRole(role));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("resistors/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteResistor(@PathVariable("id") Long id) {
        this.resistorService.deleteResistorById(id);

        return "redirect:/all-resistors";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/resistors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable("id") Long id, Model model) {

        if(!model.containsAttribute("resistorBindingModel")) {
            ResistorServiceModel resistorSM = this.resistorService.findResistorById(id);

            model.addAttribute("resistorBindingModel", this.modelMapper.map(resistorSM, ResistorBindingModel.class));
        }

        return "edit-resistor";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/resistors/params/{id}")
    @PreAuthorize("isAuthenticated()")
    public String resistorParams(@PathVariable("id") Long id, Model model) {

        model.addAttribute("resistorID", id);


        return "params-resistor";

    }
    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/fetch/resistor/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResistorParamsResponseModel> showParams(@PathVariable("id") Long id) {
        ResistorServiceModel resistorSM = this.resistorService.findResistorById(id);
        ResistorParamsResponseModel result = this.modelMapper.map(resistorSM, ResistorParamsResponseModel.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//----------------------------------------------------------------------------------------------------------------------

    @PostMapping("/resistors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editConfirm(@Valid @ModelAttribute("resistorBindingModel") ResistorBindingModel resistorBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("resistorBindingModel", resistorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resistorBindingModel", bindingResult);

            String redirect = String.format("redirect:/resistors/edit/%d", id);

            return redirect;

        } else {

            ResistorServiceModel resistorSM = this.modelMapper.map(resistorBindingModel, ResistorServiceModel.class);
            resistorSM.setId(id);

            this.resistorService.saveResistorInDatabase(resistorSM);

            return "redirect:/all-resistors";
        }


    }

//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/resistors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addResistor(Model model) {

        if(!model.containsAttribute("resistorBindingModel")){

            model.addAttribute("resistorBindingModel", new ResistorBindingModel());
        }

        return "add-resistor";
    }

    @PostMapping("/resistors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTransistorConfirm(@Valid @ModelAttribute("resistorBindingModel") ResistorBindingModel resistorBindingModel,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("resistorBindingModel", resistorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resistorBindingModel", bindingResult);

            return "redirect:/resistors/add";

        } else {

            ResistorServiceModel resistorSM = this.modelMapper.map(resistorBindingModel, ResistorServiceModel.class);

            this.resistorService.saveResistorInDatabase(resistorSM);

            return "redirect:/all-resistors";
        }


    }




}
