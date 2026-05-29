package com.ted.app.core.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.Command;
import com.ted.app.core.SceneManager;

public class Exit extends Command {

    public Exit() {
        this(true);
    }

    public Exit(boolean visible) {
        super("/ESC", "Exit", visible);
    }

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        manager.setRunning(false);
    }
}
