package com.mbn.elkhodary.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyashop.library.apicall.Ciyashop;
import com.ciyashop.library.apicall.ConstantValue;
import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.adapter.SearchHomeAdapter;
import com.mbn.elkhodary.customview.edittext.EditTextRegular;
import com.mbn.elkhodary.customview.textview.TextViewBold;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.helper.DatabaseHelper;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.javaclasses.FilterSelectedList;
import com.mbn.elkhodary.model.CategoryList;
import com.mbn.elkhodary.model.SearchLive;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.mbn.elkhodary.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFromHomeActivity extends BaseActivity implements OnItemClickListner {

    @BindView(R.id.etSearch)
    EditTextRegular etSearch;

    @BindView(R.id.rvSearch)
    RecyclerView rvSearch;

    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    @BindView(R.id.tvEmptyTitle)
    TextViewBold tvEmptyTitle;

    @BindView(R.id.tvContinueShopping)
    TextViewRegular tvContinueShopping;


    @BindView(R.id.svHome)
    NestedScrollView svHome;


    private SearchHomeAdapter searchHomeAdapter;
    private DatabaseHelper databaseHelper;
    private boolean isApiCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_from_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        }
        setToolbarTheme();
        databaseHelper = new DatabaseHelper(SearchFromHomeActivity.this);
        ButterKnife.bind(this);
        setScreenLayoutDirection();
        showBackButton();
        searchClick();
        setFilter();
        setSearchAdapter();
        setBottomBar("search", svHome);


    }

    public void setFilter() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
//                filter(s.toString());


                if (s.toString().length() >= 3) {
                    if (etSearch.getText().toString().toLowerCase().equals(ConstantValue.FLAGSTRINGVALUE)) {
                        Log.e("Condition", " become true");
                        new Ciyashop(SearchFromHomeActivity.this).setFlag(new Ciyashop(SearchFromHomeActivity.this).getPreferences(), true);
                    }
                    getCategoryListData();
                }


                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });


    }


    public void filter(String text) {
        List<SearchLive> temp = new ArrayList();
        for (SearchLive d : databaseHelper.getSearchHistoryList()) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.contains(text)) {
                temp.add(d);
            }
        }
//        update recyclerview
        searchHomeAdapter.updateList(temp);
    }


    public void setSearchAdapter() {
        searchHomeAdapter = new SearchHomeAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearch.setLayoutManager(mLayoutManager);
        rvSearch.setAdapter(searchHomeAdapter);
        rvSearch.setNestedScrollingEnabled(false);
        if (databaseHelper.getSearchHistoryList() != null) {
            searchHomeAdapter.addAll(databaseHelper.getSearchHistoryList());
        }
        if (searchHomeAdapter.getItemCount() == 0) {
            setEmptyLayout(true);
            searchHomeAdapter.addAll(new ArrayList<SearchLive>());
        } else {
            setEmptyLayout(false);
        }

    }


    public void searchClick() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    performSearch();
                    try {
                        logSearchedEvent("Shopping", etSearch.getText().toString(), true);
                    } catch (Exception e) {

                    }


                    Intent intent = new Intent(SearchFromHomeActivity.this, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.SEARCH, etSearch.getText().toString());
                    startActivity(intent);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!databaseHelper.getSearchItem(etSearch.getText().toString()) && etSearch.getText().toString().length() > 0) {
                                databaseHelper.addToSearchHistory(etSearch.getText().toString());
                            }

                            if (databaseHelper.getSearchHistoryList() != null) {
                                searchHomeAdapter.addAll(databaseHelper.getSearchHistoryList());
                            }
                            etSearch.setText("");
                        }
                    }, 200);


                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public void onItemClick(int position, String name, int id) {
        if (isApiCalled) {
            getProductDetail(id + "");
        } else {
            Intent intent = new Intent(this, CategoryListActivity.class);
            intent.putExtra(RequestParamUtils.SEARCH, name);
            intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
            startActivity(intent);
        }


    }

    public void getCategoryListData() {
        if (Utils.isInternetConnected(this)) {
            PostApi postApi = new PostApi(SearchFromHomeActivity.this, RequestParamUtils.getCategoryListData, this, getlanuage());
            try {
                JSONObject jsonObject;
                if (FilterSelectedList.filterJson.equals("")) {
                    jsonObject = new JSONObject();
                } else {
                    jsonObject = new JSONObject(FilterSelectedList.filterJson);
                }
                jsonObject.put(RequestParamUtils.SEARCH, etSearch.getText().toString());
                jsonObject.put(RequestParamUtils.ISAPPVALIDATION, new Ciyashop(SearchFromHomeActivity.this).getPreferences());
                postApi.callPostApi(new URLS().LIVESEARCH + getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }
    }

    public void getProductDetail(String productId) {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, "getProductDetail", this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.INCLUDE, productId);
                postApi.callPostApi(new URLS().PRODUCT_URL + getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResponse(String response, String methodName) {

        if (methodName.equals(RequestParamUtils.getCategoryListData)) {
            isApiCalled = true;
            if (response != null && response.length() > 0) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    List<SearchLive> searchList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String name = object.getString(RequestParamUtils.name);
                        if (name != null && name.length() != 0) {

                            searchList.add(new SearchLive(object.getInt(RequestParamUtils.ID)
                                    , object.getString(RequestParamUtils.name)));
                        }
                    }
                    if (searchList.size() == 0) {
                        setEmptyLayout(true);
                        searchHomeAdapter.addAll(new ArrayList<SearchLive>());
                    } else {
                        setEmptyLayout(false);
                        searchHomeAdapter.addAll(searchList);
                    }

                } catch (Exception e) {
                    Log.e("Exception is ", e.getMessage());
                    setEmptyLayout(true);

                }
            }
        }
        if (methodName.equals("getProductDetail")) {
            if (response != null && response.length() > 0) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    CategoryList categoryListRider = new Gson().fromJson(
                            jsonArray.get(0).toString(), new TypeToken<CategoryList>() {
                            }.getType());
                    Constant.CATEGORYDETAIL = categoryListRider;

                    if (categoryListRider.type.equals("external")) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(categoryListRider.externalUrl));
                        startActivity(browserIntent);
                    } else {
                        Intent intent = new Intent(this, ProductDetailActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }
                dismissProgress();
            }
        }
    }

    public void setEmptyLayout(boolean isEMpty) {
        if (isEMpty) {
            llEmpty.setVisibility(View.VISIBLE);
            rvSearch.setVisibility(View.GONE);
            tvEmptyTitle.setText(R.string.search_list_empty);
        } else {
            llEmpty.setVisibility(View.GONE);
            rvSearch.setVisibility(View.VISIBLE);
        }
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
        if (!databaseHelper.getSearchItem(etSearch.getText().toString()) && etSearch.getText().toString().length() > 0) {
            databaseHelper.addToSearchHistory(etSearch.getText().toString());
        }
    }
}
