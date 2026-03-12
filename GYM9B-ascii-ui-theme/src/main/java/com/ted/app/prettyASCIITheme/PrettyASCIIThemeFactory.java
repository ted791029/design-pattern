package com.ted.app.prettyASCIITheme;

import com.ted.app.UICore.AbstractThemeWidgetFactory;
import com.ted.app.UICore.ButtonThemeStrategy;
import com.ted.app.UICore.NumberListThemeStrategy;
import com.ted.app.UICore.TextThemeStrategy;

public class PrettyASCIIThemeFactory implements AbstractThemeWidgetFactory {

    @Override
    public ButtonThemeStrategy createButton() {
        return new ButtonPrettyASCIITheme();
    }

    @Override
    public NumberListThemeStrategy createNumberList() {
        return new NumberListPrettyASCIITheme();
    }

    @Override
    public TextThemeStrategy createText() {
        return new TextPrettyASCIITheme();
    }
}
