package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignRequest {
    private List<String> studentId;
    private String creatorId;
    private String batchId;
}
