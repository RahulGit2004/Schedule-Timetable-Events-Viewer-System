package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoveBatchRequest {
    private String batchId;
    private String creatorId;
    private List<String> studentsId;
}
