package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

/**
 * Created by shuwei on 2018/8/11.
 */

public class DoubleWaveActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_double_wave;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, DoubleWaveActivity.class);
        context.startActivity(starter);
    }
}
