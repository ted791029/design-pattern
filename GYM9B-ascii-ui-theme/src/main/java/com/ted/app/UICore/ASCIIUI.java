package com.ted.app.UICore;

import com.ted.app.basicASCIITheme.BasicASCIIThemeFactory;

import java.util.ArrayList;
import java.util.List;

public class ASCIIUI {

    private int height;
    private int width;
    private Theme theme = new Theme(new BasicASCIIThemeFactory());

    private final List<Widget> widgets = new ArrayList<>();

    public ASCIIUI(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void addButton(int x, int y, String text, Padding padding) {
        Button button = new Button(theme.getButtonTheme(), text, padding, new Point(x, y));
        widgets.add(button);
    }

    public void addNumberedList(int x, int y, List<String> linesOfTexts) {
        NumberedList numberedList = new NumberedList(linesOfTexts, theme.getNumberListTheme(), new Point(x, y));
        widgets.add(numberedList);
    }

    public void addText(int x, int y, String text) {
        Text uiText = new Text(text, theme.getTextTheme(), new Point(x, y));
        widgets.add(uiText);
    }

    public String display() {
        char[][] canvas = createCanvas();
        drawFrame(canvas);
        for (Widget widget : widgets) {
            paint(canvas,widget.getPoint(), widget.display());
        }
        return toDisplayText(canvas);
    }

    public void setAsciiTheme(AbstractThemeWidgetFactory factory){
        setTheme(new Theme(factory));
        applyTheme();
    }

    private void applyTheme() {
        for (Widget widget : widgets){
            widget.applyTheme(theme);
        }
    }

    private char[][] createCanvas() {
        char[][] canvas = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                canvas[y][x] = ' ';
            }
        }
        return canvas;
    }

    private void drawFrame(char[][] canvas) {
        for (int x = 0; x < width; x++) {
            canvas[0][x] = '.';
            canvas[height - 1][x] = '.';
        }
        for (int y = 0; y < height; y++) {
            canvas[y][0] = '.';
            canvas[y][width - 1] = '.';
        }
    }

    private void paint(char[][] canvas, Point point, List<String> lines) {
        for (int row = 0; row < lines.size(); row++) {
            int targetY = point.y() + row;
            if (targetY < 0 || targetY >= height) {
                continue;
            }
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                int targetX = point.x() + col;
                if (targetX < 0 || targetX >= width) {
                    continue;
                }
                canvas[targetY][targetX] = line.charAt(col);
            }
        }
    }

    private String toDisplayText(char[][] canvas) {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++) {
            output.append(canvas[y]);
            if (y < height - 1) {
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }
    //=============================
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}
