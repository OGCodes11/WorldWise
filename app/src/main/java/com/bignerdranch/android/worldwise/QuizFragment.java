package com.bignerdranch.android.worldwise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class QuizFragment extends Fragment {
    private Country mCountry;
    private Quiz mQuiz;
    private Question mQuestion;
    private int mCurrentQuestionIndex;
    private TextView questionText;
    private TextView scoreText;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private Button nextButton;
    private boolean quizCompleted = false;

    private int userScore = 0;

    public static QuizFragment newInstance(String countryName) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString("COUNTRY_NAME", countryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        questionText = view.findViewById(R.id.question_text);
        scoreText = view.findViewById(R.id.score_text);
        answer1 = view.findViewById(R.id.answers1_text);
        answer2 = view.findViewById(R.id.answers2_text);
        answer3 = view.findViewById(R.id.answers3_text);
        answer4 = view.findViewById(R.id.answers4_text);
        nextButton = view.findViewById(R.id.nextButton);

        nextButton.setEnabled(false);

        if (getArguments() != null) {
            String countryName = getArguments().getString("COUNTRY_NAME");
            Log.d("QUIZ_FRAGMENT", "Received countryName: " + countryName);
            mCountry = CountryLab.getInstance(requireContext()).getCountryByName(countryName);
            //Log.d("QUIZ_NAVIGATION", "Navigating to Quiz for: " + mCountry.getName());
            if (mCountry != null) {
                mQuiz = mCountry.getQ();
                mCurrentQuestionIndex = 0;
                mQuestion = mQuiz.getQuestions().get(mCurrentQuestionIndex);
                Log.d("QUIZ_FRAGMENT", "mQuiz question count: " + (mQuiz != null ? mQuiz.getQuestions().size() : "Quiz is null"));
                questionText.setText(mQuestion.getQuestion());
                scoreText.setText("Quiz Score: " + mQuiz.getScore() + "/" + mQuiz.getQuestions().size());
                answer1.setText(mQuestion.getAnswers().get(0));
                answer2.setText(mQuestion.getAnswers().get(1));
                answer3.setText(mQuestion.getAnswers().get(2));
                answer4.setText(mQuestion.getAnswers().get(3));
                loadQuestion();
            }
        }

        answer1.setOnClickListener(v -> {
            checkAnswer(0);
        });

        answer2.setOnClickListener(v -> {
            checkAnswer(1);
        });

        answer3.setOnClickListener(v -> {
            checkAnswer(2);
        });

        answer4.setOnClickListener(v -> {
            checkAnswer(3);
        });

        nextButton.setOnClickListener(v -> {
                    if (quizCompleted) {
                        goToFinalScreen();
                    }
        });

        return view;
    }

    private void loadQuestion() {
        if (mQuiz.isDone()) {
            quizCompleted = true;
            nextButton.setEnabled(true);
            Toast.makeText(getActivity(), "Quiz completed! Click Next to continue.", Toast.LENGTH_SHORT).show();
            return;
        }

        mQuestion = mQuiz.getQuestions().get(mQuiz.getQuestionIndex());
        Question currentQuestion = mQuiz.getQuestions().get(mQuiz.getQuestionIndex());
        questionText.setText(currentQuestion.getQuestion());
        scoreText.setText(mQuiz.getScoreString());
        answer1.setText(currentQuestion.getAnswers().get(0));
        answer2.setText(currentQuestion.getAnswers().get(1));
        answer3.setText(currentQuestion.getAnswers().get(2));
        answer4.setText(currentQuestion.getAnswers().get(3));
    }

    private void checkAnswer(int selectedAnswerIndex) {
        boolean isCorrect = mQuiz.processAnswer(selectedAnswerIndex);

        if (isCorrect) {
            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
            userScore++;
        } else {
            Toast.makeText(getActivity(), "Wrong! The correct answer was: " + mQuestion.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }

        scoreText.setText(mQuiz.getScoreString());

        if (mQuiz.isDone()) {
            quizCompleted = true;
            nextButton.setEnabled(true);
            Toast.makeText(getActivity(), "Quiz completed! Click Complete to finish your lesson. DO NOT click on any answers twice.", Toast.LENGTH_LONG).show();
        } else {
            loadQuestion();
        }
    }

    private void goToFinalScreen() {
        Log.d("QuizFragment", "Going to FinalActivity...");

        if (mCountry == null) {
            Log.e("QuizFragment", "mCountry is null, cannot proceed!");
            return;
        }
        mCountry.setCountryLearned(true);
        CountryLab lab = CountryLab.getInstance(getActivity());
        lab.markCountryAsLearned(mCountry.getName());


        ArrayList<String> allLearned = lab.getLearnedCountryNames();
        Log.d("DATABASE_TEST", "Learned countries: " + allLearned);

        Intent intent = new Intent(requireActivity(), FinalActivity.class);
        intent.putExtra("COUNTRY_NAME", mCountry.getName());
        intent.putExtra("QUIZ_SCORE", userScore);
        startActivity(intent);
    }
}