package com.bignerdranch.android.worldwise;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class TopicActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        String countryName = intent.getStringExtra("COUNTRY_NAME");
        String topicName = intent.getStringExtra("TOPIC_NAME");

        return TopicFragment.newInstance(countryName, topicName);
    }
}
