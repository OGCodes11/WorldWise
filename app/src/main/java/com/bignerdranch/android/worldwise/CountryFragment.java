package com.bignerdranch.android.worldwise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CountryFragment extends Fragment {
    private ImageView flagMap;
    private TextView country;
    private Country mCountry;
    private Button quizButton;

    private static final String PREFS_NAME = "CountryProgress";
    private static final String HISTORY_KEY = "HISTORY_VIEWED";
    private static final String GEOGRAPHY_KEY = "GEOGRAPHY_VIEWED";
    private static final String CULTURE_KEY = "CULTURE_VIEWED";
    private static final String CUISINE_KEY = "CUISINE_VIEWED";

    public static CountryFragment newInstance(String countryName) {
        CountryFragment fragment = new CountryFragment();
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

        View view = inflater.inflate(R.layout.country_fragment, container, false);

        TextView historyText = view.findViewById(R.id.history_text);
        TextView geographyText = view.findViewById(R.id.geography_text);
        TextView cultureText = view.findViewById(R.id.culture_text);
        TextView cuisineText = view.findViewById(R.id.cuisine_text);


        flagMap = view.findViewById(R.id.flag_map);
        quizButton = view.findViewById(R.id.quiz_button);

        country = view.findViewById(R.id.country_text);

        if (getArguments() != null) {
            String countryName = getArguments().getString("COUNTRY_NAME");
            country.setText("Welcome to " + countryName + "!");

            mCountry = CountryLab.getInstance(requireContext()).getCountryByName(countryName);
            if (mCountry != null) {
                flagMap.setImageDrawable(mCountry.getMap());
            }
        }
        Log.d("COUNTRY OPENED", mCountry.getName());

        historyText.setOnClickListener(v -> openTopicScreen("History", HISTORY_KEY));
        geographyText.setOnClickListener(v -> openTopicScreen("Geography", GEOGRAPHY_KEY));
        cultureText.setOnClickListener(v -> openTopicScreen("Culture", CULTURE_KEY));
        cuisineText.setOnClickListener(v -> openTopicScreen("Cuisine", CUISINE_KEY));

        quizButton.setEnabled(areAllTopicsViewed());
        quizButton.setOnClickListener(v -> {
            if (mCountry != null && areAllTopicsViewed()) {
                Log.d("QUIZ_NAVIGATION", "Navigating to Quiz for: " + mCountry.getName());
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("COUNTRY_NAME", mCountry.getName());
                startActivity(intent);
            }
        });
        return view;
    }

    /*private String getKey(String topic) {
        return mCountry.getName().toUpperCase() + "_" + topic + "_VIEWED";
    }*/

    private void openTopicScreen(String topicName, String key) {
        if (mCountry != null) {

            for (Topic topic : mCountry.getTopics()) {
                if (topic.getName().equalsIgnoreCase(topicName)) {
                    topic.setViewed(true);
                    break;
                }
            }

            Intent intent = new Intent(getActivity(), TopicActivity.class);
            intent.putExtra("COUNTRY_NAME", mCountry.getName());
            intent.putExtra("TOPIC_NAME", topicName);

            quizButton.setEnabled(areAllTopicsViewed());

            startActivity(intent);
        }
    }

    private boolean areAllTopicsViewed() {
        if (mCountry == null)
            return false;

        for (Topic topic : mCountry.getTopics()) {
            if (!topic.isViewed()) {
                return false;
            }
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        if (quizButton != null) {
        quizButton.setEnabled(areAllTopicsViewed());
        }
        //printAllTopicProgress();
    }
}