package com.ted.app.reviewwords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ted.app.core.Word;

public class QuizSession {

    private List<Question> questions;

    public static QuizSession create(List<Word> words, Random random) {
            List<Word> selected = new ArrayList<>(words);
            int limit = Math.min(10, selected.size());
    
            List<Question> questions = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                Question question = Question.fromWord(selected.get(i), random);
                if (question != null) {
                    questions.add(question);
                }
            }
            return new QuizSession(questions);
        }

    public List<Question> getQuestions() {
            return questions;
        }

    private QuizSession(List<Question> questions) {
            this.questions = questions;
        }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
