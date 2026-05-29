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

public class DeleteWordsScene extends Scene {

    private String wordsDeletedLine;

    private String wordsNotFoundLine;

    public DeleteWordsScene(AppContext context) {
        super(SceneId.DELETE_WORDS, "Delete words", LifecycleType.DISTANCE_BASED, 2, context);
    }

    @Override
    public void clearState() {
        wordsDeletedLine = null;
        wordsNotFoundLine = null;
    }

    @Override
    public void handleTextInput(String input) {
        List<String> names = CliText.parseWordNames(input);

        if (names.isEmpty()) {
            return;
        }

        List<String> deleted = new ArrayList<>();
        List<String> notFound = new ArrayList<>();
        AppContext context = getContext();

        for (String name : names) {
            if (context.getWordRepository().deleteWord(name)) {
                deleted.add(name);
            } else {
                notFound.add(name);
            }
        }
        
        wordsDeletedLine = deleted.isEmpty() ? null : "Words successfully deleted: " + String.join(", ", deleted);
        wordsNotFoundLine = notFound.isEmpty() ? null : "Words not found: " + String.join(", ", notFound);
    }

    @Override
    protected Messenger renderMessenger() {
        AppContext context = getContext();
        Messenger messenger = new Messenger();
        messenger.add(new Message(
                CliText.formatCurrentWords(context.getWordRepository().listWordNames())));
        if (wordsDeletedLine != null) {
            messenger.add(new Message(wordsDeletedLine));
        }
        if (wordsNotFoundLine != null) {
            messenger.add(new Message(wordsNotFoundLine));
        }
        return messenger;
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Please input word's names (separated by commas) that you want to delete:");
    }

    // Getters and setters
    public String getWordsDeletedLine() {
        return wordsDeletedLine;
    }

    public String getWordsNotFoundLine() {
        return wordsNotFoundLine;
    }

    public void setWordsDeletedLine(String wordsDeletedLine) {
        this.wordsDeletedLine = wordsDeletedLine;
    }

    public void setWordsNotFoundLine(String wordsNotFoundLine) {
        this.wordsNotFoundLine = wordsNotFoundLine;
    }
}
