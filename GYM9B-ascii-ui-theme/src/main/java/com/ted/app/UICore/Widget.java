package com.ted.app.UICore;

import java.util.List;

public abstract class Widget {
    private Point point;

    public Widget(Point point) {
        this.point = point;
    }

    public abstract void applyTheme(Theme theme);

    public abstract List<String> display();

    //=======================
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
