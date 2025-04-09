package ormsuperorm.ormsuper.bo.custom;

import ormsuperorm.ormsuper.bo.SuperBO;
import ormsuperorm.ormsuper.dto.TherapyProgramDTO;
import ormsuperorm.ormsuper.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public boolean save(UserDTO userDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<UserDTO> getAll() throws SQLException, IOException;
    public boolean update(UserDTO userDTO) throws IOException, SQLException;
    public boolean delete(String ID) throws SQLException, IOException;
    public UserDTO findById(String userId) throws SQLException, IOException;
}
