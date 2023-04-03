module mil.escudo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires opencv;

    opens mil.escudo to javafx.fxml;
    exports mil.escudo;
}