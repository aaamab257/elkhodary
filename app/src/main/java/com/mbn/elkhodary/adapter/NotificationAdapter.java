package com.mbn.elkhodary.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewMedium;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Notification;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 16-11-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecentViewHolder> {

    private List<Notification.Datum> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public NotificationAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }


    public void addAll(List<Notification.Datum> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);

        return new RecentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, final int position) {

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListner.onItemClick(position, list.get(position).pushMetaId, list.size());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.tvNotificationTitle.setText(list.get(position).msg + "");
        holder.tvNotificationDesc.setText(list.get(position).customMsg + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvNotificationTitle)
        TextViewMedium tvNotificationTitle;

        @BindView(R.id.tvNotificationDesc)
        TextViewLight tvNotificationDesc;

        public RecentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - height_value * 2;
        height = width / 2 + height_value;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
