package ormsuperorm.ormsuper.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TherapySessionDTO {
    private String sessionId;
    private String patientId;
    private String programmeId;
    private String sessionDate;
    private String note;
}
