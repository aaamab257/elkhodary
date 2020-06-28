package com.mbn.elkhodary.model;

import android.app.Activity;

import com.mbn.elkhodary.R;

import java.util.ArrayList;
import java.util.List;

public class Intro {

    public String title, description;
    public int image;
    public List<Intro> introList = new ArrayList<>();
    public Activity activity;

    public List<Intro> getIntroList(Activity activity) {
        this.activity = activity;
        addFirstSlider();
        addSecondSlider();
        addThirdSlider();
        return introList;
    }

    public void addFirstSlider() {
        Intro intro = new Intro();
        intro.title = activity.getResources().getString(R.string.title_intro_one);
        intro.image = R.drawable.intro_one;
        intro.description = activity.getResources().getString(R.string.intro_one);
        introList.add(intro);
    }

    public void addSecondSlider() {
        Intro intro = new Intro();
        intro.title = activity.getResources().getString(R.string.title_intro_two);
        intro.image = R.drawable.intro_two;
        intro.description = activity.getResources().getString(R.string.intro_two);
        introList.add(intro);
    }

    public void addThirdSlider() {
        Intro intro = new Intro();
        intro.title = activity.getResources().getString(R.string.title_intro_three);
        intro.image = R.drawable.intro_three;
        intro.description = activity.getResources().getString(R.string.intro_three);
        introList.add(intro);
    }
}
