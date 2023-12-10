package web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpResponse {


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "UserWeb Not Found")
    public static class UserNotFound extends Exception {
        public UserNotFound() {
            super("UserWeb Not Found");
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid input")
    public static class InvalidInput extends Exception {
        public InvalidInput() {
            super("Invalid input");
        }
    }


}
