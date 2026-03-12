package com.ted.app;

import com.ted.app.UICore.ASCIIUI;
import com.ted.app.UICore.AbstractThemeWidgetFactory;
import com.ted.app.UICore.Padding;
import com.ted.app.prettyASCIITheme.PrettyASCIIThemeFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ASCIIUI asciiui = new ASCIIUI(13, 22);
        asciiui.addButton(3, 1, "Hi, I miss u", new Padding(1, 0));
        asciiui.addButton(3, 6, "No", new Padding(1, 0));
        asciiui.addButton(12, 6, "Yes", new Padding(1, 0));
        asciiui.addText(4, 4, "Do u love me ?\nPlease tell...");
        asciiui.addNumberedList(3, 9, List.of("Let's Travel", "Back to home", "Have dinner"));
        System.out.println(asciiui.display());
        System.out.println("========================================");
        AbstractThemeWidgetFactory factory = new PrettyASCIIThemeFactory();
        asciiui.setAsciiTheme(factory);
        System.out.println(asciiui.display());
    }
}
