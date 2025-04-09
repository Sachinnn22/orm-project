package ormsuperorm.ormsuper.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TherapyProgrammeTM {
    private String therapyProgrammeId;
    private String therapyProgrammeName;
    private double fee;
    private int duration;
    private String description;
}
