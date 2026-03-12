package com.ted.app.basicASCIITheme;

import com.ted.app.UICore.Text;
import com.ted.app.UICore.TextThemeStrategy;

import java.util.Arrays;
import java.util.List;

public class TextBasicASCIITheme implements TextThemeStrategy {

    @Override
    public List<String> display(Text text) {
        return Arrays.asList(text.getText().split("\\R", -1));
    }
}
