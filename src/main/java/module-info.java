module ormsuperorm.ormsuper {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens ormsuperorm.ormsuper.entity to org.hibernate.orm.core;
    opens ormsuperorm.ormsuper.configue to jakarta.persistence;


    opens ormsuperorm.ormsuper.controller to javafx.fxml;
    opens ormsuperorm.ormsuper.tm to javafx.base;
    opens ormsuperorm.ormsuper.dto to javafx.base;
    exports ormsuperorm.ormsuper;
}