package com.bignerdranch.android.worldwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FinalActivity extends AppCompatActivity {
    private TextView countryName;
    private TextView scoreText;
    private Country mCountry;
    private Quiz mQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_final);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countryName = findViewById(R.id.countryText);
        scoreText = findViewById(R.id.score_text);

        String country = getIntent().getStringExtra("COUNTRY_NAME");
        int quizScore = getIntent().getIntExtra("QUIZ_SCORE", 0);

        if (country != null) {
            countryName.setText(country);
            mCountry = CountryLab.getInstance(this).getCountryByName(country);
            mQuiz = mCountry.getQ();
            scoreText.setText("Quiz Score: " + quizScore + "/" + mQuiz.getQuestions().size());
        } else {
            countryName.setText("Unknown Country");
        }

        Button learnButton = findViewById(R.id.learnButton);
        mCountry = CountryLab.getInstance(this).getRandomUnlearnedCountry();
        if (mCountry == null){
            Toast.makeText(this, "All countries completed! Press the restart button to continue", Toast.LENGTH_LONG).show();
            learnButton.setEnabled(false);
        }

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                //intent.putExtra("COUNTRY_NAME", mCountry.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.final_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_restart) {
            CountryLab.getInstance(this).clearLearnedCountries();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
