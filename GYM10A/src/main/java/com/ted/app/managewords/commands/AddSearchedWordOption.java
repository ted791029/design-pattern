package com.ted.app.managewords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.Command;
import com.ted.app.core.SceneId;
import com.ted.app.core.SceneManager;
import com.ted.app.core.Word;

public class AddSearchedWordOption extends Command {

    public AddSearchedWordOption(String wordName) {
        this(wordName, true);
    }

    public AddSearchedWordOption(String wordName, boolean visible) {
        super(null, "Add " + wordName + " into word repository", visible);
    }

    @Override
    public void action(AppContext context) {;
        SceneManager manager = context.getManager();
        Word queried  = ((Word) context.getTempData(AppContextKey.ADDED_WORD));
        context.getWordRepository().addWord(queried);
        manager.push(SceneId.SEARCH_ADD_CONFIRM);
    }
}
