package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.TherapyProgrammeBO;
import ormsuperorm.ormsuper.bo.custom.TherapySessionBO;
import ormsuperorm.ormsuper.dao.custom.TherapySessionDAO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.PaymentDTO;
import ormsuperorm.ormsuper.dto.TherapySessionDTO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.Payment;
import ormsuperorm.ormsuper.entity.Therapist;
import ormsuperorm.ormsuper.entity.TherapySession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.THERAPYSESSION);

    @Override
    public boolean save(TherapySessionDTO therapySessionDTO) throws IOException, SQLException {
        TherapySession therapySession = new TherapySession(
                therapySessionDTO.getSessionId(),
                therapySessionDTO.getPatientId(),
                therapySessionDTO.getProgrammeId(),
                therapySessionDTO.getSessionDate(),
                therapySessionDTO.getNote()
        );
        return therapySessionDAO.save(therapySession);
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapySessionDAO.getNextId();
    }

    @Override
    public List<TherapySessionDTO> getAll() throws SQLException, IOException {
        List<TherapySession> therapySessionList = therapySessionDAO.getAll();
        List<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();

        for (TherapySession therapySession : therapySessionList) {
            therapySessionDTOS.add(new TherapySessionDTO(
                    therapySession.getSessionId(),
                    therapySession.getPatId(),
                    therapySession.getProgrammeId(),
                    therapySession.getSessionDate(),
                    therapySession.getNote()
            ));
        }
        return therapySessionDTOS;
    }

    @Override
    public boolean update(TherapySessionDTO therapySessionDTO) throws IOException, SQLException {
        TherapySession therapySession = new TherapySession(
                therapySessionDTO.getSessionId(),
                therapySessionDTO.getPatientId(),
                therapySessionDTO.getProgrammeId(),
                therapySessionDTO.getSessionDate(),
                therapySessionDTO.getNote()
        );
        return therapySessionDAO.update(therapySession);
    }

    @Override
    public boolean delete(String ID) throws SQLException, IOException {
        return therapySessionDAO.delete(ID);
    }

    @Override
    public TherapySessionDTO findById(String therapySessionId) throws SQLException, IOException {
        TherapySession therapySession = therapySessionDAO.findById(therapySessionId);

        return new TherapySessionDTO(
                therapySession.getSessionId(),
                therapySession.getPatId(),
                therapySession.getProgrammeId(),
                therapySession.getSessionDate(),
                therapySession.getNote()
        );
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        List<TherapySession> therapySessions = therapySessionDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for (TherapySession therapySession: therapySessions) {
            ids.add(therapySession.getSessionId());
        }
        return ids;
    }
}
