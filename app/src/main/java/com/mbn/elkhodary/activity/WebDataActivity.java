package com.mbn.elkhodary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.RequestParamUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebDataActivity extends BaseActivity {


    private static final String TAG = "WebDataActivity";

    @BindView(R.id.webview)
    WebView webview;

    private String webdata, webtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_data);
        ButterKnife.bind(this);
        hideSearchNotification();
        setToolbarTheme();
        showBackButton();
        getIntentData();
        setScreenLayoutDirection();


        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        showProgress("");

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "Finished loading URL: " + url);
                dismissProgress();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebDataActivity.this, "Error :" + description, Toast.LENGTH_SHORT).show();
                dismissProgress();
            }
        });
        webview.loadUrl(webdata);
    }

    private void getIntentData() {

        Intent intent = getIntent();

        if (intent.hasExtra(RequestParamUtils.WebData)) {
            webdata = intent.getStringExtra(RequestParamUtils.WebData);
            webtitle = intent.getStringExtra(RequestParamUtils.WebTitle);
            settvTitle(webtitle);
        } else {
            webdata = "";
        }
    }
}
