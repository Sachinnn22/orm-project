package ormsuperorm.ormsuper.bo;

import ormsuperorm.ormsuper.bo.custom.impl.PatientBOImpl;
import ormsuperorm.ormsuper.bo.custom.impl.TherapistBOImpl;
import ormsuperorm.ormsuper.bo.custom.impl.TherapyProgramBOImpl;
import ormsuperorm.ormsuper.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public  enum  BOTypes{
        THERAPY,THERAPYPROGRAMME,USER,PATIENT
    }

    public SuperBO getBo(BOFactory.BOTypes types){
        switch (types){
            case THERAPY :
                return (SuperBO) new TherapistBOImpl();
            case THERAPYPROGRAMME:
                return (SuperBO) new TherapyProgramBOImpl();
            case USER:
                return (SuperBO) new UserBOImpl();
            case PATIENT:
                return new PatientBOImpl();
            default:
                return null;
        }
    }
}
