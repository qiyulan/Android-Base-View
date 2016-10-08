package moe.xing.baseview;

import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by hehanbo on 16-10-8.
 * <p>
 * sorted list rv adapter
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseSortedRVAdapter<T, R extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<R> {

    protected SortedList<T> datas;
    private int addition = 0;

    public BaseSortedRVAdapter(Class<T> kClass, SortedList.Callback<T> callback) {
        this.datas = new SortedList<>(kClass, callback);
    }

    BaseSortedRVAdapter() {
    }

    public int getAddition() {
        return addition;
    }

    public void setAddition(int addition) {
        this.addition = addition;
    }

    @Override
    public int getItemCount() {
        return datas == null ? addition : addition + datas.size();
    }

    @Nullable
    public T getItem(int position) {
        if (position <= datas.size()) {
            return datas.get(position);
        } else {
            return null;
        }
    }

    public void addData(T data) {
        datas.add(data);
    }

    public void addData(@Nullable List<T> dataList) {
        if (dataList == null) {
            return;
        }
        int start = datas.size();
        datas.addAll(dataList);
    }

    public void removeDate(T data) {
        int location = datas.indexOf(data);

        if (location != -1) {
            datas.remove(data);
        }
    }

    public void removeAllDate() {
        datas.clear();
    }

    public int getDateSize() {
        return datas.size();
    }

    public SortedList<T> getDatas() {
        return datas;
    }

    public void setDatas(SortedList<T> datas) {
        this.datas = datas;
    }
}
