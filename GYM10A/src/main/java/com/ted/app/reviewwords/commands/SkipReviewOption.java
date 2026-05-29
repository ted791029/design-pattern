package com.ted.app.reviewwords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.Command;
import com.ted.app.core.SceneManager;

public class SkipReviewOption extends Command {

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        manager.goBack();
    }

    public SkipReviewOption() {
        super("N", "No, not today.");
    }
}
