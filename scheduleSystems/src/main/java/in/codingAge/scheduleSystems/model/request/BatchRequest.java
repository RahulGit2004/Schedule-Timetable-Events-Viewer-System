package in.codingAge.scheduleSystems.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class BatchRequest {

    private String batchName;
    private String creatorId;
    private String creatorName;
    private String batchDescription;
    private LocalDate createdDate;
}
