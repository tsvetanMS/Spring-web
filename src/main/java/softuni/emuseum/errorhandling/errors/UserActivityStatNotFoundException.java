package softuni.emuseum.errorhandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User activity stat database is empty!")
public class UserActivityStatNotFoundException extends RuntimeException{

    public UserActivityStatNotFoundException(String message) {
        super(message);
    }
}

