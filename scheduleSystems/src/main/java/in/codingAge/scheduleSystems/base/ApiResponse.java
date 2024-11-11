package in.codingAge.scheduleSystems.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {

    private T data;
    private HttpStatus status;
    private String errorMessage="";

    public ApiResponse(HttpStatus status, String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }
    public ApiResponse(HttpStatus status){
        this.status= status;
    }

    public ApiResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }
}
