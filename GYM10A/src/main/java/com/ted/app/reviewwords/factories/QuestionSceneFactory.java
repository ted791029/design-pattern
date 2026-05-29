package com.ted.app.reviewwords.factories;

import com.ted.app.core.AppContext;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneFactory;
import com.ted.app.reviewwords.scenes.QuestionScene;

public class QuestionSceneFactory implements SceneFactory {
    @Override
    public Scene create(AppContext context) {
        return new QuestionScene(context);
    }
}
