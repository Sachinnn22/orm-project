package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.UserBO;
import ormsuperorm.ormsuper.dao.custom.UserDAO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.dto.UserDTO;
import ormsuperorm.ormsuper.entity.Therapist;
import ormsuperorm.ormsuper.entity.TherapyProgram;
import ormsuperorm.ormsuper.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    @Override
    public boolean save(UserDTO userDTO) throws IOException, SQLException {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getUserPassword(),
                userDTO.getRole()
        );
        return userDAO.save(user);
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return userDAO.getNextId();
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, IOException {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList) {
            userDTOS.add(new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getUserPassword(),
                    user.getRole()
            ));
        }

        return userDTOS;
    }

    @Override
    public boolean update(UserDTO userDTO) throws IOException, SQLException {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getUserPassword(),
                userDTO.getRole()
        );
        return userDAO.update(user);
    }

    @Override
    public boolean delete(String ID) throws SQLException, IOException {
        return userDAO.delete(ID);
    }

    @Override
    public UserDTO findById(String userId) throws SQLException, IOException {
        User user = userDAO.findById(userId);

        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getRole()
        );
    }
}
