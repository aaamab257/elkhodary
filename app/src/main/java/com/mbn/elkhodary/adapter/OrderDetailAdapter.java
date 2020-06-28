package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Orders;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 01-12-2017.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.RecentViewHolde> {
    private List<Orders.LineItem> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private String currencySymbol;
    private int width = 0, height = 0;

    public OrderDetailAdapter(Activity activity, OnItemClickListner onItemClickListner, String currencySymbol) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        this.currencySymbol = currencySymbol;
    }

    public void addAll(List<Orders.LineItem> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public OrderDetailAdapter.RecentViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ordered_product, parent, false);

        return new OrderDetailAdapter.RecentViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(OrderDetailAdapter.RecentViewHolde holder, final int position) {

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Constant.ORDERDETAIL = list.get(position);
//                Intent intent = new Intent(activity, OrderDetailActivity.class);
//                intent.putExtra(RequestParamUtils.ID, list.get(position).id);
//                activity.startActivity(intent);
            }
        });


        holder.tvProductName.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        holder.tvProductName.setText(list.get(position).name + " x" + list.get(position).quantity);

        try {
            holder.tvProductPrice.setText(currencySymbol + " " +  Constant.setDecimal(Double.valueOf(list.get(position).total)));
        } catch (Exception e) {
            holder.tvProductPrice.setText(currencySymbol + " " + list.get(position).total);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public class RecentViewHolde extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvProductName)
        TextViewLight tvProductName;

        @BindView(R.id.tvProductPrice)
        TextViewLight tvProductPrice;

        public RecentViewHolde(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
