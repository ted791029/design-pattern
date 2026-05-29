package com.ted.app.reviewwords.scenes;

import java.util.Set;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.DefaultMenuOptionKey;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.MenuBuilder;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;
import com.ted.app.reviewwords.Question;
import com.ted.app.reviewwords.QuizSession;
import com.ted.app.reviewwords.commands.ContinueQuestionOption;
import com.ted.app.reviewwords.commands.EndQuestionOption;

public class QuestionScene extends Scene {

    public QuestionScene(AppContext context) {
        super(SceneId.QUESTION, "Question", LifecycleType.DISTANCE_BASED, 3, context);
    }

    @Override
    public void clearState() {
        AppContext context = getContext();
        context.removeTempData(AppContextKey.QUESTION_POINT);
        context.removeTempData(AppContextKey.QUESTION_INDEX);
        context.removeTempData(AppContextKey.QUESTION_RESULT_MESSAGE);
        context.removeTempData(AppContextKey.QUESTION_SHOWING_RESULT);
        context.removeTempData(AppContextKey.QUESTION_SHOWING_EXAM_OVER);
    }

    @Override
    protected void contributeMenuOptions(MenuBuilder builder) {
        AppContext context = getContext();
        boolean showEnd = isShowingExamOver(context);
        boolean showContinue = isShowingResult(context);
        if (showEnd) {
            builder.add(new EndQuestionOption());
            return;
        }
        if (showContinue) {
            builder.add(new ContinueQuestionOption());
        }
    }

    @Override
    protected void handleTextInput(String input) {
        AppContext context = getContext();
        if (isShowingExamOver(context)) {
            new EndQuestionOption().action(context);
            return;
        }

         if (isShowingResult(context)) {
            new ContinueQuestionOption().action(context);
            return;
        }

        QuizSession session = (QuizSession) context.getTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
        if (session == null || session.getQuestions().isEmpty()) {
            return;
        }

        int questionIndex = getQuestionIndex(context);
        if (questionIndex >= session.getQuestions().size()) {
            return;
        }
        Question current = session.getQuestions().get(questionIndex);
        String answer = input == null ? "" : input.trim();
        checkAnswer(context, current, answer);
        context.putTempData(AppContextKey.QUESTION_SHOWING_RESULT, true);
    }

    @Override
    protected Set<DefaultMenuOptionKey> hiddenDefaultMenuOptions() {
        AppContext context = getContext();
        if (isShowingResult(context) || isShowingExamOver(context)) {
            return Set.of(DefaultMenuOptionKey.PREVIOUS_PAGE);
        }
        return Set.of();
    }

    @Override
    protected Messenger renderMessenger() {
        AppContext context = getContext();
        Messenger messenger = new Messenger();
        QuizSession session = (QuizSession) context.getTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
        if (isShowingExamOver(context)) {
            int latestPoint = getLatestPoint(context);
            int latestNumOfQuestions = getLatestNumOfQuestions(context);
            messenger.add(new Message("The exam is over!"));
            messenger.add(new Message("You got " + latestPoint + "/" + latestNumOfQuestions + " point."));
        } else if (session == null || session.getQuestions().isEmpty()) {
            messenger.add(new Message("No questions available."));
        } else if (isShowingResult(context)) {
            String resultMessage = (String) context.getTempData(AppContextKey.QUESTION_RESULT_MESSAGE);
            messenger.add(new Message(resultMessage == null ? "" : resultMessage));
        }else{
            int remaining = session.getQuestions().size() - getQuestionIndex(context);
            messenger.add(new Message("Point: " + getPoint(context)));
            messenger.add(new Message("Remaining: " + remaining));
        }
        return messenger;
    }

    @Override
    protected Prompt renderPrompt() {
        AppContext context = getContext();
        QuizSession session = (QuizSession) context.getTempData(AppContextKey.ACTIVE_QUIZ_SESSION);
        if (isShowingExamOver(context) || isShowingResult(context) || session == null || session.getQuestions().isEmpty()) {
            return new Prompt("Command:");
        }

        int questionIndex = getQuestionIndex(context);
        Question question = session.getQuestions().get(questionIndex);
        String promptText = (questionIndex + 1) + ". Question: "
                + question.getWordBlank()
                + ": "
                + question.getDefinitionHint()
                + System.lineSeparator()
                + "Answer:";
        return new Prompt(promptText);
    }

    private void checkAnswer(AppContext context, Question question, String answer) {
        if (question.getAnswer().equalsIgnoreCase(answer)) {
            context.putTempData(AppContextKey.QUESTION_POINT, getPoint(context) + 1);
            context.putTempData(AppContextKey.QUESTION_RESULT_MESSAGE, "You got the answer. The answer is " + question.getAnswer() + ".");
        } else {
            context.putTempData(AppContextKey.QUESTION_RESULT_MESSAGE, "You missed it! The answer is " + question.getAnswer() + ".");
        }
    }

    private int getPoint(AppContext context) {
        Integer point = (Integer) context.getTempData(AppContextKey.QUESTION_POINT);
        return point == null ? 0 : point;
    }

    private int getQuestionIndex(AppContext context) {
        Integer questionIndex = (Integer) context.getTempData(AppContextKey.QUESTION_INDEX);
        return questionIndex == null ? 0 : questionIndex;
    }

    private boolean isShowingResult(AppContext context) {
        Boolean showingResult = (Boolean) context.getTempData(AppContextKey.QUESTION_SHOWING_RESULT);
        return showingResult != null && showingResult;
    }

    private boolean isShowingExamOver(AppContext context) {
        Boolean showingExamOver = (Boolean) context.getTempData(AppContextKey.QUESTION_SHOWING_EXAM_OVER);
        return showingExamOver != null && showingExamOver;
    }

    private int getLatestPoint(AppContext context) {
        Integer latestPoint = (Integer) context.getTempData(AppContextKey.LATEST_POINT);
        return latestPoint == null ? 0 : latestPoint;
    }

    private int getLatestNumOfQuestions(AppContext context) {
        Integer latestNumOfQuestions = (Integer) context.getTempData(AppContextKey.LATEST_NUM_OF_QUESTIONS);
        return latestNumOfQuestions == null ? 0 : latestNumOfQuestions;
    }

    @Override
    public String getBreadcrumbLabel() {
        AppContext context = getContext();
        if (isShowingExamOver(context)) {
            return "Exam's Over";
        }
        if (isShowingResult(context)) {
            return "Result";
        }
        return "Question " + (getQuestionIndex(context) + 1);
    }
}
