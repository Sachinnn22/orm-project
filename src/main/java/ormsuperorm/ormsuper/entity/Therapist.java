package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {
    @Id
    @Column(name = "therapist_id")
    private String therapistId;

    @Column(nullable = false)
    private String therapistName;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram program;
}
