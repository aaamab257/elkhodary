package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.graphics.Color;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.javaclasses.CheckSimpleSelector;
import com.mbn.elkhodary.model.CategoryList;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class ProductSimpleAdapter extends RecyclerView.Adapter<ProductSimpleAdapter.ProductVariationViewHolder> implements OnItemClickListner {

    private List<CategoryList.Attribute> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private Map<Integer, Integer> filterTypeList = new HashMap<>();
    ProductSimpleInnerAdapter productSimpleInnerAdapter;

    public ProductSimpleAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;

    }

    public void addAll(List<CategoryList.Attribute> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public ProductVariationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_detail_variation, parent, false);

        return new ProductVariationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductVariationViewHolder holder, final int position) {
        setFilterTypeAdapter(holder.rvProductVariation, position);

        holder.tvTitle.setText(list.get(position).name);
        holder.tvTitle.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerpos) {
        if (outerpos == 0) {
            ProductColorAdapter.selectedpos = position;
        }
        CheckSimpleSelector.setSelectedItem(list.get(outerpos).name, list.get(outerpos).options.get(position));
    }


    public class ProductVariationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rvProductVariation)
        RecyclerView rvProductVariation;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;


        public ProductVariationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }


    }

    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - 20;
        height = width - height_value;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setFilterTypeAdapter(RecyclerView recyclerView, int pos) {
        productSimpleInnerAdapter = new ProductSimpleInnerAdapter(activity, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(productSimpleInnerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        productSimpleInnerAdapter.addAll(list.get(pos).options);
        productSimpleInnerAdapter.setOuterPositionValue(list.get(pos).name);
        productSimpleInnerAdapter.setOuterPosition(pos);
    }
}