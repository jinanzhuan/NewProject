package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/26
 *     desc   :
 *     modify :
 * </pre>
 */

public class MultipleSizeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_multiple_size;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, MultipleSizeActivity.class);
        context.startActivity(starter);
    }
}
