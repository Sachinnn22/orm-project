package ormsuperorm.ormsuper.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapyProgramDTO {
    private String programmeId;
    private String programmeName;
    private double fee;
    private int duration;
    private String description;
}
