package ormsuperorm.ormsuper.dao;

import ormsuperorm.ormsuper.dao.custom.impl.PatientDAOImpl;
import ormsuperorm.ormsuper.dao.custom.impl.TherapistDAOImpl;
import ormsuperorm.ormsuper.dao.custom.impl.TherapyProgramDAOImpl;
import ormsuperorm.ormsuper.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public  enum  DAOTypes{
        THERAPY,THERAPYPROGRAMME,USER,PATIENT
    }

    public SuperDAO getDAO(DAOFactory.DAOTypes types){
        switch (types){
            case THERAPY :
                return new TherapistDAOImpl();
            case THERAPYPROGRAMME:
                return new TherapyProgramDAOImpl();
            case USER:
                return new UserDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            default:
                return null;
        }
    }
}
