package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/3
 *     desc   :
 *     modify :
 * </pre>
 */

public class PathActivity extends BaseActivity {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_path;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, PathActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
