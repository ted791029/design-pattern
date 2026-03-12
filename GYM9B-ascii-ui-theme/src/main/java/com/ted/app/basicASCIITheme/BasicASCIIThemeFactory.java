package com.ted.app.basicASCIITheme;

import com.ted.app.UICore.AbstractThemeWidgetFactory;
import com.ted.app.UICore.ButtonThemeStrategy;
import com.ted.app.UICore.NumberListThemeStrategy;
import com.ted.app.UICore.TextThemeStrategy;

public class BasicASCIIThemeFactory implements AbstractThemeWidgetFactory {

    @Override
    public ButtonThemeStrategy createButton() {
        return new ButtonBasicASCIITheme();
    }

    @Override
    public NumberListThemeStrategy createNumberList() {
        return new NumberListBasicASCIITheme();
    }

    @Override
    public TextThemeStrategy createText() {
        return new TextBasicASCIITheme();
    }
}
