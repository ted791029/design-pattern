package com.ted.app.managewords.scenes;

import java.util.Set;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.DefaultMenuOptionKey;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.MenuBuilder;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;
import com.ted.app.core.Word;
import com.ted.app.managewords.commands.ConfirmSearchAddOption;

public class SearchAddConfirmScene extends Scene {

    private boolean added;

    public SearchAddConfirmScene(AppContext context) {
        super(SceneId.SEARCH_ADD_CONFIRM, "Add word into word repository", LifecycleType.CLEAR_ON_LEAVE, 0, context);
    }

    @Override
    public void clearState() {
        added = false;
        AppContext context = getContext();
        context.removeTempData(AppContextKey.PENDING_WORD_FROM_SEARCH);
        context.removeTempData(AppContextKey.ADDED_WORD);
    }

    @Override
    protected void contributeMenuOptions(MenuBuilder builder) {
        builder.add(new ConfirmSearchAddOption());
    }

    @Override
    protected void handleTextInput(String input) {
        getContext().getManager().goBack();
    }

    @Override
    protected void renderAction() {
        AppContext context = getContext();
        if (!added && context.getTempData(AppContextKey.PENDING_WORD_FROM_SEARCH) != null) {
            context.getWordRepository().addWord((Word) context.getTempData(AppContextKey.PENDING_WORD_FROM_SEARCH));
            added = true;
        }
    }

    @Override
    protected Messenger renderMessenger() {
        AppContext context = getContext();
        String name = context.getTempData(AppContextKey.ADDED_WORD) == null ? "(unknown)" : ((Word) context.getTempData(AppContextKey.ADDED_WORD)).getName();
        return new Messenger(new Message("The word '" + name + "' has been added."));
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Command:");
    }

    @Override
    protected Set<DefaultMenuOptionKey> hiddenDefaultMenuOptions() {
        return Set.of(DefaultMenuOptionKey.PREVIOUS_PAGE);
    }

    // Getters and setters
    public boolean getAdded() {
        return added;
    }

    @Override
    public String getBreadcrumbLabel() {
        AppContext context = getContext();
        String name = context.getTempData(AppContextKey.ADDED_WORD) == null ? "(unknown)" : ((Word) context.getTempData(AppContextKey.ADDED_WORD)).getName();
        return "Add '" + name + "' into word repository";
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
