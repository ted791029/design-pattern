package com.ted.app.prettyASCIITheme;

import com.ted.app.UICore.Text;
import com.ted.app.UICore.TextThemeStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TextPrettyASCIITheme implements TextThemeStrategy {

    @Override
    public List<String> display(Text text) {
        return Arrays.asList(text.getText().toUpperCase(Locale.ROOT).split("\\R", -1));
    }
}
