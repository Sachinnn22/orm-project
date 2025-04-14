package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.custom.PaymentBO;
import ormsuperorm.ormsuper.dao.DAOFactory;
import ormsuperorm.ormsuper.dao.custom.PatientDAO;
import ormsuperorm.ormsuper.dao.custom.PaymentDAO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.PaymentDTO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);;

    @Override
    public boolean save(PaymentDTO paymentDTO) throws IOException, SQLException {
        Payment payment = new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getPaymentType(),
                paymentDTO.getPatientId(),
                paymentDTO.getConductor(),
                paymentDTO.getCash(),
                paymentDTO.getBalance()
        );
        return paymentDAO.save(payment);
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return paymentDAO.getNextId();
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, IOException {

        List<Payment> paymentList = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : paymentList) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getPayId(),
                    payment.getPaymentType(),
                    payment.getPatId(),
                    payment.getConductor(),
                    payment.getCash(),
                    payment.getBalance()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws IOException, SQLException {
        Payment payment = new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getPaymentType(),
                paymentDTO.getPatientId(),
                paymentDTO.getConductor(),
                paymentDTO.getCash(),
                paymentDTO.getBalance()
        );
        return paymentDAO.update(payment);
    }

    @Override
    public boolean delete(String paymentId) throws SQLException, IOException {
        return paymentDAO.delete(paymentId);
    }

    @Override
    public PaymentDTO findById(String paymentId) throws SQLException, IOException {
        Payment payment = paymentDAO.findById(paymentId);

        return new PaymentDTO(
                payment.getPayId(),
                payment.getPaymentType(),
                payment.getPatId(),
                payment.getConductor(),
                payment.getCash(),
                payment.getBalance()
        );
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for (Payment payment : payments) {
            ids.add(payment.getPatId());
        }
        return ids;
    }
}
