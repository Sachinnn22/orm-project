package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "therapy_program")
public class TherapyProgram implements SuperEntity {
    @Id
    @Column(name = "program_id")
    private String programmeId;

    @Column(nullable = false)
    private String programmeName;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    private int duration;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Therapist> therapists;
}
