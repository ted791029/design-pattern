package com.ted.app.reviewwords.commands;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.Command;
import com.ted.app.reviewwords.QuizSession;

public class ContinueQuestionOption extends Command {

    public ContinueQuestionOption() {
        this(true);
    }

    public ContinueQuestionOption(boolean visible) {
        super("/*", "Continue", visible);
    }

    @Override
    public void action(AppContext context) {
        QuizSession session = (QuizSession) context.getTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
        if (session == null || session.getQuestions().isEmpty()) {
            return;
        }
        int nextIndex = getQuestionIndex(context) + 1;
        if (nextIndex >= session.getQuestions().size()) {
            context.putTempData(AppContextKey.LATEST_POINT, getPoint(context));
            context.putTempData(AppContextKey.LATEST_NUM_OF_QUESTIONS, session.getQuestions().size());
            context.removeTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
            context.putTempData(AppContextKey.QUESTION_SHOWING_RESULT, false);
            context.putTempData(AppContextKey.QUESTION_SHOWING_EXAM_OVER, true);
            return;
        }
        context.putTempData(AppContextKey.QUESTION_INDEX, nextIndex);
        context.putTempData(AppContextKey.QUESTION_SHOWING_RESULT, false);
    }

    private int getPoint(AppContext context) {
        Integer point = (Integer) context.getTempData(AppContextKey.QUESTION_POINT);
        return point == null ? 0 : point;
    }

    private int getQuestionIndex(AppContext context) {
        Integer index = (Integer) context.getTempData(AppContextKey.QUESTION_INDEX);
        return index == null ? 0 : index;
    }
}
