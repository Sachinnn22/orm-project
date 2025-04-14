package ormsuperorm.ormsuper.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentTM {
    private String paymentId;
    private String paymentType;
    private String patientId;
    private String conductor;
    private double cash;
    private double balance;
}
