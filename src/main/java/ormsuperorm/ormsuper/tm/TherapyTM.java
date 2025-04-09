package ormsuperorm.ormsuper.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TherapyTM {
    private String therapistId;
    private String therapistName;
    private String Profession;
    private String email;
}

