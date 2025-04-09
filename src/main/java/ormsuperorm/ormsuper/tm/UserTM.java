package ormsuperorm.ormsuper.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserTM {
    private String userId;
    private String userName;
    private String userPassword;
}
