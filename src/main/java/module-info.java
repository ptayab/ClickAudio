module com.example.clickaudioproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
//    requires edu.cmu.sphinx.api;
    requires javafx.web;
    requires jdk.jsobject;


    opens com.clickaudioproject to javafx.fxml;
    exports com.clickaudioproject;
}