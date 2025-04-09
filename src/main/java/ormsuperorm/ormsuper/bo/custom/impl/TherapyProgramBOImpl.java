package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.custom.TherapyProgrammeBO;
import ormsuperorm.ormsuper.dao.DAOFactory;
import ormsuperorm.ormsuper.dao.custom.TherapyProgrammeDAO;
import ormsuperorm.ormsuper.dto.TherapyProgramDTO;
import ormsuperorm.ormsuper.entity.TherapyProgram;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgrammeBO {

    TherapyProgrammeDAO programDAO = (TherapyProgrammeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPYPROGRAMME);

    @Override
    public boolean save(TherapyProgramDTO dto) throws SQLException, IOException {
        TherapyProgram program = new TherapyProgram(
                dto.getProgrammeId(),
                dto.getProgrammeName(),
                dto.getFee(),
                dto.getDuration(),
                dto.getDescription(),
                null
        );
        return programDAO.save(program);
    }

    @Override
    public boolean update(TherapyProgramDTO dto) throws SQLException, IOException {
        TherapyProgram program = new TherapyProgram(
                dto.getProgrammeId(),
                dto.getProgrammeName(),
                dto.getFee(),
                dto.getDuration(),
                dto.getDescription(),
                null
        );
        return programDAO.update(program);
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException {
        return programDAO.delete(id);
    }

    @Override
    public TherapyProgramDTO findById(String id) throws SQLException, IOException {
        TherapyProgram program = programDAO.findById(id);
        return new TherapyProgramDTO(
                program.getProgrammeId(),
                program.getProgrammeName(),
                program.getFee(),
                program.getDuration(),
                program.getDescription()
        );
    }

    @Override
    public List<TherapyProgramDTO> getAll() throws SQLException, IOException {
        List<TherapyProgram> programs = programDAO.getAll();
        List<TherapyProgramDTO> dtos = new ArrayList<>();
        for (TherapyProgram p : programs) {
            dtos.add(new TherapyProgramDTO(
                    p.getProgrammeId(),
                    p.getProgrammeName(),
                    p.getFee(),
                    p.getDuration(),
                    p.getDescription()
            ));
        }
        return dtos;
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return programDAO.getNextId();
    }
}
