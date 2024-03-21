package com.clickaudioproject;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechToText {
    Voice voice;
    private int voiceRate = 150;
    private  int voicePitch = 120;
    private  int voiceVolume = 100;


    public SpeechToText() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            try {
                voice.setRate(voiceRate);
                voice.setPitch(voicePitch);
                voice.setVolume(voiceVolume);

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void sayText(String wordsToSay) {
        voice.setVolume(100);
        voice.speak(wordsToSay);
    }

    public void cancelText() {
        voice.setVolume(0);
        voice.deallocate();

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.setRate(voiceRate);
            voice.setPitch(voicePitch);
            voice.setVolume(voiceVolume);
        }
    }



}
