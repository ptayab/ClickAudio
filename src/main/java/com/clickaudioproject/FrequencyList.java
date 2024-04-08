package com.clickaudioproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class FrequencyList {
    ArrayList<Icon> icons;
    ArrayList<Icon> topFiveIcons = new ArrayList<>();
    TextToSpeech textToSpeech;

    public FrequencyList (ArrayList<Icon> newIcons) {
        icons = newIcons;
        textToSpeech = new TextToSpeech();
    }

    public void Order() {
        ArrayList<Integer> frequencyVals = new ArrayList<>();
        for(Icon icon: icons) {
            if (icon.getFrequencyCount() != 0 && !topFiveIcons.contains(icon)) {
                frequencyVals.add(icon.getFrequencyCount());
                if (topFiveIcons.size() < 5) {
                    topFiveIcons.add(icon);
                } else {
                    for (Icon topIcon : topFiveIcons) {
                        if (icon.getFrequencyCount() > topIcon.getFrequencyCount()) {
                            topFiveIcons.add(icon);
                            topFiveIcons.remove(topIcon);
                            break;
                        }
                    }
                }
            }
        }
    }




    public HBox display() {
        Order();

        HBox frequencyListDisplay = new HBox();
        frequencyListDisplay.setAlignment(Pos.CENTER);

        // Add icon names to columns
        int iconIndex = 0;
        for (int col = 0; col < 5; col++) {
            if (iconIndex < topFiveIcons.size()) {
                HBox topIcon = new HBox();
                callIfOver(topIcon, topFiveIcons.get(iconIndex));
                topIcon.setPadding(new Insets(10,90,10,90));
                topIcon.setBorder(new Border(new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));

            topIcon.getChildren().add(topFiveIcons.get(iconIndex).getDisplayName());
                frequencyListDisplay.getChildren().add(topIcon);
                iconIndex++;
            } else {
                break;
            }
        }

        return frequencyListDisplay;
    }

    private void callIfOver(HBox topIcon, Icon icon) {
        topIcon.setOnMouseEntered(event -> {
            textToSpeech.sayText(icon.getAppName());
        });
        topIcon.setOnMouseExited(event -> {
            textToSpeech.cancelText();
        });
    }


    public void frequencyCommandBoard(String firstWord, String words) {

        firstWord.toLowerCase();
        if(firstWord.equals("read")) {
            for (Icon topIcon: topFiveIcons) {
                textToSpeech.sayText(topIcon.getAppName());
            }
        } else if (firstWord.equals("reset")) {
            for (Icon icon: icons) {
                icon.setFrequencyCount(0);
            }
            topFiveIcons.clear();
        } else if (firstWord.equals("delete")) {
            for (Icon topIcon: topFiveIcons) {
                String lowerAppName = topIcon.getAppName().toLowerCase();
                    if (lowerAppName.contains(words)) {
                        topIcon.setFrequencyCount(0);
                        topFiveIcons.remove(topIcon);
                    } else {
                        System.out.println("File Name does not exist");
                    }
                    System.out.println("Removing...");
            }
        }
    }





}
