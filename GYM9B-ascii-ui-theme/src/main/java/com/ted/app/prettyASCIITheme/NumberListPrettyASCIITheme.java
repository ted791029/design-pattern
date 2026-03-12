package com.ted.app.prettyASCIITheme;

import com.ted.app.UICore.NumberListThemeStrategy;
import com.ted.app.UICore.NumberedList;
import java.util.ArrayList;
import java.util.List;

public class NumberListPrettyASCIITheme implements NumberListThemeStrategy {

    @Override
    public List<String> display(NumberedList numberedList) {
        List<String> output = new ArrayList<>();
        List<String> lines = numberedList.getLinesOfTexts();
        for (int i = 0; i < lines.size(); i++) {
            output.add(toRoman(i + 1) + ". " + lines.get(i));
        }
        return output;
    }

    private String toRoman(int value) {
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        int remaining = value;
        for (int i = 0; i < numbers.length; i++) {
            while (remaining >= numbers[i]) {
                remaining -= numbers[i];
                roman.append(symbols[i]);
            }
        }
        return roman.toString();
    }
}
