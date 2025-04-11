package ormsuperorm.ormsuper.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientDTO {
    private String patId;
    private String patName;
    private int patAge;
    private int patContact;
    private String medicalHistory;
}
