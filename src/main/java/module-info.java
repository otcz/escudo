module mil.escudo {
    requires javafx.controls;
    requires javafx.fxml;


    opens mil.escudo to javafx.fxml;
    exports mil.escudo;
}