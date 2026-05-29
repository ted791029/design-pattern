package com.ted.app.reviewwords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.Command;
import com.ted.app.core.SceneId;
import com.ted.app.core.SceneManager;
import com.ted.app.reviewwords.QuizSession;

public class StartReviewOption extends Command {

    public StartReviewOption() {
        super("Y", "Let's do it.");
    }

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        if (context.getWordRepository().size() > 0) {
            context.putTempData(AppContextKey.ACTIVE_QUIZ_SESSION,
                    QuizSession.create(
                        context.getWordRepository().listWords(),
                        context.getRandom()));
            context.putTempData(AppContextKey.QUESTION_POINT, 0);
            context.putTempData(AppContextKey.QUESTION_INDEX, 0);
            context.putTempData(AppContextKey.QUESTION_RESULT_MESSAGE, null);
            context.putTempData(AppContextKey.QUESTION_SHOWING_RESULT, false);
            context.putTempData(AppContextKey.QUESTION_SHOWING_EXAM_OVER, false);
            manager.push(SceneId.QUESTION);
        }
    }
}
