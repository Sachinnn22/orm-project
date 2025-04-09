package ormsuperorm.ormsuper.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TherapistDTO {

    private String therapistId;
    private String therapistName;
    private String profession;
    private String email;
    private String programId;

    public TherapistDTO(String therapistId, String therapistName, String profession, String email) {
    }
}
