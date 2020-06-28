package com.mbn.elkhodary.customview;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created by Bhumi Shah on 11/9/2017.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);

    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
