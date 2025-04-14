package ormsuperorm.ormsuper.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class PaymentDTO {
    private String paymentId;
    private String paymentType;
    private String patientId;
    private String conductor;
    private double cash;
    private double balance;
}
