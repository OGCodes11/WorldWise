package com.bignerdranch.android.worldwise;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class QuizActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        String countryName = intent.getStringExtra("COUNTRY_NAME");

        return QuizFragment.newInstance(countryName);
    }
}
