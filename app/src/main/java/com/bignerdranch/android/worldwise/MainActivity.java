package com.bignerdranch.android.worldwise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Country mCountry;
    private ImageView flagMap;
    private TextView guessAttempt;
    private EditText userGuess;
    private Button guessButton;
    private Button learnButton;
    private ArrayList<Country> mCountryArrayList;
    private int attemptsLeft = 3;
    private boolean correctGuess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flagMap = findViewById(R.id.flag_map);
        guessAttempt = findViewById(R.id.guessAttempt);
        userGuess = findViewById(R.id.userGuess);
        guessButton = findViewById(R.id.guessButton);
        learnButton = findViewById(R.id.okButton);
        learnButton.setEnabled(false);


        mCountryArrayList = CountryLab.getInstance(this).getCountryList();

        loadUnlearnedRandomCountry();

        guessButton.setOnClickListener(v -> checkGuess());

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.putExtra("COUNTRY_NAME", mCountry.getName());
                startActivity(intent);
            }
        });

        updateGuessText();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadUnlearnedRandomCountry() {
        mCountry = CountryLab.getInstance(this).getRandomUnlearnedCountry();

        if (mCountry == null) {
            Toast.makeText(this, "All countries completed!", Toast.LENGTH_LONG).show();
            if (flagMap != null) {
                flagMap.setImageDrawable(null);
            }
            learnButton.setEnabled(false);
            guessButton.setEnabled(false);
            return;
        }

        if (flagMap == null) {
            flagMap = findViewById(R.id.flag_map);
        }

        flagMap.setImageDrawable(mCountry.getMap());
        attemptsLeft = 3;
        userGuess.setText("");
        updateGuessText();
        learnButton.setEnabled(false);
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        String guess = userGuess.getText().toString().trim();

        if (guess.equalsIgnoreCase(mCountry.getName())) {
            correctGuess = true;
            enableLearnButton();
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            attemptsLeft--;
            updateGuessText();
            if (attemptsLeft > 0) {
                Toast.makeText(this, "Incorrect! " + attemptsLeft + " tries left.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect! The correct answer was: " + mCountry.getName(), Toast.LENGTH_LONG).show();
                enableLearnButton();
            }
        }
    }

    private void enableLearnButton() {
        learnButton.setEnabled(true);
    }

    private void updateGuessText() {
        guessAttempt.setText("Guess Attempt: " + (3 - attemptsLeft) + "/3");
    }

    private void moveToNextScreen() {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra("COUNTRY_NAME", mCountry.getName());
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_next) {
            loadUnlearnedRandomCountry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
