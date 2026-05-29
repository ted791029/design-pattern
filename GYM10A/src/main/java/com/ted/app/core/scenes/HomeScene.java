package com.ted.app.core.scenes;

import com.ted.app.core.AppContext;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;

public class HomeScene extends Scene {

    public HomeScene(AppContext context) {
        super(SceneId.HOME, "/", LifecycleType.NEVER_CLEAR, 0, context);
    }

    @Override
    protected Messenger renderMessenger() {
        return new Messenger(
                new Message("Hello, welcome to vocabulary learning system.")
            );
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("What are you looking for?");
    }
}
