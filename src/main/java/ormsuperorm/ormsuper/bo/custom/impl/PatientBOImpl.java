package ormsuperorm.ormsuper.bo.custom.impl;

import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.PatientBO;
import ormsuperorm.ormsuper.dao.DAOFactory;
import ormsuperorm.ormsuper.dao.custom.PatientDAO;
import ormsuperorm.ormsuper.dao.custom.UserDAO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.dto.UserDTO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.Therapist;
import ormsuperorm.ormsuper.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);;

    @Override
    public boolean save(PatientDTO patientDTO) throws IOException, SQLException {
        Patient patient = new Patient(
                patientDTO.getPatId(),
                patientDTO.getPatName(),
                patientDTO.getPatAge(),
                patientDTO.getPatContact(),
                patientDTO.getMedicalHistory()
        );
        return patientDAO.save(patient);
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return patientDAO.getNextId();
    }

    @Override
    public List<PatientDTO> getAll() throws SQLException, IOException {

        List<Patient> patientList = patientDAO.getAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient patient : patientList) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatId(),
                    patient.getPatName(),
                    patient.getPatAge(),
                    patient.getPatContact(),
                    patient.getMedicalHistory()
            ));
        }
        return patientDTOS;
    }

    @Override
    public boolean update(PatientDTO patientDTO) throws IOException, SQLException {
            Patient patient = new Patient(
                    patientDTO.getPatId(),
                    patientDTO.getPatName(),
                    patientDTO.getPatAge(),
                    patientDTO.getPatContact(),
                    patientDTO.getMedicalHistory()
            );
            return patientDAO.update(patient);
    }

    @Override
    public boolean delete(String patientId) throws SQLException, IOException {
        return patientDAO.delete(patientId);
    }

    @Override
    public PatientDTO findById(String patientId) throws SQLException, IOException {
        Patient patient = patientDAO.findById(patientId);

        return new PatientDTO(
                patient.getPatId(),
                patient.getPatName(),
                patient.getPatAge(),
                patient.getPatContact(),
                patient.getMedicalHistory()
        );

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        List<Patient> patients = patientDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for (Patient patient : patients) {
            ids.add(patient.getPatId());
        }
        return ids;
    }
}
