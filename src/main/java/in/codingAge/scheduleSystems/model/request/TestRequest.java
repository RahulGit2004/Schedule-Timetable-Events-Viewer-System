package in.codingAge.scheduleSystems.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TestRequest {

    private String batchId;
    private String testName;
    private String creatorName;
    private String testDuration;
    private String testDescription;
    private Date testDate;
    private Time testTime;

}
