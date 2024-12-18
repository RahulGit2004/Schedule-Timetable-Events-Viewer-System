package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    private String userId;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailId;
    private String userRole;
    private List<String> batchesIdList;
    private List<Notification> notificationList;

    public User() {
        this.notificationList =new ArrayList<>();
        this.batchesIdList =new ArrayList<>();
    }



}
