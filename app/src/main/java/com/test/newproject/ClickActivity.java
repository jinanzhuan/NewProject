package com.test.newproject;

import android.content.Context;
import android.content.Intent;

import com.test.newproject.base.BaseActivity;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/20
 *     desc   :
 *     modify :
 * </pre>
 */

public class ClickActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_click;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ClickActivity.class);
        context.startActivity(starter);
    }
}
