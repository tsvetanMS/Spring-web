package softuni.emuseum.errorhandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User with such username all ready exist!")
public class UserAllReadyExistException extends RuntimeException {

    public UserAllReadyExistException(String message) {
        super(message);
    }

}
