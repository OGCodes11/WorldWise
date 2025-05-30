package com.bignerdranch.android.worldwise;

public class Topic {
    private String name;
    private String videoUrl;
    private String[] funFacts;
    private boolean isViewed;

    public Topic (String name, String videoUrl, String[] funFacts, boolean isViewed){
        this.name = name;
        this.videoUrl = videoUrl;
        this.funFacts = funFacts;
        this.isViewed = isViewed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String[] getFunFacts() {
        return funFacts;
    }

    public void setFunFacts(String[] funFacts) {
        this.funFacts = funFacts;
    }

    public boolean isViewed() { return isViewed; }

    public void setViewed(boolean viewed) { isViewed = viewed; }
}
