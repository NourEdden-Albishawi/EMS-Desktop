module me.nouredden.ems.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires jdatepicker;
    requires trident;
    requires com.formdev.flatlaf;

    opens me.nouredden.ems to javafx.fxml;
    exports me.nouredden.ems;
}