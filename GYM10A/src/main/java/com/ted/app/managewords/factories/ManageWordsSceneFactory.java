package com.ted.app.managewords.factories;

import com.ted.app.core.AppContext;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneFactory;
import com.ted.app.managewords.scenes.ManageWordsScene;

public class ManageWordsSceneFactory implements SceneFactory {
    @Override
    public Scene create(AppContext context) {
        return new ManageWordsScene(context);
    }
}
