package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/18
 *     desc   :
 *     modify :
 * </pre>
 */

public class ScannerActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.btn_zxing)
    Button mBtnZxing;
    @InjectView(R.id.btn_zbar)
    Button mBtnZbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scanner;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ScannerActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnZxing.setOnClickListener(this);
        mBtnZbar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zxing :
                ZXingActivity.actionStart(mContext);
                break;
            case R.id.btn_zbar :

                break;
        }
    }
}
