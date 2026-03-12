package com.ted.app.basicASCIITheme;

import com.ted.app.UICore.NumberListThemeStrategy;
import com.ted.app.UICore.NumberedList;
import java.util.ArrayList;
import java.util.List;

public class NumberListBasicASCIITheme implements NumberListThemeStrategy {

    @Override
    public List<String> display(NumberedList numberedList) {
        List<String> output = new ArrayList<>();
        List<String> lines = numberedList.getLinesOfTexts();
        for (int i = 0; i < lines.size(); i++) {
            output.add((i + 1) + ". " + lines.get(i));
        }
        return output;
    }
}
