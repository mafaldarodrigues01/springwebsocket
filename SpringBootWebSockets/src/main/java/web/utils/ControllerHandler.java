package web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class ControllerHandler {
    public static <T> ResponseEntity<T> handleException(ThrowingSupplier<T> function, HttpStatusCode code) {
        try {
            T ret = function.get();
            return new ResponseEntity<>(ret, code);
        } catch (HttpResponse.UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserWeb hasn't been found", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
