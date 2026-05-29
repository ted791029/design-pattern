package com.ted.app.reviewwords.factories;

import com.ted.app.core.AppContext;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneFactory;
import com.ted.app.reviewwords.scenes.ReviewWordsScene;

public class ReviewWordsSceneFactory implements SceneFactory {
    @Override
    public Scene create(AppContext context) {
        return new ReviewWordsScene(context);
    }
}
