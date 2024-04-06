package com.clickaudioproject;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Icon {
    private double x, y;
    private int id;
    private String appName;
    private String fileName;
    private String shortName;
    private String lowerName;
    private Image image;
    private ImageView imageView;
    private boolean isSelected;
    private double iconWidth;
    private double iconHeight;
    private int frequencyCount;
    VBox displayIcon = new VBox();
    boolean isMouseOver;
    TextToSpeech textToSpeech;
    private PauseTransition hoverTransition;
    private Stage appStage;

    public Icon(String newFileName, String newAppName, String newImagePath, TextToSpeech newTxtToSpeech) {
        iconWidth = 35;
        iconHeight = 35;
        fileName = newFileName;
        appName = newAppName;
        image = new Image(newImagePath);
        isSelected = false;
        frequencyCount = 0;
        textToSpeech = newTxtToSpeech;
        lowerName = appName.toLowerCase();
        appStage = new Stage();
        if (appName.length() > 8) {
            shortName = appName.substring(0, 7);
        } else {
            shortName = appName;
        }

        hoverTransition = new PauseTransition(Duration.seconds(1.5));
        hoverTransition.setOnFinished(event -> {
            if (isMouseOver) {
                textToSpeech.sayText(appName);
            }
        });

    }


    public void setSelected(boolean val) {
        isSelected = val;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Image getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAppName() {
        return appName;
    }

    public int getFrequencyCount() {
        return frequencyCount;
    }
    public void setFrequencyCount(Integer newFrequencyCount) {
        frequencyCount = newFrequencyCount;
    }

    public ImageView getImageView() {
        imageView = new ImageView(image);
        imageView.setFitWidth(iconWidth);
        imageView.setFitHeight(iconHeight);
        return imageView;

    }

    public Text getDisplayName() {
        Text displayName = new Text(shortName);
        displayName.setFont(new Font(10));
        return displayName;
    }

    public VBox displayIcon() {

        displayIcon.setAlignment(Pos.CENTER);
        displayIcon.getChildren().addAll(getImageView(), getDisplayName());
        displayIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, this::isClicked);
//        displayIcon.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
//
////            System.out.println("Mouse entered " + currentIcon.getAppName());
//            // You can perform further actions here when the mouse enters an icon
//        });
//        displayIcon.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
//                    System.out.println("Mouse exited " + currentIcon.getAppName());
//
//                });
//            // You can perform further actions here when the mouse exits a
        isMouseOver();

        return displayIcon;
    }

    private void isClicked(MouseEvent event) {
        openAppWindow();
        frequencyCount++;
    }

    public void isMouseOver(){
        displayIcon.setOnMouseEntered(event -> {
            hoverTransition.playFromStart();

            isMouseOver = true;
        });
        displayIcon.setOnMouseExited(event -> {
            hoverTransition.stop();
            textToSpeech.cancelText();
            isMouseOver = false;
        });
    }

    public void commandBoard(String firstWord, String words) {
        String lowerAppName = appName.toLowerCase();

        switch (firstWord.toLowerCase()) {
            case "open":
                if (lowerAppName .contains(words)) {
                    frequencyCount++;
                    openAppWindow();
                    System.out.println("Opening...");
                } else {
                    System.out.println("File Name does not exist");
                }
                break;
            case "close":
                if (lowerAppName .contains(words)) {
                    appStage.close();
                    System.out.println("Opening...");
                } else {
                    System.out.println("File Name does not exist");
                }
                System.out.println("Closing...");
                break;
            case "remove":
                // Do something when the command starts with "remove"
                System.out.println("Removing...");
                break;
            default:
                // Handle unrecognized commands
                System.out.println("Unrecognized command.");
                break;
        }
    }



    private void openAppWindow() {


        // Create the root layout for the new window
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(80);

        root.getChildren().add(imageView);

        // Add content to the new window
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> appStage.close());
        root.getChildren().add(closeButton);

        // Create the scene for the new window
        Scene scene = new Scene(root, 600, 300);

        // Set the scene on the new stage
        appStage.setScene(scene);
        appStage.setTitle(appName);

        // Show the new stage
        appStage.show();
    }
}
