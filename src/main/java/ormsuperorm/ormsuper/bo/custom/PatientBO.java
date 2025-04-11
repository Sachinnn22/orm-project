package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.TherapistDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBO extends SuperBO {

    public boolean save(PatientDTO patientDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<PatientDTO> getAll() throws SQLException, IOException;
    public boolean update(PatientDTO patientDTO) throws IOException, SQLException;
    public boolean delete(String patientId) throws SQLException, IOException;
    public TherapistDTO findById(String patientId) throws SQLException, IOException;

    ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
}
