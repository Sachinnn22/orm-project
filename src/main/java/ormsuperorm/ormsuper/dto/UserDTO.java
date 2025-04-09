package ormsuperorm.ormsuper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private String userId;
    private String userName;
    private String userPassword;
}
