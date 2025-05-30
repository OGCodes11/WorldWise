package com.bignerdranch.android.worldwise;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private ArrayList<Question> questions;
    private int score;
    private boolean done;
    private int questionIndex;

    public Quiz (ArrayList<Question> questions, int score, boolean done, int questionIndex){
        this.questions = questions;
        this.score = score;
        this.done = done;
        this.questionIndex = questionIndex;
    }

    public int getScore() {
        return score;
    }

    public String getScoreString(){
        return "Quiz Score: " + score + "/" + questions.size();
    }

    public boolean isDone() {
        return done;
    }

    public boolean processAnswer(int choice){
        boolean questionAnswer = false;
        Question currentQuestion = questions.get(questionIndex);
        if(choice == currentQuestion.getCorrectAnswerIndex()) {
            questionAnswer = true;
            score++;
        } else
            questionAnswer = false;
        questionIndex++;
        if(questionIndex >= questions.size()){
            done = true;
        }
        return questionAnswer;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
