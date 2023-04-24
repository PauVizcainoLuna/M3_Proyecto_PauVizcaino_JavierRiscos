module com.mycompany.m3proyecto_javierriscos_pauvizcaino {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.m3proyecto_javierriscos_pauvizcaino to javafx.fxml;
    exports com.mycompany.m3proyecto_javierriscos_pauvizcaino;
}
