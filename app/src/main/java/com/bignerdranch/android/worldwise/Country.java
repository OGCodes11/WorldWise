package com.bignerdranch.android.worldwise;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Country {
    private String name;
    private ArrayList<Topic> topics;
    private Drawable map;
    private Quiz q;
    private boolean countryLearned;

    public Country (String name, ArrayList<Topic> topics, Drawable map, Quiz q, boolean countryLearned){
        this.name = name;
        this.topics = topics;
        this.map = map;
        this.q = q;
        this.countryLearned = countryLearned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public Drawable getMap() {
        return map;
    }

    public void setMap(Drawable map) {
        this.map = map;
    }

    public Quiz getQ() {
        return q;
    }

    public void setQ(Quiz q) {
        this.q = q;
    }

    public boolean isCountryLearned() { return countryLearned; }

    public void setCountryLearned(boolean countryLearned) { this.countryLearned = countryLearned; }
}
