package ormsuperorm.ormsuper.dao.custom.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormsuperorm.ormsuper.configue.FactoryConfiguration;
import ormsuperorm.ormsuper.dao.custom.UserDAO;
import ormsuperorm.ormsuper.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean save(User user) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
        return true;
    }


    public boolean delete(String userId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.remove(user);
            transaction.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    public User findById(String userId) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = session.get(User.class, userId);
        session.close();
        return user;
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        String lastId = session
                .createQuery("SELECT u.id FROM User u ORDER BY u.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        session.close();

        if (lastId != null) {
            int id = Integer.parseInt(lastId.replace("U", "")) + 1;
            return String.format("U%03d", id);
        } else {
            return "U001";
        }
    }

    public List<User> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User", User.class);
        List<User> userList = query.list();
        transaction.commit();
        session.close();
        return userList;
    }


}
