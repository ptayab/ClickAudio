package com.clickaudioproject;

import javafx.scene.web.WebView;

public class JavaBridge {
    public void log(String text)
    {
        System.out.println(text);
    }

    public void startSpeechRecognition(WebView webView) {
        // Call the startSpeechRecognition function in JavaScript
        webView.getEngine().executeScript("startSpeechRecognition()");
    }

    public String OpenCommand() {
        return ("Netflix");
    }
}
