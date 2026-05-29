package com.ted.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CliText {

    private CliText() {
    }

    public static String normalizeCommand(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().toLowerCase(Locale.ROOT);
    }

    public static List<String> parseWordNames(String input) {
        if (input == null) {
            return List.of();
        }
        String[] split = input.split(",");
        List<String> names = new ArrayList<>();
        for (String token : split) {
            String trimmed = token.trim();
            if (!trimmed.isEmpty()) {
                names.add(trimmed);
            }
        }
        return names;
    }

    public static String formatCurrentWords(List<String> names) {
        if (names.isEmpty()) {
            return "You don't have any words.";
        }
        return "Current Words: " + String.join(", ", names);
    }
}
