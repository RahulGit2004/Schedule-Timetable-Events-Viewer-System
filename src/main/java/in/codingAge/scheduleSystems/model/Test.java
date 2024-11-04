package in.codingAge.scheduleSystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Document(collection = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    private String testId;

    private String batchId;
    private String testName;
    private String creatorName;
    private String testDuration;
    private String testDescription;
    private Date testDate;
    private Time testTime;


}
