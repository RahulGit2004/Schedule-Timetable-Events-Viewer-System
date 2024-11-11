package in.codingAge.scheduleSystems.exception;

import org.springframework.context.ApplicationContextException;

public class AppException extends ApplicationContextException {
    public AppException(String message){
        super(message);
    }
}
