package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patient")

public class Patient implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient-id")
    private String patId;

    @Column
    private String patName;

    @Column
    private int patAge;

    @Column
    private int patContact;

    @Column
    private String medicalHistory;
}
