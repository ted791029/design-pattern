package com.ted.app.managewords.scenes;

import java.util.ArrayList;
import java.util.List;

import com.ted.app.core.AppContext;
import com.ted.app.core.CliText;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;
import com.ted.app.core.Word;

public class AddWordsScene extends Scene {

    private String wordsAddedLine;

    private String wordsNotFoundLine;

    public AddWordsScene(AppContext context) {
        super(SceneId.ADD_WORDS, "Add new words", LifecycleType.DISTANCE_BASED, 2, context);
    }

    @Override
    public void clearState() {
        wordsAddedLine = null;
        wordsNotFoundLine = null;
    }

    @Override
    public void handleTextInput(String input) {
        List<String> names = CliText.parseWordNames(input);

        if (names.isEmpty()) {
            return;
        }

        List<String> added = new ArrayList<>();
        List<String> notFound = new ArrayList<>();
        AppContext context = getContext();
        
        for (String name : names) {
            Word queried = context.getDictionaryQueryTechnique().queryWord(name);
            if (queried == null) {
                notFound.add(name);
                continue;
            }
            if (context.getWordRepository().addWord(queried)) {
                added.add(queried.getName());
            }
        }

        wordsAddedLine = added.isEmpty() ? null : "Words successfully added: " + String.join(", ", added);
        wordsNotFoundLine = notFound.isEmpty() ? null : "Words not found: " + String.join(", ", notFound);
    }
    
    @Override
    protected Messenger renderMessenger() {
        AppContext context = getContext();
        Messenger messenger = new Messenger();
        messenger.add(new Message(CliText.formatCurrentWords(context.getWordRepository().listWordNames())));
        if (wordsAddedLine != null) {
            messenger.add(new Message(wordsAddedLine));
        }
        if (wordsNotFoundLine != null) {
            messenger.add(new Message(wordsNotFoundLine));
        }
        return messenger;
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Please input word's names (separated by commas) that you want to add:");
    }

    // Getters and setters
    public String getWordsAddedLine() {
        return wordsAddedLine;
    }

    public String getWordsNotFoundLine() {
        return wordsNotFoundLine;
    }

    public void setWordsAddedLine(String wordsAddedLine) {
        this.wordsAddedLine = wordsAddedLine;
    }

    public void setWordsNotFoundLine(String wordsNotFoundLine) {
        this.wordsNotFoundLine = wordsNotFoundLine;
    }
}
