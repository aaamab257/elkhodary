package com.mbn.elkhodary.activity;

import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mbn.elkhodary.R;
import com.mbn.elkhodary.adapter.SearchCategoryAdapter;
import com.mbn.elkhodary.interfaces.OnItemClickListner;
import com.mbn.elkhodary.model.Home;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCategoryListActivity extends BaseActivity implements OnItemClickListner {

    @BindView(R.id.rvSearchCategory)
    RecyclerView rvSearchCategory;

    @BindView(R.id.svHome)
    NestedScrollView svHome;


    private SearchCategoryAdapter searchCategoryAdapter;
    private Bundle bundle;
    private String from;
    public static int sortPosition;
    private List<Home.AllCategory> list = new ArrayList<>();
    public static String search, sortBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category_list);
        ButterKnife.bind(this);
        setToolbarTheme();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.all_category));
        getIntentData();
        showSearch();
        showCart();
        showBackButton();
        setSerachAdapter();
        setBottomBar("search", svHome);


    }

    public void getIntentData() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            from = bundle.getString(RequestParamUtils.from);
            search = bundle.getString(RequestParamUtils.SEARCH);
            sortBy = bundle.getString(RequestParamUtils.ORDER_BY);
            sortPosition = bundle.getInt(RequestParamUtils.POSITION);
        }
    }

// intent.putExtra(RequestParamUtils.ORDER_BY, Constant.getSortList().get(sortAdapter.getSelectedPosition()).getSyntext());
//            intent.putExtra(RequestParamUtils.POSITION,sortPosition);

    public void setSerachAdapter() {
        searchCategoryAdapter = new SearchCategoryAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearchCategory.setLayoutManager(mLayoutManager);
        rvSearchCategory.setAdapter(searchCategoryAdapter);
        rvSearchCategory.setNestedScrollingEnabled(false);
        searchCategoryAdapter.setFrom(from);
        for (int i = 0; i < Constant.MAINCATEGORYLIST.size(); i++) {
            if (Constant.MAINCATEGORYLIST.get(i).parent == 0) {
                list.add(Constant.MAINCATEGORYLIST.get(i));
            }
        }
        searchCategoryAdapter.addAll(list);

    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showCart();
    }
}
