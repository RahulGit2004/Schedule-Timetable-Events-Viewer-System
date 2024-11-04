package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BatchRequest {

    private String batchName;
    private String creatorName;
    private Date createdDate;
}
