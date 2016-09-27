package moe.xing.baseview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hehanbo on 2016/5/17 0017.
 * <p>
 * RecyclerView 的基础适配器
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseRecyclerViewAdapter<T, R extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<R> {
    protected List<T> datas = new ArrayList<>();

    private int addition = 0;

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
    public T getItem(int postion) {
        if (postion <= datas.size()) {
            return datas.get(postion);
        } else {
            return null;
        }
    }

    public void addData(T data) {
        datas.add(data);
        notifyItemInserted(datas.size() - 1);
    }

    public void addData(@Nullable List<T> dataList) {
        if (dataList == null) {
            return;
        }
        int start = datas.size();
        datas.addAll(dataList);
        notifyItemRangeInserted(start, dataList.size());
    }

    public void removeDate(T data) {
        int location = datas.indexOf(data);

        if (location != -1) {
            datas.remove(data);
            notifyItemRemoved(location);
        }
    }

    public void removeAllDate() {
        datas.clear();
        notifyDataSetChanged();
    }

    public int getDateSize() {
        return datas.size();
    }
}
