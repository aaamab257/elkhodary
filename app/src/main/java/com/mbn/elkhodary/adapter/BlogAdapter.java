package com.mbn.elkhodary.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.activity.BlogDescriptionActivity;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.helper.DatabaseHelper;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.BlogPost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogHolder> {

    private List<BlogPost> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private DatabaseHelper databaseHelper;


    public BlogAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        databaseHelper = new DatabaseHelper(activity);
    }

    public void addAll(List<BlogPost> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public BlogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog, parent, false);

        return new BlogHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BlogHolder holder, final int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        SimpleDateFormat newFormet = new SimpleDateFormat("MMMM dd,yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date1 = null;

        try {
            date1 = sdf.parse(list.get(position).dateGmt);
            String formattedDate = newFormet.format(date1);
            holder.tvBlogDate.setText(formattedDate);
        } catch (ParseException e) {
            Log.e("Date Exception is ", e.getMessage());
        }

        if (list.get(position).featuredImageSrc != null && list.get(position).featuredImageSrc.medium != null) {

            Glide.with(activity)
                    .load(list.get(position).featuredImageSrc.medium)
                    .asBitmap().format(DecodeFormat.PREFER_ARGB_8888)
                    .error(R.drawable.no_image_available)
                    .into(holder.ivBlog);

        } else {
            holder.ivBlog.setImageResource(R.drawable.no_image_available);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvBlogTitle.setText(Html.fromHtml(list.get(position).title.rendered, Html.FROM_HTML_MODE_COMPACT));
            holder.tvBlogContent.setText(Html.fromHtml(list.get(position).content.rendered, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvBlogTitle.setText(Html.fromHtml(list.get(position).title.rendered));
            holder.tvBlogContent.setText(Html.fromHtml(list.get(position).content.rendered));
        }

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\n" + list.get(position).title.rendered + "\n\n";
                    shareMessage = shareMessage + list.get(position).link + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    activity.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    Log.e("Exception is ", e.getMessage());
                }
            }
        });

        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BlogDescriptionActivity.class);
                intent.putExtra("date", holder.tvBlogDate.getText().toString());
                intent.putExtra("id", list.get(position).id);
                intent.putExtra("name", list.get(position).title.rendered);
                intent.putExtra("description", list.get(position).content.rendered);
                intent.putExtra("link", list.get(position).link);
                if (list.get(position).featuredImageSrc != null) {
                    intent.putExtra("image", list.get(position).featuredImageSrc.medium);
                }

                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class BlogHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvBlogDate)
        TextViewRegular tvBlogDate;

        @BindView(R.id.ivBlog)
        ImageView ivBlog;

        @BindView(R.id.tvBlogTitle)
        TextViewLight tvBlogTitle;

        @BindView(R.id.tvCategory)
        TextViewRegular tvCategory;

        @BindView(R.id.tvBlogContent)
        TextViewLight tvBlogContent;

        @BindView(R.id.ivShare)
        ImageView ivShare;

        @BindView(R.id.tvReadMore)
        TextViewRegular tvReadMore;


        public BlogHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}