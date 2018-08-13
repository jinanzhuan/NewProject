package com.test.newproject.base;

import android.view.View;

/**
 * Title:BaseViewHolder
 * Description：
 * Created by：ljn
 */
public abstract class BaseViewHolder<T> {
    private View rootView;
    private T t;
    public BaseViewHolder(){
        rootView = initView();
        rootView.setTag(this);
    }

    public T getT() {
        return t;
    }

    public View getRootView() {
        return rootView;
    }

    public abstract View initView();

    public abstract void setData(T t, int position);
}
