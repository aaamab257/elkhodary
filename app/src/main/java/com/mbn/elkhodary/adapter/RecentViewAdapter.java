package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.ProductDetailActivity;
import com.mbn.elkhodary.customview.MaterialRatingBar;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.CategoryList;
import com.mbn.elkhodary.utils.BaseActivity;
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

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.RecentViewHolde> {
    MediaPlayer mp ;
    private List<CategoryList> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public RecentViewAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<CategoryList> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    @Override
    public RecentViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_view, parent, false);

        return new RecentViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(RecentViewHolde holder, final int position) {
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mp = MediaPlayer.create(activity , R.raw.click_sound);
                //mp.start();
                if (list.get(position).type.equals(RequestParamUtils.external)) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).externalUrl));
                    activity.startActivity(browserIntent);
                } else {
                    Constant.CATEGORYDETAIL = list.get(position);
                    Intent intent = new Intent(activity, ProductDetailActivity.class);
                    intent.putExtra(RequestParamUtils.ID, list.get(position).id);
                    intent.putExtra(RequestParamUtils.POSITION, position);
                    activity.startActivity(intent);
                }
            }
        });

        if(list.get(position).appthumbnail!=null) {
            Picasso.with(activity).load(list.get(position).appthumbnail)
                    .error(R.drawable.no_image_available)
                    .into(holder.ivImage);
        }else {
            holder.ivImage.setImageResource(R.drawable.no_image_available);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.
                os.Build.VERSION_CODES.N) {
            holder.tvName.setText(Html.fromHtml(list.get(position).name + "", Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tvName.setText(Html.fromHtml(list.get(position).name + ""));
        }

        holder.tvPrice.setTextSize(15);
        if (list.get(position).priceHtml != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml + "", Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml) + "");
            }
        holder.tvPrice.setTextSize(15);

        ((BaseActivity) activity).setPrice(holder.tvPrice, holder.tvPrice1, list.get(position).priceHtml);

        if (!list.get(position).averageRating.equals("") && list.get(position).averageRating != null) {
            holder.ratingBar.setRating(Float.parseFloat(list.get(position).averageRating));
        }else {
            holder.ratingBar.setRating(0);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() > 5) {
            return 5;
        }
        return list.size();
    }

    public class RecentViewHolde extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewLight tvName;

        @BindView(R.id.tvPrice)
        TextViewRegular tvPrice;

        @BindView(R.id.tvPrice1)
        TextViewRegular tvPrice1;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.ratingBar)
        MaterialRatingBar ratingBar;

        public RecentViewHolde(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}