package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.dto.TherapySessionDTO;
import ormsuperorm.ormsuper.entity.TherapySession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapySessionBO extends SuperBO {

    public boolean save(TherapySessionDTO therapySessionDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<TherapySessionDTO > getAll() throws SQLException, IOException;
    public boolean update(TherapySessionDTO therapySessionDTO) throws IOException, SQLException;
    public boolean delete(String ID) throws SQLException, IOException;
    public TherapySessionDTO  findById(String therapySessionId) throws SQLException, IOException;

    ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
}
