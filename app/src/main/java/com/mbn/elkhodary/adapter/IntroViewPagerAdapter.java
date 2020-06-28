package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.model.Intro;

import java.util.ArrayList;
import java.util.List;


public class IntroViewPagerAdapter extends PagerAdapter {
    private List<Intro> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Activity activity;
    private int length;

    public IntroViewPagerAdapter(Activity activity) {
        this.activity = activity;
        list = new Intro().getIntroList(activity);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_intro_pager, container, false);
        container.addView(view);

        ImageView ivIntro = view.findViewById(R.id.ivIntro);
        TextViewRegular tvTitle = view.findViewById(R.id.tvTitle);
        TextViewLight tvDescription = view.findViewById(R.id.tvDescription);
        ivIntro.setImageResource(list.get(position).image);
        tvDescription.setText(list.get(position).description);
        tvTitle.setText(list.get(position).title);

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}

