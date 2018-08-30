package com.test.newproject.fragment;

import android.view.View;
import android.widget.Button;

import com.test.newproject.R;
import com.test.newproject.activity.KardiaActivity;
import com.test.newproject.activity.ObjectAnimationActivity;
import com.test.newproject.activity.TestActivity;
import com.test.newproject.base.BaseFragment;

import butterknife.InjectView;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/16
 *     desc   :
 *     modify :
 * </pre>
 */

public class MessageFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.btn_test)
    Button mBtnTest;
    @InjectView(R.id.btn_animation)
    Button mBtnAnimation;
    @InjectView(R.id.btn_kardia)
    Button mBtnKardia;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnTest.setOnClickListener(this);
        mBtnAnimation.setOnClickListener(this);
        mBtnKardia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                TestActivity.actionStart(mContext);
                break;
            case R.id.btn_animation:
                ObjectAnimationActivity.actionStart(mContext);
                break;
            case R.id.btn_kardia:
                KardiaActivity.actionStart(mContext);
                break;
        }
    }
}
