package ormsuperorm.ormsuper.configue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ormsuperorm.ormsuper.entity.*;


public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Therapist.class).addAnnotatedClass(TherapyProgram.class).addAnnotatedClass(User.class).addAnnotatedClass(Patient.class).addAnnotatedClass(Payment.class).addAnnotatedClass(TherapySession.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ?
                factoryConfiguration =
                        new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
