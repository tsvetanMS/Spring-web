package softuni.emuseum.errorhandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Page stat database is empty!")
public class PageStatNotFoundException extends RuntimeException{

    public PageStatNotFoundException(String message) {
        super(message);
    }
}
