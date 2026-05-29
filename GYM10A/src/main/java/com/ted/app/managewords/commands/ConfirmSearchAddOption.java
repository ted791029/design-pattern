package com.ted.app.managewords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.Command;
import com.ted.app.core.SceneManager;

public class ConfirmSearchAddOption extends Command {

    public ConfirmSearchAddOption() {
        super("/*", "Okay, I got it");
    }

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        manager.goBack();
    }

}
