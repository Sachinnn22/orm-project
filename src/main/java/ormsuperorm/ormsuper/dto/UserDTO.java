package ormsuperorm.ormsuper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {

    private String userId;
    private String userName;
    private String userPassword;
    private String role;
}
