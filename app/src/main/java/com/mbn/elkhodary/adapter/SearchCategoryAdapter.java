package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.CategoryListActivity;
import com.mbn.elkhodary.activity.SearchCategoryInnerListActivity;
import com.mbn.elkhodary.activity.SearchCategoryListActivity;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Home;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.CategoryViewHolder> implements OnItemClickListner {

    private final int REQUEST_CODE = 101;
    SearchInnerCategoryAdapter searchInnerCategoryAdapter;
    private List<Home.AllCategory> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private String from = "";


    public SearchCategoryAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.AllCategory> list) {
        this.list = list;

        notifyDataSetChanged();
    }


    public void setFrom(String from) {
        if (from != null) {
            this.from = from;
        }

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_catgory, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        if (from.equals(RequestParamUtils.filter)) {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).id + "");
                    intent.putExtra(RequestParamUtils.SEARCH, SearchCategoryListActivity.search);
                    intent.putExtra(RequestParamUtils.ORDER_BY, SearchCategoryListActivity.sortBy);
                    intent.putExtra(RequestParamUtils.POSITION, SearchCategoryListActivity.sortPosition);
                    activity.setResult(RESULT_OK, intent);
                    activity.finish();
                }
            });

        } else {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, SearchCategoryInnerListActivity.class);
                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).id);
                    activity.startActivity(intent);
                }
            });


        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvName.setText(Html.fromHtml(list.get(position).name, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvName.setText(Html.fromHtml(list.get(position).name));
        }

        if (!list.get(position).image.src.equals("")) {
            Picasso.with(activity).load(list.get(position).image.src + "").into(holder.ivImage);
            holder.ivImage.setVisibility(View.VISIBLE);
        } else {
            holder.ivImage.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewLight tvName;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}