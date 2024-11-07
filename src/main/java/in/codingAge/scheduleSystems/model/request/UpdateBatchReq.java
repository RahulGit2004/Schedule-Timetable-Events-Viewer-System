package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBatchReq {
    private String batchId;
    private String batchCreatorId;
    private String batchName;
}
