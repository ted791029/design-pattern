package com.ted.app.UICore;

public class Theme {
    private AbstractThemeWidgetFactory factory;

    public Theme(AbstractThemeWidgetFactory factory) {
        this.factory = factory;
    }

    public ButtonThemeStrategy getButtonTheme(){
        return factory.createButton();
    }

    public NumberListThemeStrategy getNumberListTheme(){
        return factory.createNumberList();
    }

    public TextThemeStrategy getTextTheme(){
        return factory.createText();
    }

    //=======================

    public AbstractThemeWidgetFactory getFactory() {
        return factory;
    }

    public void setFactory(AbstractThemeWidgetFactory factory) {
        this.factory = factory;
    }
}
