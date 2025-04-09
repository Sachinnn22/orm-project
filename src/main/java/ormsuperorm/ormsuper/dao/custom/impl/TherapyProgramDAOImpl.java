package ormsuperorm.ormsuper.dao.custom.impl;

import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.TherapyProgrammeDAO;
import ormsuperorm.ormsuper.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgrammeDAO {

    @Override
    public boolean save(TherapyProgram program) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(program);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapyProgram program) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(program);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String programId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        TherapyProgram program = session.get(TherapyProgram.class, programId);
        if (program != null) {
            session.remove(program);
            tx.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public TherapyProgram findById(String programId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        TherapyProgram program = session.get(TherapyProgram.class, programId);
        session.close();
        return program;
    }

    @Override
    public List<TherapyProgram> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<TherapyProgram> list = session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        session.close();
        return list;
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = session
                .createQuery("SELECT t.programmeId FROM TherapyProgram t ORDER BY t.programmeId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        if (lastId != null) {
            int id = Integer.parseInt(lastId.replace("P", "")) + 1;
            return String.format("P%03d", id);
        } else {
            return "P001";
        }
    }
}
