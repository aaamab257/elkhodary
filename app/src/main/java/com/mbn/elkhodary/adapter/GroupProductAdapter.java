package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.CartActivity;
import com.mbn.elkhodary.activity.ProductDetailActivity;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.helper.DatabaseHelper;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Cart;
import com.mbn.elkhodary.model.CategoryList;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Config;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.CustomToast;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class GroupProductAdapter extends RecyclerView.Adapter<GroupProductAdapter.GroupProductViewHolder> {

    private  List<CategoryList> list = new ArrayList<>();
    private  Activity           activity;
    private  OnItemClickListner onItemClickListner;
    private DatabaseHelper     databaseHelper;
    private CustomToast     toast;

    public GroupProductAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        databaseHelper = new DatabaseHelper(activity);
        this.toast = new CustomToast(activity);

    }

    public void addAll(List<CategoryList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<CategoryList> getList() {
        return list;
    }

    @Override
    public GroupProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_product, parent, false);

        return new GroupProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GroupProductViewHolder holder, final int position) {
        holder.ivCart.setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        if (position == list.size() - 1) {
            holder.tvDivider.setVisibility(View.GONE);
        } else {
            holder.tvDivider.setVisibility(View.VISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvName.setText(Html.fromHtml(list.get(position).name, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvName.setText(Html.fromHtml(list.get(position).name));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml, Html.FROM_HTML_MODE_COMPACT));

        } else {
            holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml));
        }
        holder.tvPrice.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        holder.tvPrice.setTextSize(15);

        ((BaseActivity) activity).setPrice(holder.tvPrice, holder.tvPrice1, list.get(position).priceHtml);


        holder.tvDetail.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));


        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.CATEGORYDETAIL = list.get(position);
                Intent intent = new Intent(activity, ProductDetailActivity.class);
                intent.putExtra(RequestParamUtils.ID, list.get(position).id);
                activity.startActivity(intent);

            }
        });

        if (list.get(position).images.size() > 0) {
            holder.ivImage.setVisibility(View.VISIBLE);
            Picasso.with(activity).load(list.get(position).images.get(0).src).into(holder.ivImage);
        } else {
            holder.ivImage.setVisibility(View.INVISIBLE);
        }

        if (Config.IS_CATALOG_MODE_OPTION) {
            holder.ivCart.setVisibility(View.GONE);
        } else {
            holder.ivCart.setVisibility(View.VISIBLE);
        }

        if (!list.get(position).inStock) {
            holder.ivCart.setClickable(false);
            toast.showToast(activity.getString(R.string.out_of_stock));
        }

        if (!list.get(position).type.equals("variable")) {
            if (databaseHelper != null) {
                if (databaseHelper.getProductFromCartById(list.get(position).id + "") != null) {
                    holder.ivCart.setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
                } else {
                    holder.ivCart.setColorFilter(R.color.black);
                }
            }
        }

        holder.ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).inStock) {
                    if (list.get(position).type.equals("variable")) {
                        holder.ivCart.setVisibility(View.GONE);
                    } else if (list.get(position).type.equals("simple")) {
                        holder.ivCart.setVisibility(View.VISIBLE);
                        holder.ivCart.setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
                        Cart cart = new Cart();
                        cart.setQuantity(1);
                        cart.setVariation("{}");
                        cart.setProduct(new Gson().toJson(list.get(position)));
                        cart.setVariationid(0);
                        cart.setProductid(list.get(position).id + "");
                        cart.setBuyNow(0);
                        cart.setManageStock(list.get(position).manageStock);

                        if (databaseHelper.getProductFromCartById(list.get(position).id + "") != null) {
                            databaseHelper.addToCart(cart);
                            Intent intent = new Intent(activity, CartActivity.class);
                            intent.putExtra("buynow", 0);
                            activity.startActivity(intent);
                        } else {
                            databaseHelper.addToCart(cart);
                            ((BaseActivity) activity).showCart();
                            toast = new CustomToast(activity);
                            toast.showToast(activity.getString(R.string.item_added_to_your_cart));
                            toast.showBlackbg();
                        }
                        onItemClickListner.onItemClick(position,list.get(position).id+"",11459060);
                    }
                } else {
                    toast = new CustomToast(activity);
                    toast.showToast(activity.getString(R.string.out_of_stock));
                    toast.showBlackbg();
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GroupProductViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewRegular tvName;

        @BindView(R.id.tvPrice)
        TextViewRegular tvPrice;

        @BindView(R.id.tvPrice1)
        TextViewRegular tvPrice1;

        @BindView(R.id.tvDivider)
        TextViewRegular tvDivider;

        @BindView(R.id.tvDetail)
        TextViewLight tvDetail;

        @BindView(R.id.ivCart)
        ImageView ivCart;


        public GroupProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}