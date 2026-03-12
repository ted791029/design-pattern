package com.ted.app.prettyASCIITheme;

import com.ted.app.UICore.Button;
import com.ted.app.UICore.ButtonThemeStrategy;
import com.ted.app.UICore.Padding;
import com.ted.app.basicASCIITheme.ButtonBasicASCIITheme;

import java.util.ArrayList;
import java.util.List;

public class ButtonPrettyASCIITheme implements ButtonThemeStrategy {

    @Override
    public List<String> display(Button button) {
        return buildButton(button, '┌', '┐', '└', '┘', '─', '│');
    }

    private List<String> buildButton(Button button, char topLeft, char topRight, char bottomLeft,
                                     char bottomRight, char horizontal, char vertical) {
        Padding padding = button.getPadding();
        int contentWidth = padding.width() * 2 + button.getText().length();
        String border = topLeft + String.valueOf(horizontal).repeat(contentWidth) + topRight;
        String bottom = bottomLeft + String.valueOf(horizontal).repeat(contentWidth) + bottomRight;
        String empty = vertical + " ".repeat(contentWidth) + vertical;
        String textLine = vertical + " ".repeat(padding.width()) + button.getText()
                + " ".repeat(padding.width()) + vertical;

        List<String> lines = new ArrayList<>();
        lines.add(border);
        for (int i = 0; i < padding.height(); i++) {
            lines.add(empty);
        }
        lines.add(textLine);
        for (int i = 0; i < padding.height(); i++) {
            lines.add(empty);
        }
        lines.add(bottom);
        return lines;
    }
}
