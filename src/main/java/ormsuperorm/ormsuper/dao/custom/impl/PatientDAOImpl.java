package ormsuperorm.ormsuper.dao.custom.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.PatientDAO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.Therapist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = session
                .createQuery("SELECT p.patId FROM Patient p ORDER BY p.patId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        if (lastId != null) {
            int id = Integer.parseInt(lastId.replace("T", "")) + 1;
            return String.format("T%03d", id);
        } else {
            return "T001";
        }
    }

    @Override
    public List<Patient> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patient> list = session.createQuery("FROM Patient ", Patient.class).list();
        session.close();
        return list;
    }

    @Override
    public boolean save(Patient patient) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(patient);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(patient);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String patientId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Patient patient = session.get(Patient.class, patientId);
        if (patient != null) {
            session.remove(patient);
            tx.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public Patient findById(String patientId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Patient patient = session.get(Patient.class, patientId);
        session.close();
        return patient;
    }
}
