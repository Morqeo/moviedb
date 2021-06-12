open module pl.adrianherdzina {
    requires javafx.controls;
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires com.fasterxml.classmate;
    requires java.net.http;
    requires org.hibernate.orm.core;
    requires mysql.connector.java;
    requires java.sql;
    requires net.bytebuddy;
    requires java.xml.bind;
    requires java.persistence;
    exports pl.adrianherdzina;
}
