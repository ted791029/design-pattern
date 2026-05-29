package com.ted.app.core.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.Command;
import com.ted.app.core.SceneManager;

public class PreviousPage extends Command {

    public PreviousPage() {
        this(true);
    }

    public PreviousPage(boolean visible) {
        super("/B", "Previous Page", visible);
    }

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        if (manager.getSceneStack().size() <= 1) {
            manager.setRunning(false);
            return;
        }
        manager.goBack();
    }
}
