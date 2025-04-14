package ormsuperorm.ormsuper.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TherapySessionTM {
    private String sessionId;
    private String patientId;
    private String programmeId;
    private String sessionDate;
    private String note;
}
