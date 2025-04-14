package ormsuperorm.ormsuper.dao.custom.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.PaymentDAO;
import ormsuperorm.ormsuper.entity.Patient;
import ormsuperorm.ormsuper.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = session
                .createQuery("SELECT p.payId FROM Payment p ORDER BY p.payId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        if (lastId != null) {
            int id = Integer.parseInt(lastId.replace("T", "")) + 1;
            return String.format("P%03d", id);
        } else {
            return "P001";
        }
    }

    @Override
    public List<Payment> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Payment> list = session.createQuery("FROM Payment ", Payment.class).list();
        session.close();
        return list;
    }

    @Override
    public boolean save(Payment payment) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(payment);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment payment) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(payment);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String ID) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Payment payment = session.get(Payment.class, ID);
        if (payment != null) {
            session.remove(payment);
            tx.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public Payment findById(String ID) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Payment payment = session.get(Payment.class, ID);
        session.close();
        return payment;
    }
}
