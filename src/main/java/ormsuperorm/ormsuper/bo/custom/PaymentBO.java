package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.PaymentDTO;
import ormsuperorm.ormsuper.dto.TherapistDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean save(PaymentDTO paymentDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<PaymentDTO> getAll() throws SQLException, IOException;
    public boolean update(PaymentDTO paymentDTO) throws IOException, SQLException;
    public boolean delete(String paymentId) throws SQLException, IOException;
    public PaymentDTO findById(String paymentId) throws SQLException, IOException;

    ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
}
