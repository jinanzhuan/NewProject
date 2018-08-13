package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.view.RulerView;

import java.util.Calendar;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/13
 *     desc   :
 *     modify :
 * </pre>
 */

public class RulerActivity extends BaseActivity {
    @InjectView(R.id.rv_year)
    RulerView mRvYear;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ruler;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RulerActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData() {
        super.initData();
        int i = Calendar.getInstance().get(Calendar.YEAR);
        mRvYear.setValue(1990, 1900, i, 1);
    }
}
