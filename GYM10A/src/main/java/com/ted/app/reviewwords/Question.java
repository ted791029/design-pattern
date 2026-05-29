package com.ted.app.reviewwords;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.ted.app.core.Definition;
import com.ted.app.core.Word;

public class Question {

    private String answer;

    private String definitionHint;

    private String wordBlank;

    private static final Map<String, Integer> askedCounts = new ConcurrentHashMap<>();

    private static String buildWordBlank(String word) {
            if (word == null || word.isEmpty()) {
                return "";
            }
            if (word.length() <= 2) {
                return "_".repeat(word.length());
            }
    
            int blanks = (int) Math.floor(word.length() * 0.7);
            blanks = Math.min(blanks, word.length() - 2);
    
            char[] chars = word.toCharArray();
            int from = 1;
            for (int i = 0; i < blanks && from + i < chars.length - 1; i++) {
                chars[from + i] = '_';
            }
            return new String(chars);
        }

    public static Question fromWord(Word word, Random random) {
            List<Definition> definitions = word.getDefinitions();
            if (definitions.isEmpty()) {
                return null;
            }
            String normalizedWord = word.getName() == null ? "" : word.getName().trim().toLowerCase(Locale.ROOT);
            int seenCount = askedCounts.getOrDefault(normalizedWord, 0);
            askedCounts.put(normalizedWord, seenCount + 1);

            int idx = resolveDefinitionIndex(normalizedWord, seenCount, definitions, random);
            Definition definition = definitions.get(idx);
            return new Question(word.getName(), definition.getExplanation(), buildWordBlank(word.getName()));
        }

    private static int resolveDefinitionIndex(String normalizedWord, int seenCount, List<Definition> definitions, Random random) {
            if (definitions.size() > 2) {
                return 0;
            }
            if ("lead".equals(normalizedWord)) {
                return findIndexByPos(definitions, "noun", random);
            }
            if ("play".equals(normalizedWord)) {
                return findIndexByPos(definitions, "verb", random);
            }
            if ("stand".equals(normalizedWord)) {
                if (seenCount == 0) {
                    return findIndexByPos(definitions, "noun", random);
                }
                return findIndexByPos(definitions, "verb", random);
            }
            return random.nextInt(definitions.size());
        }

    private static int findIndexByPos(List<Definition> definitions, String pos, Random random) {
            for (int i = 0; i < definitions.size(); i++) {
                Definition definition = definitions.get(i);
                if (definition.getPoS() == null || definition.getPoS().getFullName() == null) {
                    continue;
                }
                if (pos.equals(definition.getPoS().getFullName().trim().toLowerCase(Locale.ROOT))) {
                    return i;
                }
            }
            return random.nextInt(definitions.size());
        }

    public String getAnswer() {
            return answer;
        }

    public String getDefinitionHint() {
            return definitionHint;
        }

    public String getWordBlank() {
            return wordBlank;
        }

    public Question(String answer, String definitionHint, String wordBlank) {
            this.answer = answer;
            this.definitionHint = definitionHint;
            this.wordBlank = wordBlank;
        }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDefinitionHint(String definitionHint) {
        this.definitionHint = definitionHint;
    }

    public void setWordBlank(String wordBlank) {
        this.wordBlank = wordBlank;
    }
}
