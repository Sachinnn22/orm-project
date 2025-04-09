package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")

public class User implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user-id")
    private String userId;

    @Column
    private String userName;

    @Column
    private String userPassword;

}
