package moe.xing.baseview;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Hehanbo on 2016/7/27 0027.
 */

public class StatusRecyclerViewAdapter<T> extends RecyclerViewAdapterWrapper {


    public static final int STATE_NORMAL = 0;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 3;
    public static final int TYPE_EMPTY = 1001;
    public static final int TYPE_ERROR = 1002;
    public static final int TYPE_LOADING = 1003;
    @LayoutRes
    private int emptyViewID = 0, loadingViewID = 0, errorViewID = 0;
    @NonNull
    private BaseRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> mAdapter;
    private int state = STATE_NORMAL;

    public StatusRecyclerViewAdapter(@NonNull BaseRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> wrapped,
                                     @NonNull Context context) {
        super(wrapped);
        this.state = STATE_LOADING;
        this.mAdapter = wrapped;
    }

    public void setEmptyViewID(int emptyViewID) {
        this.emptyViewID = emptyViewID;
    }

    public void setLoadingViewID(int loadingViewID) {
        this.loadingViewID = loadingViewID;
    }

    public void setErrorViewID(int errorViewID) {
        this.errorViewID = errorViewID;
    }

    @State
    public int getState() {
        return state;
    }

    public void setState(@State int state) {
        this.state = state;
        getWrappedAdapter().notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public void setList(@Nullable List<T> list) {
        if (list == null) {
            setState(STATE_ERROR);
        } else if (list.size() == 0) {
            setState(STATE_EMPTY);
            mAdapter.removeAllDate();
        } else {
            setState(STATE_NORMAL);
            mAdapter.removeAllDate();
            mAdapter.addData(list);
        }
    }

    public void deleteItem(T item) {
        mAdapter.removeDate(item);
        if (mAdapter.getItemCount() == 0) {
            setState(STATE_EMPTY);
        }
    }

    @Override
    public int getItemCount() {
        switch (state) {
            case STATE_EMPTY:
            case STATE_ERROR:
            case STATE_LOADING:
                return 1;
        }
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        switch (state) {
            case STATE_EMPTY:
                return TYPE_EMPTY;
            case STATE_ERROR:
                return TYPE_ERROR;
            case STATE_LOADING:
                return TYPE_LOADING;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    public BaseRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> getAdapter() {
        return mAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_EMPTY:
                int empty = R.layout.view_empty;
                if (emptyViewID != 0) {
                    empty = emptyViewID;
                }
                View emptyView = LayoutInflater.from(parent.getContext()).inflate(empty, null);
                return new SimpleViewHolder(emptyView);
            case TYPE_ERROR:
                int error = R.layout.view_error;
                if (errorViewID != 0) {
                    error = errorViewID;
                }
                View errorView = LayoutInflater.from(parent.getContext()).inflate(error, null);
                return new SimpleViewHolder(errorView);
            case TYPE_LOADING:
                int loading = R.layout.view_loading;
                if (loadingViewID != 0) {
                    loading = loadingViewID;
                }
                View loadingView = LayoutInflater.from(parent.getContext()).inflate(loading, null);
                return new SimpleViewHolder(loadingView);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (state) {
            case STATE_EMPTY:
            case STATE_ERROR:
            case STATE_LOADING:
                onBindStatueViewHolder(holder, position);
                break;
            case STATE_NORMAL:
                super.onBindViewHolder(holder, position);
                break;
        }
    }

    public void onBindStatueViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @IntDef({STATE_NORMAL, STATE_EMPTY, STATE_ERROR, STATE_LOADING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

}
