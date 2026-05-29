package com.ted.app.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WordRepository {

    private Map<String, Word> words = new LinkedHashMap<>();

    public boolean addWord(Word word) {
        if (word == null || word.getName() == null) {
            return false;
        }
        String key = normalizeKey(word.getName());
        if (key.isEmpty()) {
            return false;
        }
        if (words.containsKey(key)) {
            return false;
        }
        words.put(key, word.copy());
        return true;
    }

    public boolean deleteWord(String name) {
        String key = normalizeKey(name);
        if (key.isEmpty()) {
            return false;
        }
        return words.remove(key) != null;
    }

    public List<String> listWordNames() {
        List<String> names = new ArrayList<>();
        for (Word word : words.values()) {
            names.add(word.getName());
        }
        return names;
    }

    public List<Word> listWords() {
        List<Word> snapshot = new ArrayList<>();
        for (Word word : words.values()) {
            snapshot.add(word.copy());
        }
        return snapshot;
    }

    public int size() {
        return words.size();
    }

    private String normalizeKey(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().toLowerCase(Locale.ROOT);
    }

    // Getters and setters
    public Map<String, Word> getWords() {
        return words;
    }

    public void setWords(Map<String, Word> words) {
        this.words = words;
    }

}
