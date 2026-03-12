package com.ted.app.UICore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberedList extends Widget{
    private List<String> linesOfTexts;

    private NumberListThemeStrategy numberListThemeStrategy;

    private Point point;

    public NumberedList(List<String> linesOfTexts, NumberListThemeStrategy numberListThemeStrategy, Point point) {
        super(point);
        this.linesOfTexts = linesOfTexts;
        this.numberListThemeStrategy = numberListThemeStrategy;
        this.point = point;
    }

    @Override
    public void applyTheme(Theme theme) {
        numberListThemeStrategy = theme.getNumberListTheme();
    }

    @Override
    public List<String> display() {
        return numberListThemeStrategy.display(this);
    }

    //=====================================
    public List<String> getLinesOfTexts() {
        return Collections.unmodifiableList(linesOfTexts);
    }

    public List<String> display(NumberListThemeStrategy strategy) {
        return strategy.display(this);
    }

    public void setLinesOfTexts(List<String> linesOfTexts) {
        this.linesOfTexts = linesOfTexts;
    }

    public NumberListThemeStrategy getNumberListThemeStrategy() {
        return numberListThemeStrategy;
    }

    public void setNumberListThemeStrategy(NumberListThemeStrategy numberListThemeStrategy) {
        this.numberListThemeStrategy = numberListThemeStrategy;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
