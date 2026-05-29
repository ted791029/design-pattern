package com.ted.app.core.factories;

import com.ted.app.core.AppContext;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneFactory;
import com.ted.app.core.scenes.HomeScene;

public class HomeSceneFactory implements SceneFactory {
    @Override
    public Scene create(AppContext context) {
        return new HomeScene(context);
    }
}
