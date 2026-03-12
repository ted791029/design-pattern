package com.ted.app.UICore;

import java.util.List;

public class Text extends Widget{

    private String text;

    private TextThemeStrategy textThemeStrategy;

    public Text(String text, TextThemeStrategy textThemeStrategy, Point point) {
        super(point);
        this.text = text;
        this.textThemeStrategy = textThemeStrategy;
    }

    @Override
    public void applyTheme(Theme theme) {
        textThemeStrategy = theme.getTextTheme();
    }

    @Override
    public List<String> display() {
        return textThemeStrategy.display(this);
    }

    //==========================

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextThemeStrategy getTextThemeStrategy() {
        return textThemeStrategy;
    }

    public void setTextThemeStrategy(TextThemeStrategy textThemeStrategy) {
        this.textThemeStrategy = textThemeStrategy;
    }
}
