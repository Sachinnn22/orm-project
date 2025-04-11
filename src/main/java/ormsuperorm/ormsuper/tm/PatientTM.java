package ormsuperorm.ormsuper.tm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientTM {
    private String patId;
    private String patName;
    private int patAge;
    private int patContact;
    private String medicalHistory;

}
