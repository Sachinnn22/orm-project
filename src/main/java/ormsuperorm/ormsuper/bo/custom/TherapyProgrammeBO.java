package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.dto.TherapyProgramDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TherapyProgrammeBO extends SuperBO {
    public boolean save(TherapyProgramDTO therapyProgramDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<TherapyProgramDTO> getAll() throws SQLException, IOException;
    public boolean update(TherapyProgramDTO therapyProgramDTO) throws IOException, SQLException;
    public boolean delete(String ID) throws SQLException, IOException;
    public TherapyProgramDTO findById(String therapyProgrammeId) throws SQLException, IOException;
}
