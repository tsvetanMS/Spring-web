package softuni.emuseum.errorhandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import softuni.emuseum.errorhandling.errors.PageStatNotFoundException;
import softuni.emuseum.errorhandling.errors.UserActivityStatNotFoundException;

@ControllerAdvice
public class GlobalDBExceptionHandler {

    @ExceptionHandler({PageStatNotFoundException.class, UserActivityStatNotFoundException.class})
    public ModelAndView handleDBErrors(RuntimeException ex){

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());

        return modelAndView;
    }


}
