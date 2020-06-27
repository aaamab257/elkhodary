package com.mbn.elkhodary.activity;

import android.graphics.Color;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.GetApiWithoutOauth;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mbn.elkhodary.customview.GridSpacingItemDecoration;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.adapter.BlogAdapter;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.BlogPost;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.mbn.elkhodary.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {


    @BindView(R.id.rvBlog)
    RecyclerView rvBlog;


    @BindView(R.id.progress_wheel)
    ProgressWheel progress_wheel;

    @BindView(R.id.llProgress)
    LinearLayout llProgress;

    private BlogAdapter blogAdapter;
    private List<BlogPost> blogPostsList = new ArrayList<>();
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    Boolean setNoItemFound = false;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        ButterKnife.bind(this);
        settvImage();
        hideSearchNotification();
        setToolbarTheme();
        showBackButton();
        setAdapter();
        setScreenLayoutDirection();
        getBlog(true);

    }

    public void setAdapter() {
        blogAdapter = new BlogAdapter(this, this);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        rvBlog.setLayoutManager(mLayoutManager);
        rvBlog.setAdapter(blogAdapter);
        rvBlog.setNestedScrollingEnabled(false);
        rvBlog.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(20), true));
        rvBlog.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            if (setNoItemFound != true) {
                                loading = false;
                                page = page + 1;
                                Log.e("End ", "Last Item Wow  and page no:- " + page);
                                llProgress.setVisibility(View.VISIBLE);
                                progress_wheel.setBarColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
                                getBlog(false);
                                //Do pagination.. i.e. fetch new data
                            }
                        }
                    }
                }
            }
        });
    }

    public void getBlog(boolean isDialogShow) {
        if (Utils.isInternetConnected(this)) {
            if (isDialogShow) {
                showProgress("");
            }

            GetApiWithoutOauth getApi = new GetApiWithoutOauth(this, RequestParamUtils.getBlog, this, getlanuage());
            String strURl = new URLS().WOO_BLOG_URL + "?page=" + page;

            getApi.callGetApi(strURl);
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onItemClick(int position, String value, int outerpos) {

    }

    @Override
    public void onResponse(String response, String methodName) {
        dismissProgress();
        if (response != null && response.length() > 0) {
            try {

                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);

                    //Convert json response into gson and made model class
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    BlogPost blogPostRider = gson.fromJson(
                            object.toString(), new TypeToken<BlogPost>() {
                            }.getType());
                    blogPostsList.add(blogPostRider);
                }
                loading = true;

                blogAdapter.addAll(blogPostsList);

            } catch (JSONException e) {
                Log.e("Json Exception is ", e.getMessage());
            }
        }
        llProgress.setVisibility(View.GONE);
    }
}
