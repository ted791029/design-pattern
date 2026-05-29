package com.ted.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Menu {

    private List<MenuOption> options = new ArrayList<>();

    public Menu(MenuOption... options) {
        this.options.addAll(Arrays.asList(options));
    }

    public String displayLabel() {
        StringBuilder text = new StringBuilder();
        int generatedIndex = 1;
        for (int i = 0; i < options.size(); i++) {
            MenuOption option = options.get(i);
            if (!option.isVisible()) {
                continue;
            }

            if (text.length() > 0) {
                text.append(System.lineSeparator());
            }

            String key = option.getOptionCustomKey();
            if (key != null && !key.isBlank()) {
                text.append("[").append(key).append("] ");
            } else {
                text.append("[/").append(generatedIndex).append("] ");
                generatedIndex++;
            }
            text.append(option.getOptionLabel());
        }
        return text.toString();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public MenuOption resolveOption(String input) {
        String command = normalize(input);
        if (command.isEmpty()) {
            return null;
        }

        MenuOption option = resolveByCustomKey(command);
        if (option != null) {
            return option;
        }

        return resolveByGeneratedIndex(command);
    }

    public int size() {
        int visibleCount = 0;
        for (MenuOption option : options) {
            if (option.isVisible()) {
                visibleCount++;
            }
        }
        return visibleCount;
    }

    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().toLowerCase(Locale.ROOT);
    }

    private Integer parseGeneratedIndex(String command) {
        if (command.length() < 2 || command.charAt(0) != '/') {
            return null;
        }
        String number = command.substring(1);
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return null;
            }
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private MenuOption resolveByCustomKey(String command) {
        for (MenuOption option : options) {
            if (!option.isVisible()) {
                continue;
            }
            String key = normalize(option.getOptionCustomKey());
            if (!key.isEmpty() && key.equals(command)) {
                return option;
            }
        }
        return null;
    }

    private MenuOption resolveByGeneratedIndex(String command) {
        Integer index = parseGeneratedIndex(command);
        if (index == null || index <= 0) {
            return null;
        }

        int current = 0;
        for (MenuOption option : options) {
            if (!option.isVisible()) {
                continue;
            }
            String key = option.getOptionCustomKey();
            if (key == null || key.isBlank()) {
                current++;
                if (current == index) {
                    return option;
                }
            }
        }
        return null;
    }

    // Getters and setters
    public List<MenuOption> getOptions() {
        return options;
    }

    public void setOptions(List<MenuOption> options) {
        this.options = options;
    }
}
