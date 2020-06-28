package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.CategoryListActivity;
import com.mbn.elkhodary.activity.SearchCategoryInnerListActivity;
import com.mbn.elkhodary.activity.SearchCategoryListActivity;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Home;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class HomeTopCategoryAdapter extends RecyclerView.Adapter<HomeTopCategoryAdapter.CategoryViewHolder> {

    private List<Home.MainCategory> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public HomeTopCategoryAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.MainCategory> list) {
        this.list.clear();
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_top_category, parent, false);


        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {

        Home.MainCategory mainCategory = list.get(position);

        holder.llMain.getLayoutParams().width = width;
        if (holder.flImage.getLayoutParams().width > width - 10) {
            holder.flImage.getLayoutParams().width = width - 10;
            holder.flImage.getLayoutParams().height = width - 10;
        }

        if (position == list.size() - 1) {
            holder.ivImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_more_white));
        } else {

            if (mainCategory.mainCatImage != null && !mainCategory.mainCatImage.equals("")) {
                Picasso.with(activity).load(mainCategory.mainCatImage).error(R.drawable.ic_more_white).into(holder.ivImage);
            } else {
                holder.ivImage.setImageResource(R.drawable.blackround);
            }
        }
        if (list.get(position).mainCatName != null && !list.get(position).mainCatName.equals("")) {
            holder.tvName.setText(list.get(position).mainCatName);
        }

        if (position == list.size() - 1) {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, SearchCategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.from, RequestParamUtils.SEARCH);
                    activity.startActivity(intent);
                }
            });

        } else {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent intent = new Intent(activity, SearchCategoryInnerListActivity.class);
                        intent.putExtra(RequestParamUtils.CATEGORY, Integer.parseInt(list.get(position).mainCatId));
                        activity.startActivity(intent);
                    } catch (Exception e) {
                        Log.e("Category exception", e.getMessage());
                        Intent intent = new Intent(activity, CategoryListActivity.class);
                        intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).mainCatId);
                        activity.startActivity(intent);
                    }

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.flImage)
        FrameLayout flImage;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.tvName)
        TextViewRegular tvName;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getWidthAndHeight() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / list.size();

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}