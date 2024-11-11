package in.codingAge.scheduleSystems.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailId;
    private String userRole;
}
