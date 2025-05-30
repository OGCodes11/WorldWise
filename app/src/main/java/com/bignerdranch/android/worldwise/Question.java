package com.bignerdranch.android.worldwise;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswerIndex;

    public Question (String question, ArrayList<String> answers, int correctAnswer){
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestions(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return answers.get(correctAnswerIndex);
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}
