package com.ted.app.managewords.scenes;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.Definition;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.MenuBuilder;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;
import com.ted.app.core.Word;
import com.ted.app.managewords.commands.AddSearchedWordOption;

public class SearchWordsScene extends Scene {

    private Word lastFoundWord;

    private String notFoundName;

    public SearchWordsScene(AppContext context) {
        super(SceneId.SEARCH, "Search words", LifecycleType.CLEAR_ON_LEAVE, 0, context);
    }

    @Override
    public void clearState() {
        lastFoundWord = null;
        notFoundName = null;
    }

    @Override
    public void handleTextInput(String input) {
        AppContext context = getContext();
        Word word = context.getDictionaryQueryTechnique().queryWord(input);

        if (word == null) {
            notFoundName = input;
        } else {
            lastFoundWord = word;
            notFoundName = null;
        }
    }

    @Override
    protected void renderAction() {
        AppContext context = getContext();
        if (lastFoundWord != null) {
            context.putTempData(AppContextKey.ADDED_WORD, lastFoundWord);
        }
    }

    @Override
    protected Messenger renderMessenger() {
        Messenger messenger = new Messenger();
        if (lastFoundWord == null) {
            if (notFoundName == null) {
                messenger.add(new Message("Genius is one percent inspiration and ninety-nine percent perspiration."));
            } else {
                messenger.add(new Message("Cannot find the word '" + notFoundName + "'."));
            }
        }else{
            messenger.add(new Message("Word: " + lastFoundWord.getName()));
            messenger.add(new Message("Description: " + trimTrailingPeriod(lastFoundWord.getDescription())));
            for (Definition definition : lastFoundWord.getDefinitions()) {
                messenger.add(new Message(formatPos(definition) + " - " + definition.getExplanation()));
            }
        }
        return messenger;
    }

    @Override
    protected void contributeMenuOptions(MenuBuilder builder) {
        boolean foundWord = lastFoundWord != null;
        String wordName = foundWord ? lastFoundWord.getName() : "(unknown)";
        builder.add(foundWord
                ? new AddSearchedWordOption(wordName)
                : new AddSearchedWordOption(wordName, false));
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Please input a word's name: ");
    }

    private String formatPos(Definition definition) {
        String fullName = definition.getPoS().getFullName();
        if (fullName == null) {
            return "";
        }
        String normalized = fullName.trim().toLowerCase();
        if ("adjective".equals(normalized)) {
            return "adj";
        }
        return normalized;
    }

    private String trimTrailingPeriod(String value) {
        if (value == null) {
            return "";
        }
        if (value.endsWith(".")) {
            return value.substring(0, value.length() - 1);
        }
        return value;
    }

    // Getters and setters
    public Word getLastFoundWord() {
        return lastFoundWord;
    }

    public String getNotFoundName() {
        return notFoundName;
    }

    public void setLastFoundWord(Word lastFoundWord) {
        this.lastFoundWord = lastFoundWord;
    }

    public void setNotFoundName(String notFoundName) {
        this.notFoundName = notFoundName;
    }

}
