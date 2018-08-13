package com.test.newproject.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.test.newproject.api.OnItemClickListener;

import java.util.List;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2017/11/13
 *     desc   :
 *     modify :
 * </pre>
 */

public abstract class BaseReclerAdapter<T> extends RecyclerView.Adapter<BaseReclerAdapter.ViewHolder>{
    public Context mContext;
    public List<T> mDatas;
    private OnItemClickListener mListener;

    public BaseReclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0:mDatas.size();
    }

    @Override
    public BaseReclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }

    public abstract ViewHolder getViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(BaseReclerAdapter.ViewHolder holder, final int position) {
        T t = mDatas.get(position);
        holder.setData(t, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });
    }

    /**
     * 添加数据
     * @param data
     */
    public void setData(List<T> data){
        this.mDatas = data;
        notifyDataSetChanged();
    }

    /**
     * 添加更多数据
     * @param data
     */
    public void addData(List<T> data){
        if(data != null && !data.isEmpty()) {
            if(this.mDatas == null) {
                this.mDatas = data;
            }else {
                this.mDatas.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * return the data which show in list.
     * @return
     */
    public List<T> getData(){
        return mDatas;
    }

    /**
     * 设置点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    /**
     * add this we can user it, not new again.
     */
    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setData(T t, int position);
    }
}
