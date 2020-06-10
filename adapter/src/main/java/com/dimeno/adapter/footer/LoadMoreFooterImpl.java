package com.dimeno.adapter.footer;

import android.view.View;

import com.dimeno.adapter.R;
import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.meta.LoadMoreState;

/**
 * LoadMoreFooterImpl
 * Created by wangzhen on 2020/6/10.
 */
public class LoadMoreFooterImpl extends LoadMoreFooter implements View.OnClickListener {
    private View mContainerLoading;
    private View mContainerNoMore;
    private View mContainerError;

    public LoadMoreFooterImpl(OnLoadMoreCallback callback) {
        super(callback);
    }

    @Override
    public int layout() {
        return R.layout.item_load_more_layout;
    }

    @Override
    public void onViewCreated(View itemView) {
        mContainerLoading = itemView.findViewById(R.id.container_loading);
        mContainerNoMore = itemView.findViewById(R.id.container_no_more);
        mContainerError = itemView.findViewById(R.id.container_error);
        mContainerError.setOnClickListener(this);
    }

    @Override
    public void setState(int state) {
        mState = state;
        mContainerLoading.setVisibility(mState == LoadMoreState.LOADING ? View.VISIBLE : View.GONE);
        mContainerNoMore.setVisibility(mState == LoadMoreState.NO_MORE ? View.VISIBLE : View.GONE);
        mContainerError.setVisibility(mState == LoadMoreState.ERROR ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.container_error) {
            loadMore();
        }
    }
}
