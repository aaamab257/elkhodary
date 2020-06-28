package com.mbn.elkhodary.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.GetApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.adapter.DownloadAdapter;
import com.mbn.elkhodary.customview.textview.TextViewBold;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Download;
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

public class DownloadActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    @BindView(R.id.rvDownload)
    RecyclerView rvDownload;

    @BindView(R.id.tvEmptyTitle)
    TextViewBold tvEmptyTitle;

    @BindView(R.id.tvEmptyDesc)
    TextViewLight tvEmptyDesc;

    @BindView(R.id.tvContinueShopping)
    TextViewRegular tvContinueShopping;

    DownloadAdapter downloadAdapter;

    List<Download> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        setDownloadAdapter();
        getDownloadProducts();
        settvTitle(getString(R.string.download));
        setToolbarTheme();
        setScreenLayoutDirection();
        showBackButton();
        hideSearchNotification();
        setThemeColor();
    }

    void setDownloadAdapter() {
        downloadAdapter = new DownloadAdapter(this, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDownload.setLayoutManager(mLayoutManager);
        rvDownload.setAdapter(downloadAdapter);
        rvDownload.setNestedScrollingEnabled(false);
    }

    public void setThemeColor() {
        TextViewRegular tvContinueShopping = findViewById(R.id.tvContinueShopping);
        ImageView ivGo = findViewById(R.id.ivGo);
        tvContinueShopping.setTextColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(5, Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        tvContinueShopping.setBackground(gradientDrawable);
        ivGo.setColorFilter(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));

    }

    @Override
    public void onItemClick(int position, String value, int outerpos) {

    }

    void getDownloadProducts() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            GetApi getApi = new GetApi(this, RequestParamUtils.getDownloads, this,getlanuage());
            String customerId = getPreferences().getString(RequestParamUtils.ID, "");
//        showProgress("");
            getApi.callGetApi(new URLS().WOO_MAIN_URL + new URLS().WOO_CUSTOMERS + "/" + customerId + "/" + new URLS().WOO_DOWNLOADS_URL);
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
        dismissProgress();
        if (response == null && response.length() < 0) {
            return;
        } else if (methodName.endsWith(RequestParamUtils.getDownloads)) {
            Log.e("getDownload", response);
            try {

                JSONArray jsonArray = new JSONArray(response);
                list = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    String jsonResponse = jsonArray.get(i).toString();
                    Download downloadListRider = new Gson().fromJson(
                            jsonResponse, new TypeToken<Download>() {
                            }.getType());
                    list.add(downloadListRider);
                }
                downloadAdapter.addAll(list);
                if (list.size() == 0) {
                    showEmptyLayout();
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                dismissProgress();

            } catch (Exception e) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("message").equals("No product found")) {
//                        setNoItemFound = true;
                        if (rvDownload.getAdapter().getItemCount() == 0) {
                            showEmptyLayout();
                        }
                    }
                } catch (JSONException e1) {
                    Log.e("noproductJSONException", e1.getMessage());
                }
                Log.e(methodName + "Gson Exception is ", e.getMessage());
                dismissProgress();
            }

        }
    }

    public void showEmptyLayout() {
        llEmpty.setVisibility(View.VISIBLE);
        tvEmptyTitle.setText(getString(R.string.no_product_found));
        tvContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDownloadProducts();
    }
}
