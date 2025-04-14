package ormsuperorm.ormsuper.entity;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payment")

public class Payment implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment-id")
    private String payId;

    @Column
    private String paymentType;

    @Column
    private String patId;

    @Column
    private String conductor;

    @Column
    private double cash;

    @Column
    private double balance;
}
