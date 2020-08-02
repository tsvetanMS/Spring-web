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
import softuni.emuseum.models.binding.DiodeBindingModel;
import softuni.emuseum.models.responce.CommonResponseModel;
import softuni.emuseum.models.responce.DiodeParamsResponseModel;
import softuni.emuseum.models.service.DiodeServiceModel;
import softuni.emuseum.services.api.DiodeService;
import softuni.emuseum.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class DiodeController {


    private final DiodeService diodeService;
    private final ModelMapper modelMapper;
    private final UserService userService;



    public DiodeController(DiodeService diodeService, ModelMapper modelMapper, UserService userService) {
        this.diodeService = diodeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


//----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/all-diodes")
    @PreAuthorize("isAuthenticated()")
    public String getAllDiodes() {

        return "all-diodes";
    }

    @GetMapping(value = "/fetch/diodes")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommonResponseModel>> getAllDiodes(Principal principal) {

        String username = principal.getName();
        Set<String> roles = this.userService.findUserRolesByUsername(username);
        String role;

        if (roles.contains("ROLE_ADMIN")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }

        List<CommonResponseModel> result = diodeService.findAllDiodes()
                .stream()
                .map(diode -> modelMapper.map(diode, CommonResponseModel.class))
                .collect(Collectors.toList());

        result.forEach(diode -> diode.setUserRole(role));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("diodes/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteDiode(@PathVariable("id") Long id) {
        this.diodeService.deleteDiodeById(id);

        return "redirect:/all-diodes";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/diodes/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable("id") Long id, Model model) {

        if(!model.containsAttribute("diodeBindingModel")) {
            DiodeServiceModel diodeSM = this.diodeService.findDiodeById(id);

            model.addAttribute("diodeBindingModel", this.modelMapper.map(diodeSM, DiodeBindingModel.class));
        }

        return "edit-diode";
    }

    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/diodes/params/{id}")
    @PreAuthorize("isAuthenticated()")
    public String diodeParams(@PathVariable("id") Long id, Model model) {

        model.addAttribute("diodeID", id);


        return "params-diode";

    }
    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/fetch/diode/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DiodeParamsResponseModel> showParams(@PathVariable("id") Long id) {
        DiodeServiceModel diodeSM = this.diodeService.findDiodeById(id);
        DiodeParamsResponseModel result = this.modelMapper.map(diodeSM, DiodeParamsResponseModel.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//----------------------------------------------------------------------------------------------------------------------
    @PostMapping("/diodes/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editConfirm(@Valid @ModelAttribute("diodeBindingModel") DiodeBindingModel diodeBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("diodeBindingModel", diodeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.diodeBindingModel", bindingResult);

            String redirect = String.format("redirect:/diodes/edit/%d", id);

            return redirect;

        } else {

        DiodeServiceModel diodeSM = this.modelMapper.map(diodeBindingModel, DiodeServiceModel.class);
        diodeSM.setId(id);

        this.diodeService.saveDiodeInDatabase(diodeSM);

        return "redirect:/all-diodes";
    }

}

//----------------------------------------------------------------------------------------------------------------------

    @GetMapping("/diodes/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDiode(Model model) {

        if(!model.containsAttribute("diodeBindingModel")){

            model.addAttribute("diodeBindingModel", new DiodeBindingModel());
        }

        return "add-diode";
    }

    @PostMapping("/diodes/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDiodeConfirm(@Valid @ModelAttribute("diodeBindingModel") DiodeBindingModel diodeBindingModel,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("diodeBindingModel", diodeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.diodeBindingModel", bindingResult);

            return "redirect:/diodes/add";

        } else {

            DiodeServiceModel diodeSM = this.modelMapper.map(diodeBindingModel, DiodeServiceModel.class);

            this.diodeService.saveDiodeInDatabase(diodeSM);

            return "redirect:/all-diodes";
        }


    }

}


