package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.custom.TherapistBO;
import ormsuperorm.ormsuper.dao.DAOFactory;
import ormsuperorm.ormsuper.dao.custom.TherapistDAO;
import ormsuperorm.ormsuper.dao.custom.TherapyProgrammeDAO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.entity.Therapist;
import ormsuperorm.ormsuper.entity.TherapyProgram;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY);
    TherapyProgrammeDAO therapyProgramDAO = (TherapyProgrammeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPYPROGRAMME);

    @Override
    public boolean save(TherapistDTO dto) throws SQLException, IOException {
        TherapyProgram program = therapyProgramDAO.findById(dto.getProgramId());

        Therapist therapist = new Therapist(
                dto.getTherapistId(),
                dto.getTherapistName(),
                dto.getProfession(),
                dto.getEmail(),
                program
        );
        return therapistDAO.save(therapist);
    }

    @Override
    public boolean update(TherapistDTO dto) throws SQLException, IOException {
        TherapyProgram program = therapyProgramDAO.findById(dto.getProgramId());

        Therapist therapist = new Therapist(
                dto.getTherapistId(),
                dto.getTherapistName(),
                dto.getProfession(),
                dto.getEmail(),
                program
        );
        return therapistDAO.update(therapist);
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException {
        return therapistDAO.delete(id);
    }

    @Override
    public TherapistDTO findById(String id) throws SQLException, IOException {
        Therapist therapist = therapistDAO.findById(id);

        return new TherapistDTO(
                therapist.getTherapistId(),
                therapist.getTherapistName(),
                therapist.getProfession(),
                therapist.getEmail(),
                therapist.getProgram().getProgrammeId()
        );
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        List<Therapist> therapists = therapistDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for (Therapist therapist : therapists) {
            ids.add(therapist.getTherapistId());
        }
        return ids;
    }

    @Override
    public List<TherapistDTO> getAll() throws SQLException, IOException {
        List<Therapist> therapists = therapistDAO.getAll();
        List<TherapistDTO> dtos = new ArrayList<>();

        for (Therapist t : therapists) {
            dtos.add(new TherapistDTO(
                    t.getTherapistId(),
                    t.getTherapistName(),
                    t.getProfession(),
                    t.getEmail(),
                    t.getProgram().getProgrammeId()
            ));
        }

        return dtos;
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapistDAO.getNextId();
    }
}
