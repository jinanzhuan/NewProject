package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.fragment.KardiaFragment;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/30
 *     desc   :
 *     modify :
 * </pre>
 */

public class KardiaActivity extends BaseActivity {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.fl_fragment)
    FrameLayout mFlFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_kardia;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, KardiaActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, new KardiaFragment()).commit();
    }
}
