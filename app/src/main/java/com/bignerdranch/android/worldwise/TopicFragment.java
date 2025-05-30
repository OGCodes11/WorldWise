package com.bignerdranch.android.worldwise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopicFragment extends Fragment {
    private Country mCountry;
    private Topic mTopic;
    private WebView mWebView;

    public static TopicFragment newInstance(String countryName, String topicName) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString("COUNTRY_NAME", countryName);
        args.putString("TOPIC_NAME", topicName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.topic_fragment, container, false);

        TextView funFacts = view.findViewById(R.id.funFacts);
        mWebView = view.findViewById(R.id.webView);
        Button backButton = view.findViewById(R.id.backButton);

        TextView topicTitle = view.findViewById(R.id.funFact);
        /*if (topicName.equalsIgnoreCase("Cuisine")) {
            topicTitle.setText("Recipe");
        } else {
            topicTitle.setText("Fun Facts");
        }*/

        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CountryFragment.newInstance(mCountry.getName()))
                    .addToBackStack(null)
                    .commit();

            /*Intent intent = new Intent(getActivity(), CountryActivity.class);
            intent.putExtra("COUNTRY_NAME", mCountry.getName());
            startActivity(intent);
            requireActivity().finish();*/
        });

        if (getArguments() != null) {
            String countryName = getArguments().getString("COUNTRY_NAME");
            String topicName = getArguments().getString("TOPIC_NAME");

            mCountry = CountryLab.getInstance(requireContext()).getCountryByName(countryName);
            if (mCountry != null) {
                for (Topic topic : mCountry.getTopics()) {
                    if (topic.getName().equalsIgnoreCase(topicName)) {
                        mTopic = topic;
                        if (topicName.equalsIgnoreCase("Cuisine")) {
                            topicTitle.setText("Recipe");
                        }
                        break;
                    }
                }
                if (mTopic != null) {
                    String funFactsText = String.join("\n\n", mTopic.getFunFacts());
                    funFacts.setText(funFactsText);
                    mWebView.loadData(mTopic.getVideoUrl(), "text/html", "utf-8");
                    mWebView.getSettings().setJavaScriptEnabled(true);
                    mWebView.setWebChromeClient(new WebChromeClient());

                }
            }
        }
        return view;
    }
}