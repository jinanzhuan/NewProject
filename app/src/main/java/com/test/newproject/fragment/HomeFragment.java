package com.test.newproject.fragment;

import android.view.View;
import android.widget.Button;

import com.test.newproject.ClickActivity;
import com.test.newproject.PermissionActivity;
import com.test.newproject.R;
import com.test.newproject.activity.AlarmClockActivity;
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

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.btn_click_result)
    Button mBtnClickResult;
    @InjectView(R.id.btn_permission)
    Button mBtnPermission;
    @InjectView(R.id.btn_alarm)
    Button mBtnAlarm;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnClickResult.setOnClickListener(this);
        mBtnPermission.setOnClickListener(this);
        mBtnAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click_result:
                ClickActivity.actionStart(mContext);
                break;
            case R.id.btn_permission:
                PermissionActivity.actionStart(mContext);
                break;
            case R.id.btn_alarm:
                AlarmClockActivity.actionStart(mContext);
                break;
        }
    }
}
