module com.example.journi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.logging;

    opens com.example.journi to javafx.fxml;
    exports com.example.journi;
    exports com.example.journi.planner.view;
    opens com.example.journi.planner.view to javafx.fxml;
    exports com.example.journi.planner.model;
    opens com.example.journi.planner.model to javafx.fxml;
    exports com.example.journi.loginSystem.view;
    opens com.example.journi.loginSystem.view to javafx.fxml;
}