package com.ted.app.UICore;

import java.util.List;

public class Button extends Widget{

    private ButtonThemeStrategy buttonThemeStrategy;
    private String text;
    private Padding padding;

    public Button(ButtonThemeStrategy buttonThemeStrategy, String text, Padding padding, Point point) {
        super(point);
        this.buttonThemeStrategy = buttonThemeStrategy;
        this.text = text;
        this.padding = padding;
    }

    @Override
    public void applyTheme(Theme theme) {
        buttonThemeStrategy = theme.getButtonTheme();
    }

    @Override
    public List<String> display() {
        return buttonThemeStrategy.display(this);
    }

    //===========================
    public ButtonThemeStrategy getButtonThemeStrategy() {
        return buttonThemeStrategy;
    }

    public void setButtonThemeStrategy(ButtonThemeStrategy buttonThemeStrategy) {
        this.buttonThemeStrategy = buttonThemeStrategy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }
}
