package com.bignerdranch.android.worldwise;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.bignerdranch.android.worldwise.database.CountryBaseHelper;
import com.bignerdranch.android.worldwise.database.CountryDbSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CountryLab {
    private static CountryLab countryLab; // Singleton instance
    private static ArrayList<Country> mCountries; // List of countries
    private Random random;
    private Context mContext; // Context for accessing resources

    private SQLiteDatabase mDatabase;

    private CountryLab(Context context) {
        this.mContext = context;
        this.mCountries = new ArrayList<>();
        this.random = new Random();
        loadCountries();// Initialize the country list
        mContext = context.getApplicationContext();
        mDatabase = new CountryBaseHelper(mContext).getWritableDatabase();
    }

    public static CountryLab getInstance(Context context) {
        if (countryLab == null) {
            countryLab = new CountryLab(context);
        }
        return countryLab;
    }

    private void loadCountries() {
        Resources res = mContext.getResources();
        String[] usaHistoryFacts = res.getStringArray(R.array.usa_history_array);
        Topic usaHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/T6kkq-ajRBQ?si=SwbNW0p7aW5q1ZRL\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", usaHistoryFacts, false);
        String[] usaGeographyFacts = res.getStringArray(R.array.usa_geography_array);
        Topic usaGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YeWBc0eowrs?si=e0oqTtf4YLod3VtA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", usaGeographyFacts, false);
        String[] usaCultureFacts = res.getStringArray(R.array.usa_culture_array);
        Topic usaCulture = createTopic("Culture", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/atZvLpk2NgM?si=qHhqr4REcD0fViXK\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", usaCultureFacts, false);
        String[] usaCuisineRecipe = res.getStringArray(R.array.usa_cuisine_array);
        Topic usaCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yLfKbH5S3QM?si=sr0_R6htyCKlZImF\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", usaCuisineRecipe, false);
        ArrayList<Question> usaQuiz = new ArrayList<>();
        ArrayList<String> usaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.usa_q1_array)));
        String usaQ = res.getString(R.string.usa_q1);
        Question usaQ1 = new Question(usaQ, usaAns, 2);
        usaQuiz.add(usaQ1);
        usaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.usa_q2_array)));
        usaQ = res.getString(R.string.usa_q2);
        Question usaQ2 = new Question(usaQ, usaAns, 2);
        usaQuiz.add(usaQ2);
        usaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.usa_q3_array)));
        usaQ = res.getString(R.string.usa_q3);
        Question usaQ3 = new Question(usaQ, usaAns, 1);
        usaQuiz.add(usaQ3);
        usaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.usa_q4_array)));
        usaQ = res.getString(R.string.usa_q4);
        Question usaQ4 = new Question(usaQ, usaAns, 2);
        usaQuiz.add(usaQ4);
        usaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.usa_q5_array)));
        usaQ = res.getString(R.string.usa_q5);
        Question usaQ5 = new Question(usaQ, usaAns, 2);
        usaQuiz.add(usaQ5);
        mCountries.add(new Country(
                "USA",
                createTopics(usaHistory, usaGeography, usaCulture, usaCuisine),
                getDrawable(R.drawable.usa_flag_map),
                new Quiz(usaQuiz, 0, false, 0),
                false
        ));
        mCountries.remove(new Country(
                "USA",
                createTopics(usaHistory, usaGeography, usaCulture, usaCuisine),
                getDrawable(R.drawable.usa_flag_map),
                new Quiz(usaQuiz, 0, true, 4),
                true
        ));

        String[] australiaHistoryFacts = res.getStringArray(R.array.australia_history_array);
        Topic australiaHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QIDDIDS2Tjk?si=dVr031ETGT85vyN5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", australiaHistoryFacts, false);
        String[] australiaGeographyFacts = res.getStringArray(R.array.australia_geography_array);
        Topic australiaGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0-K8q5HcwsY?si=zwcioy8v893xXd7-\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", australiaGeographyFacts, false);
        String[] australiaCultureFacts = res.getStringArray(R.array.australia_culture_array);
        Topic australiaCulture = createTopic("Culture", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CXbQxgqRDc4?si=xl5n_t8lbDSydvuo\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", australiaCultureFacts, false);
        String[] australiaCuisineRecipe = res.getStringArray(R.array.australia_cuisine_array);
        Topic australiaCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4-TO4vIIqnA?si=sLMLxBU2V_uOgowG\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", australiaCuisineRecipe, false);
        ArrayList<Question> australiaQuiz = new ArrayList<>();
        ArrayList<String> australiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.australia_q1_array)));
        String australiaQ = res.getString(R.string.australia_q1);
        Question australiaQ1 = new Question(australiaQ, australiaAns, 1);
        australiaQuiz.add(australiaQ1);
        australiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.australia_q2_array)));
        australiaQ = res.getString(R.string.australia_q2);
        Question australiaQ2 = new Question(australiaQ, australiaAns, 1);
        australiaQuiz.add(australiaQ2);
        australiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.australia_q3_array)));
        australiaQ = res.getString(R.string.australia_q3);
        Question australiaQ3 = new Question(australiaQ, australiaAns, 0);
        australiaQuiz.add(australiaQ3);
        australiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.australia_q4_array)));
        australiaQ = res.getString(R.string.australia_q4);
        Question australiaQ4 = new Question(australiaQ, australiaAns, 1);
        australiaQuiz.add(australiaQ4);
        australiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.australia_q5_array)));
        australiaQ = res.getString(R.string.australia_q5);
        Question australiaQ5 = new Question(australiaQ, australiaAns, 1);
        australiaQuiz.add(australiaQ5);
        mCountries.add(new Country(
                "Australia",
                createTopics(australiaHistory, australiaGeography, australiaCulture, australiaCuisine),
                getDrawable(R.drawable.australia_flag_map),
                new Quiz(australiaQuiz, 0, false, 0),
                false
        ));
        String[] chinaHistoryFacts = res.getStringArray(R.array.china_history_array);
        Topic chinaHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cRZGo4arWEg?si=-CAyatA_vQr1YNmT\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", chinaHistoryFacts, false);
        String[] chinaGeographyFacts = res.getStringArray(R.array.china_geography_array);
        Topic chinaGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8J1TDaPKL44?si=CECjDylSzGsRXO2V\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", chinaGeographyFacts, false);
        String[] chinaCultureFacts = res.getStringArray(R.array.china_culture_array);
        Topic chinaCulture = createTopic("Culture","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/j6K_NS516U0?si=09R_A3JusIotn6Wn\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", chinaCultureFacts, false);
        String[] chinaCuisineRecipe = res.getStringArray(R.array.china_cuisine_array);
        Topic chinaCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JW4GKm8TlEs?si=RmIlBitQWckYMXdg\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", chinaCuisineRecipe, false);
        ArrayList<Question> chinaQuiz = new ArrayList<>();
        ArrayList<String> chinaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.china_q1_array)));
        String chinaQ = res.getString(R.string.china_q1);
        Question chinaQ1 = new Question(chinaQ, chinaAns, 2);
        chinaQuiz.add(chinaQ1);
        chinaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.china_q2_array)));
        chinaQ = res.getString(R.string.china_q2);
        Question chinaQ2 = new Question(chinaQ, chinaAns, 2);
        chinaQuiz.add(chinaQ2);
        chinaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.china_q3_array)));
        chinaQ = res.getString(R.string.china_q3);
        Question chinaQ3 = new Question(chinaQ, chinaAns, 0);
        chinaQuiz.add(chinaQ3);
        chinaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.china_q4_array)));
        chinaQ = res.getString(R.string.china_q4);
        Question chinaQ4 = new Question(chinaQ, chinaAns, 1);
        chinaQuiz.add(chinaQ4);
        chinaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.china_q5_array)));
        chinaQ = res.getString(R.string.china_q5);
        Question chinaQ5 = new Question(chinaQ, chinaAns, 2);
        chinaQuiz.add(chinaQ5);
        mCountries.add(new Country(
                "China",
                createTopics(chinaHistory, chinaGeography, chinaCulture, chinaCuisine),
                getDrawable(R.drawable.china_flag_map),
                new Quiz(chinaQuiz, 0, false, 0),
                false
        ));
        String[] colombiaHistoryFacts = res.getStringArray(R.array.colombia_history_array);
        Topic colombiaHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/prhRNVWQrKo?si=iBsuccX-O-PeN-ST\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", colombiaHistoryFacts, false);
        String[] colombiaGeographyFacts = res.getStringArray(R.array.colombia_geography_array);
        Topic colombiaGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wxxeFdQqfoo?si=O5sva6KAZ-gHIMK2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", colombiaGeographyFacts, false);
        String[] colombiaCultureFacts = res.getStringArray(R.array.colombia_culture_array);
        Topic colombiaCulture = createTopic("Culture","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jmMxqMWPHDw?si=GS-2TMwmXGOnKO6r\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", colombiaCultureFacts, false);
        String[] colombiaCuisineRecipe = res.getStringArray(R.array.colombia_cuisine_array);
        Topic colombiaCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/n6h5GwO6UpQ?si=QnitAI2PD6mc-S6k\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", colombiaCuisineRecipe, false);
        ArrayList<Question> colombiaQuiz = new ArrayList<>();
        ArrayList<String> colombiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colombia_q1_array)));
        String colombiaQ = res.getString(R.string.colombia_q1);
        Question colombiaQ1 = new Question(colombiaQ, colombiaAns, 1);
        colombiaQuiz.add(colombiaQ1);
        colombiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colombia_q2_array)));
        colombiaQ = res.getString(R.string.colombia_q2);
        Question colombiaQ2 = new Question(colombiaQ, colombiaAns, 1);
        colombiaQuiz.add(colombiaQ2);
        colombiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colombia_q3_array)));
        colombiaQ = res.getString(R.string.colombia_q3);
        Question colombiaQ3 = new Question(colombiaQ, colombiaAns, 2);
        colombiaQuiz.add(colombiaQ3);
        colombiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colombia_q4_array)));
        colombiaQ = res.getString(R.string.colombia_q4);
        Question colombiaQ4 = new Question(colombiaQ, colombiaAns, 0);
        colombiaQuiz.add(colombiaQ4);
        colombiaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colombia_q5_array)));
        colombiaQ = res.getString(R.string.colombia_q5);
        Question colombiaQ5 = new Question(colombiaQ, colombiaAns, 1);
        colombiaQuiz.add(colombiaQ5);
        mCountries.add(new Country(
                "Colombia",
                createTopics(colombiaHistory, colombiaGeography, colombiaCulture, colombiaCuisine),
                getDrawable(R.drawable.colombia_flag_map),
                new Quiz(colombiaQuiz, 0, false, 0),
                false
        ));
        String[] italyHistoryFacts = res.getStringArray(R.array.italy_history_array);
        Topic italyHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TfZIRavtdV0?si=yNioNN1880OIGJKc\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", italyHistoryFacts, false);
        String[] italyGeographyFacts = res.getStringArray(R.array.italy_geography_array);
        Topic italyGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N-Cmq95steI?si=KuxUWlf8rPQhsEmZ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", italyGeographyFacts, false);
        String[] italyCultureFacts = res.getStringArray(R.array.italy_culture_array);
        Topic italyCulture = createTopic("Culture","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KsxqUinURno?si=iOs_65UGm8Lc8ZsC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", italyCultureFacts, false);
        String[] italyCuisineRecipe = res.getStringArray(R.array.italy_cuisine_array);
        Topic italyCuisine = createTopic("Cuisine","<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/h0WijUHHUmA?si=5gOy-MTj2x1pzSmd\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", italyCuisineRecipe, false);
        ArrayList<Question> italyQuiz = new ArrayList<>();
        ArrayList<String> italyAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.italy_q1_array)));
        String italyQ = res.getString(R.string.italy_q1);
        Question italyQ1 = new Question(italyQ, italyAns, 2);
        italyQuiz.add(italyQ1);
        italyAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.italy_q2_array)));
        italyQ = res.getString(R.string.italy_q2);
        Question italyQ2 = new Question(italyQ, italyAns, 1);
        italyQuiz.add(italyQ2);
        italyAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.italy_q3_array)));
        italyQ = res.getString(R.string.italy_q3);
        Question italyQ3 = new Question(italyQ, italyAns, 3);
        italyQuiz.add(italyQ3);
        italyAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.italy_q4_array)));
        italyQ = res.getString(R.string.italy_q4);
        Question italyQ4 = new Question(italyQ, italyAns, 1);
        italyQuiz.add(italyQ4);
        italyAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.italy_q5_array)));
        italyQ = res.getString(R.string.italy_q5);
        Question italyQ5 = new Question(italyQ, italyAns, 1);
        italyQuiz.add(italyQ5);
        mCountries.add(new Country(
                "Italy",
                createTopics(italyHistory, italyGeography, italyCulture, italyCuisine),
                getDrawable(R.drawable.italy_flag_map),
                new Quiz(italyQuiz, 0, false, 0),
                false
        ));
        String[] mexicoHistoryFacts = res.getStringArray(R.array.mexico_history_array);
        Topic mexicoHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kOXR5rtHJbk?si=BACXmlBLUIS_7HUM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", mexicoHistoryFacts, false);
        String[] mexicoGeographyFacts = res.getStringArray(R.array.mexico_geography_array);
        Topic mexicoGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Qkl86xz3utU?si=0dAvDexaZNR9s61q\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", mexicoGeographyFacts, false);
        String[] mexicoCultureFacts = res.getStringArray(R.array.mexico_culture_array);
        Topic mexicoCulture = createTopic("Culture", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MXmo1yf6or4?si=RHYKZEHSmtRwOjyO\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", mexicoCultureFacts, false);
        String[] mexicoCuisineRecipe = res.getStringArray(R.array.mexico_cuisine_array);
        Topic mexicoCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pyEs2vmuwF4?si=DU0BfxNLCUfMIxKD\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", mexicoCuisineRecipe, false);
        ArrayList<Question> mexicoQuiz = new ArrayList<>();
        ArrayList<String> mexicoAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.mexico_q1_array)));
        String mexicoQ = res.getString(R.string.mexico_q1);
        Question mexicoQ1 = new Question(mexicoQ, mexicoAns, 1);
        mexicoQuiz.add(mexicoQ1);
        mexicoAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.mexico_q2_array)));
        mexicoQ = res.getString(R.string.mexico_q2);
        Question mexicoQ2 = new Question(mexicoQ, mexicoAns, 2);
        mexicoQuiz.add(mexicoQ2);
        mexicoAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.mexico_q3_array)));
        mexicoQ = res.getString(R.string.mexico_q3);
        Question mexicoQ3 = new Question(mexicoQ, mexicoAns, 3);
        mexicoQuiz.add(mexicoQ3);
        mexicoAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.mexico_q4_array)));
        mexicoQ = res.getString(R.string.mexico_q4);
        Question mexicoQ4 = new Question(mexicoQ, mexicoAns, 1);
        mexicoQuiz.add(mexicoQ4);
        mexicoAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.mexico_q5_array)));
        mexicoQ = res.getString(R.string.mexico_q5);
        Question mexicoQ5 = new Question(mexicoQ, mexicoAns, 3);
        mexicoQuiz.add(mexicoQ5);
        mCountries.add(new Country(
                "Mexico",
                createTopics(mexicoHistory, mexicoGeography, mexicoCulture, mexicoCuisine),
                getDrawable(R.drawable.mexico_flag_map),
                new Quiz(mexicoQuiz, 0, false, 0),
                false
        ));
        String[] nigeriaHistoryFacts = res.getStringArray(R.array.nigeria_history_array);
        Topic nigeriaHistory = createTopic("History", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fMmkmHUAAO0?si=3jagWRxowqPshXI6\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", nigeriaHistoryFacts, false);
        String[] nigeriaGeographyFacts = res.getStringArray(R.array.nigeria_geography_array);
        Topic nigeriaGeography = createTopic("Geography", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/PUNPx23snwI?si=GYgN98UIKi8V0e47\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", nigeriaGeographyFacts, false);
        String[] nigeriaCultureFacts = res.getStringArray(R.array.nigeria_culture_array);
        Topic nigeriaCulture = createTopic("Culture", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uQ8_IGM6pdI?si=SDODaGDaGTA-ZzSA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", nigeriaCultureFacts, false);
        String[] nigeriaCuisineRecipe = res.getStringArray(R.array.nigeria_cuisine_array);
        Topic nigeriaCuisine = createTopic("Cuisine", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ILI3uHpT8jI?si=yEinTDoju3XJEhM5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", nigeriaCuisineRecipe, false);
        ArrayList<Question> nigeriaQuiz = new ArrayList<>();
        ArrayList<String> nigeriaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.nigeria_q1_array)));
        String nigeriaQ = res.getString(R.string.nigeria_q1);
        Question nigeriaQ1 = new Question(nigeriaQ, nigeriaAns, 2);
        nigeriaQuiz.add(nigeriaQ1);
        nigeriaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.nigeria_q2_array)));
        nigeriaQ = res.getString(R.string.nigeria_q2);
        Question nigeriaQ2 = new Question(nigeriaQ, nigeriaAns, 1);
        nigeriaQuiz.add(nigeriaQ2);
        nigeriaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.nigeria_q3_array)));
        nigeriaQ = res.getString(R.string.nigeria_q3);
        Question nigeriaQ3 = new Question(nigeriaQ, nigeriaAns, 2);
        nigeriaQuiz.add(nigeriaQ3);
        nigeriaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.nigeria_q4_array)));
        nigeriaQ = res.getString(R.string.nigeria_q4);
        Question nigeriaQ4 = new Question(nigeriaQ, nigeriaAns, 0);
        nigeriaQuiz.add(nigeriaQ4);
        nigeriaAns = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.nigeria_q5_array)));
        nigeriaQ = res.getString(R.string.nigeria_q5);
        Question nigeriaQ5 = new Question(nigeriaQ, nigeriaAns, 0);
        nigeriaQuiz.add(nigeriaQ5);
        mCountries.add(new Country(
                "Nigeria",
                createTopics(nigeriaHistory, nigeriaGeography, nigeriaCulture, nigeriaCuisine),
                getDrawable(R.drawable.nigeria_flag_map),
                new Quiz(nigeriaQuiz, 0, false, 0),
                false
        ));
    }

    private Topic createTopic(String name, String videoUrl, String[] funFacts, boolean isViewed){
        return new Topic(name, videoUrl,funFacts, isViewed);
    }

    private ArrayList<Topic> createTopics(Topic history, Topic geography, Topic culture, Topic cuisine) {
        ArrayList<Topic> topics = new ArrayList<>();
        topics.add(history);
        topics.add(geography);
        topics.add(culture);
        topics.add(cuisine);
        return topics;
    }

    private Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    public Country getRandomCountry() {
        if (mCountries.isEmpty()) return null;
        return mCountries.get(random.nextInt(mCountries.size()));
    }

    public Country getCountryByName(String name) {
        for (Country country : mCountries) {
            if (country.getName().equalsIgnoreCase(name)) {
                return country;
            }
        }
        return null;
    }

    public void addCountry(Country country) {
        if (country != null && !mCountries.contains(country)) {
            mCountries.add(country);
        }
    }

    /*public void updateCountry(Country country) {
        if (country != null && !mCountries.contains(country) && mCountries.isCountryLearned() ) {
            mCountries.add(country);
        }
    }*/

    public boolean removeCountry(Country country) {
        return mCountries.remove(country);
    }

    public static ArrayList<Country> getCountryList() {
        return mCountries;
    }

    public void markCountryAsLearned(String countryName) {
        ContentValues values = new ContentValues();
        values.put(CountryDbSchema.CountryTable.Cols.COUNTRY_NAME, countryName);
        mDatabase.insert(CountryDbSchema.CountryTable.NAME, null, values);
    }

    public ArrayList<String> getLearnedCountryNames() {
        ArrayList<String> learnedList = new ArrayList<>();

        try (Cursor cursor = mDatabase.query(CountryDbSchema.CountryTable.NAME, new String[]{CountryDbSchema.CountryTable.Cols.COUNTRY_NAME},
                null, null, null, null, null)) {

            int index = cursor.getColumnIndex(CountryDbSchema.CountryTable.Cols.COUNTRY_NAME);
            while (cursor.moveToNext()) {
                String name = cursor.getString(index);
                learnedList.add(name);
            }
        }

        return learnedList;
    }

    public Country getRandomUnlearnedCountry() {
        ArrayList<Country> unlearnedCountries = getUnlearnedCountryList();

        if (unlearnedCountries == null || unlearnedCountries.isEmpty()) {
            return null;
        }

        return unlearnedCountries.get(new Random().nextInt(unlearnedCountries.size()));
    }

    public ArrayList<Country> getUnlearnedCountryList() {
        ArrayList<String> learnedCountries = CountryLab.getInstance(null).getLearnedCountryNames();
        ArrayList<Country> unlearnedCountries = new ArrayList<Country>();

        for (Country country : mCountries) {
            if (!learnedCountries.contains(country.getName())) {
                unlearnedCountries.add(country);
            }
        }

        Log.d("UNLEARNED_COUNTRIES", "Unlearned count: " + unlearnedCountries.size());
        return unlearnedCountries;
    }

    public void clearLearnedCountries() {
        mDatabase.delete(CountryDbSchema.CountryTable.NAME, null, null);
    }
}



