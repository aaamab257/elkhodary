package com.mbn.elkhodary.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.WalletTransaction;
import com.mbn.elkhodary.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletTransectionAdapter extends RecyclerView.Adapter<WalletTransectionAdapter.WalletTransectionViewHolder> {

    private List<WalletTransaction.Transaction> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;

    public WalletTransectionAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<WalletTransaction.Transaction> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletTransectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_transection, parent, false);
        return new WalletTransectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletTransectionViewHolder holder, int position) {

        WalletTransaction.Transaction transaction = list.get(position);

        holder.tvTitle.setText(transaction.getDetails());
        if (transaction.getType().equals("credit")) {
            holder.tvAmmount.setText("+ "+ transaction.getAmount()+" "+Constant.CURRENCYSYMBOL);
            holder.tvAmmount.setTextColor(activity.getResources().getColor(R.color.green));
        } else {
            holder.tvAmmount.setText("- " +transaction.getAmount()+" "+Constant.CURRENCYSYMBOL);
            holder.tvAmmount.setTextColor(activity.getResources().getColor(R.color.red));
        }
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("m dd, yyyy");

        Date d = null;
        try {
            d = input.parse(transaction.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvDate.setText(output.format(d));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WalletTransectionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;

        @BindView(R.id.tvAmmount)
        TextViewRegular tvAmmount;

        @BindView(R.id.tvDate)
        TextViewLight tvDate;

        public WalletTransectionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
