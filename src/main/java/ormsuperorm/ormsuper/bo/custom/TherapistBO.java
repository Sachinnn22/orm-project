package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.TherapistDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO {

    public boolean save(TherapistDTO therapistDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<TherapistDTO> getAll() throws SQLException, IOException;
    public boolean update(TherapistDTO therapistDTO) throws IOException, SQLException;
    public boolean delete(String ID) throws SQLException, IOException;
    public TherapistDTO findById(String therapyId) throws SQLException, IOException;

    ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
}
