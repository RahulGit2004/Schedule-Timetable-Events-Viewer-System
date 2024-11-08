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
    private boolean inBatch;

    private List<Batch> batchList;
    public User(){
        this.batchList = new ArrayList<>();
    }

}
