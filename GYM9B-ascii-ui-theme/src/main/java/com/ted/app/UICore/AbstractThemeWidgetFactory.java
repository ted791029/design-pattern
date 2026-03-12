package com.ted.app.UICore;

public interface AbstractThemeWidgetFactory {

    ButtonThemeStrategy createButton();

    NumberListThemeStrategy createNumberList();

    TextThemeStrategy createText();
}
