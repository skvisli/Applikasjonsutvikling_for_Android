package com.example.oving3alt;

public class ExampleItem {
    private String textView_line1;
    private String textView_line2;

    public ExampleItem(String line1, String line2) {
        textView_line1 = line1;
        textView_line2 = "Bursdag: " + line2;
    }

    public void changeName(String text) {
        textView_line1 = text;
    }

    public void changeBirthday(String text) {
        textView_line2 = "Bursdag:" + text;
    }

    public String getTextView_line1() {
        return textView_line1;
    }

    public String getTextView_line2() {
        return textView_line2;
    }
}
