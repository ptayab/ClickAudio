module com.example.clickaudioproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
    requires javafx.web;


    opens com.clickaudioproject to javafx.fxml;
    exports com.clickaudioproject;
}