package com.clickaudioproject;

public class Phrases {

    String welcomMessage;
    String instructions;

    public Phrases() {
        welcomMessage = "Welcome to Click Audio! Please listen carefully to the following instructions";

        instructions = "" +
                "First, You have a Frequency List available to you at the bottom of the screen which tracks top 5 most used apps\n" +
                "\n" +
                "If it is not empty and you hover over it, it will read the file name.\n" +
                "Second, you can use Keyboard Inputs\n" +
                "\n" +
                "Use digits 1-5 to select app.   " +
                "Press \"L\" to hear the list.    " +
                "\"Esc\" closes tabs.\n" +
                "\"O\" + digit opens apps.\n" +
                "Third, you can use Text Input:\n" +
                "\n" +
                "Click or right-click to activate.\n" +
                "Each keystroke spoken.\n" +
                "Commands:\n" +
                "\"Open filename\n"+
                "\"Close  filename>\n" +
                "\"Reset\" Clears the frequency list.\n" +
                "\"Delete filename\": Removes the specific file in the list\n" +
                "Finally, I help you withLocation Awareness:\n" +
                "\n" +
                "Hovering icon announces its name.\n" +
                "Opening file prompts \"Opening <file_name>\".\n" +
                "Right-click activates input.\n" +
                "Hovering window announces its name.";
    }


}
