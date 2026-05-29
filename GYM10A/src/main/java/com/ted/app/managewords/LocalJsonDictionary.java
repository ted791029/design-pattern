package com.ted.app.managewords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ted.app.core.CliText;
import com.ted.app.core.Definition;
import com.ted.app.core.PartOfSpeech;
import com.ted.app.core.Word;

public class LocalJsonDictionary {

    private Map<String, Word> fallbackData = new LinkedHashMap<>();

    private Path jsonPath;

    public LocalJsonDictionary(Path jsonPath) {
        this.jsonPath = jsonPath;
        seedFallbackData();
    }

    public Word queryWord(String name) {
        if (name == null) {
            return null;
        }
        String query = name.trim();
        if (query.isEmpty()) {
            return null;
        }

        Map<String, Word> data = loadData();
        for (Map.Entry<String, Word> entry : data.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(query)) {
                return entry.getValue().copy();
            }
        }
        return null;
    }

    private Map<String, Word> loadData() {
        if (!Files.exists(jsonPath)) {
            return fallbackData;
        }
        try {
            String json = Files.readString(jsonPath, StandardCharsets.UTF_8);
            Map<String, Word> parsed = parseJsonToWords(json);
            return parsed.isEmpty() ? fallbackData : parsed;
        } catch (IOException e) {
            return fallbackData;
        }
    }

    private Map<String, Word> parseJsonToWords(String json) {
        Map<String, Word> output = new LinkedHashMap<>();
        Pattern wordPattern = Pattern.compile(
                "\"([^\"]+)\"\\s*:\\s*\\{\\s*\"description\"\\s*:\\s*\"([^\"]*)\"\\s*,\\s*\"definitions\"\\s*:\\s*\\{(.*?)\\}\\s*\\}",
                Pattern.DOTALL);
        Pattern definitionPattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"");

        Matcher wordMatcher = wordPattern.matcher(json);
        while (wordMatcher.find()) {
            String name = unescape(wordMatcher.group(1));
            String description = unescape(wordMatcher.group(2));
            String definitionsBlock = wordMatcher.group(3);

            List<Definition> definitions = new ArrayList<>();
            Matcher defMatcher = definitionPattern.matcher(definitionsBlock);
            while (defMatcher.find()) {
                String key = unescape(defMatcher.group(1));
                String value = unescape(defMatcher.group(2));
                definitions.add(new Definition(toPartOfSpeech(key), value));
            }
            if (!definitions.isEmpty()) {
                output.put(CliText.normalizeCommand(name), new Word(name, description, definitions));
            }
        }
        return output;
    }

    private void seedFallbackData() {
        fallbackData.put("light", new Word(
                "light",
                "The natural agent that stimulates sight and makes things visible.",
                List.of(
                        new Definition(new PartOfSpeech("n.", "Noun"),
                                "The natural agent that stimulates sight and makes things visible."),
                        new Definition(new PartOfSpeech("v.", "Verb"),
                                "To provide with light or lighting; illuminate."),
                        new Definition(new PartOfSpeech("a.", "Adjective"),
                                "Having a considerable or sufficient amount of natural light; not dark."))));
        fallbackData.put("stand", new Word(
                "stand",
                "Remain upright on the feet.",
                List.of(
                        new Definition(new PartOfSpeech("v.", "Verb"),
                                "Have or maintain an upright position, supported by one's feet."),
                        new Definition(new PartOfSpeech("n.", "Noun"), "A stand or booth for items."))));
        fallbackData.put("work", new Word(
                "work",
                "Activity involving mental or physical effort done to achieve a purpose.",
                List.of(
                        new Definition(new PartOfSpeech("n.", "Noun"),
                                "Activity involving mental or physical effort done to achieve a purpose."),
                        new Definition(new PartOfSpeech("v.", "Verb"),
                                "Be engaged in physical or mental activity in order to achieve a result."))));
        fallbackData.put("point", new Word(
                "point",
                "A particular spot, place, or position.",
                List.of(
                        new Definition(new PartOfSpeech("n.", "Noun"), "A particular spot, place, or position."),
                        new Definition(new PartOfSpeech("v.", "Verb"),
                                "Direct attention or indicate a particular item."))));
        fallbackData.put("change", new Word(
                "change",
                "Make or become different.",
                List.of(
                        new Definition(new PartOfSpeech("v.", "Verb"), "Make or become different."),
                        new Definition(new PartOfSpeech("n.", "Noun"),
                                "An act or process through which something becomes different."))));
    }

    private PartOfSpeech toPartOfSpeech(String value) {
        String key = value == null ? "" : value.trim().toLowerCase();
        return switch (key) {
            case "noun", "n." -> new PartOfSpeech("n.", "Noun");
            case "verb", "v." -> new PartOfSpeech("v.", "Verb");
            case "adjective", "a." -> new PartOfSpeech("a.", "Adjective");
            case "adverb", "adv." -> new PartOfSpeech("adv.", "Adverb");
            default -> new PartOfSpeech(key, value);
        };
    }

    private String unescape(String value) {
        return value.replace("\\n", "\n").replace("\\\"", "\"");
    }

    // Getters and setters
    public Map<String, Word> getFallbackData() {
        return fallbackData;
    }

    public Path getJsonPath() {
        return jsonPath;
    }

    public void setFallbackData(Map<String, Word> fallbackData) {
        this.fallbackData = fallbackData;
    }

    public void setJsonPath(Path jsonPath) {
        this.jsonPath = jsonPath;
    }
}
