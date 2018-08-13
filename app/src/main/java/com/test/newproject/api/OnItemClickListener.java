package com.test.newproject.api;

import android.view.View;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2017/11/07
 *     desc   : 作为recyclerView的公用的条目点击事件
 *     modify :
 * </pre>
 */

public interface OnItemClickListener {
    /**
     * 条目点击时间
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);
}
