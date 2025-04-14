package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TherapySession")

public class TherapySession extends SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session-id")
    private String sessionId;

    @Column
    private String patId;

    @Column
    private String programmeId;

    @Column
    private String sessionDate;

    @Column
    private String note;
}
