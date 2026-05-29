package com.ted.app.reviewwords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.Command;

public class EndQuestionOption extends Command {

    public EndQuestionOption() {
        super("/*", "End");
    }

    @Override
    public void action(AppContext context) {
        clearQuestionState(context);
        context.getManager().goBack();
    }

    private void clearQuestionState(AppContext context) {
        context.removeTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
        context.removeTempData(AppContextKey.QUESTION_INDEX);
        context.removeTempData(AppContextKey.QUESTION_POINT);
        context.removeTempData(AppContextKey.QUESTION_RESULT_MESSAGE);
        context.removeTempData(AppContextKey.QUESTION_SHOWING_RESULT);
        context.removeTempData(AppContextKey.QUESTION_SHOWING_EXAM_OVER);
    }
}
