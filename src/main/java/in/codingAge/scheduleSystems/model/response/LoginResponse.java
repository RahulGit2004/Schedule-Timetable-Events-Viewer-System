package in.codingAge.scheduleSystems.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String loggedId;
    private String loggedRole;
    private String loggedUserName;
}
