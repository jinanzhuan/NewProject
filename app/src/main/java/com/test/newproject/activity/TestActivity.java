package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/21
 *     desc   :
 *     modify :
 * </pre>
 */

public class TestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }
}
