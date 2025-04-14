package ormsuperorm.ormsuper.dao.custom.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.TherapySessionDAO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.TherapySession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = session
                .createQuery("SELECT s.sessionId FROM TherapySession s ORDER BY s.sessionId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        if (lastId != null) {
            int id = Integer.parseInt(lastId.replace("S", "")) + 1;
            return String.format("S%03d", id);
        } else {
            return "S001";
        }
    }

    @Override
    public List<TherapySession> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<TherapySession> list = session.createQuery("FROM TherapySession ", TherapySession.class).list();
        session.close();
        return list;
    }

    @Override
    public boolean save(TherapySession therapySession) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(therapySession);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapySession therapySession) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(therapySession);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String ID) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        TherapySession therapySession = session.get(TherapySession.class, ID);
        if (therapySession != null) {
            session.remove(therapySession);
            tx.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public TherapySession findById(String ID) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        TherapySession therapySession = session.get(TherapySession.class, ID);
        session.close();
        return therapySession;
    }
}
