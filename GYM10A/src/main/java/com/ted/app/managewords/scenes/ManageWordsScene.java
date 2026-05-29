package com.ted.app.managewords.scenes;

import com.ted.app.core.AppContext;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;

public class ManageWordsScene extends Scene {

    public ManageWordsScene(AppContext context) {
        super(SceneId.MANAGE, "Manage words", LifecycleType.NEVER_CLEAR, 0, context);
    }

    @Override
    protected Messenger renderMessenger() {
        return new Messenger(new Message("Wanna learn a new word?"));
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Hello, please select a place to go:");
    }
}
