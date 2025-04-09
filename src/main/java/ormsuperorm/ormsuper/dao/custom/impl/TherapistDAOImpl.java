package ormsuperorm.ormsuper.dao.custom.impl;

import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.TherapistDAO;
import ormsuperorm.ormsuper.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {

    @Override
    public boolean save(Therapist therapist) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(therapist);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Therapist therapist) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(therapist);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String therapistId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Therapist therapist = session.get(Therapist.class, therapistId);
        if (therapist != null) {
            session.remove(therapist);
            tx.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public Therapist findById(String therapistId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Therapist therapist = session.get(Therapist.class, therapistId);
        session.close();
        return therapist;
    }

    @Override
    public List<Therapist> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> list = session.createQuery("FROM Therapist", Therapist.class).list();
        session.close();
        return list;
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = session
                .createQuery("SELECT t.therapistId FROM Therapist t ORDER BY t.therapistId DESC", String.class)
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
}
