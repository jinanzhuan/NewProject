package com.test.newproject.fragment;

import android.view.View;
import android.widget.Button;

import com.test.newproject.R;
import com.test.newproject.activity.CalendarIndexActivity;
import com.test.newproject.activity.ProgressActivity;
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

public class DoctorFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.btn_progress)
    Button mBtnProgress;
    @InjectView(R.id.btn_calendar)
    Button mBtnCalendar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnProgress.setOnClickListener(this);
        mBtnCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_progress:
                ProgressActivity.actionStart(mContext);
                break;
            case R.id.btn_calendar:
                CalendarIndexActivity.actionStart(mContext);
                break;
        }
    }

}
