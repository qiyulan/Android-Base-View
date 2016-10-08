package moe.xing.baseview;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

/**
 * Created by Hehanbo on 2016/5/17 0017.
 * <p>
 * RecyclerView 的基础适配器
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseRecyclerViewAdapter<T, R extends RecyclerView.ViewHolder> extends BaseSortedRVAdapter<T, R> {

    private int addition = 0;

    public BaseRecyclerViewAdapter(Class<T> kClass) {
        setDatas(new SortedList<>(kClass, new SortedListAdapterCallback<T>(this) {
            @Override
            public int compare(T o1, T o2) {
                return 0;
            }

            @Override
            public boolean areContentsTheSame(T oldItem, T newItem) {
                return false;
            }

            @Override
            public boolean areItemsTheSame(T item1, T item2) {
                return false;
            }
        }));
    }
}
