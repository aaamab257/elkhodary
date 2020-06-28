package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.CategoryListActivity;
import com.mbn.elkhodary.customview.textview.TextViewBold;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Home;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Home.CategoryBanner> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public CategoryAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.CategoryBanner> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.llMain.getLayoutParams().width = width;
        holder.llMain.getLayoutParams().height = height;
        if (position == 0) {
            holder.tvLeft.setVisibility(View.VISIBLE);
            holder.tvRight.setVisibility(View.GONE);
        } else if (position == list.size() - 1) {
            holder.tvLeft.setVisibility(View.GONE);
            holder.tvRight.setVisibility(View.VISIBLE);
        } else {
            holder.tvLeft.setVisibility(View.GONE);
            holder.tvRight.setVisibility(View.GONE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvName.setText(Html.fromHtml(list.get(position).catBannersTitle, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvName.setText(Html.fromHtml(list.get(position).catBannersTitle));
        }

        if (list.get(position).catBannersImageUrl != null) {
            Picasso.with(activity).load(list.get(position).catBannersImageUrl)
                    .error(R.drawable.no_image_available)
                    .into(holder.ivImage);
        } else {
            holder.ivImage.setImageResource(R.drawable.no_image_available);
        }


        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CategoryListActivity.class);
                intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).catBannersCatId);
                intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - height_value;
        height = width - height_value;
        width = width + width / 18;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvLeft)
        TextViewLight tvLeft;

        @BindView(R.id.tvRight)
        TextViewLight tvRight;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.tvName)
        TextViewBold tvName;


        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}