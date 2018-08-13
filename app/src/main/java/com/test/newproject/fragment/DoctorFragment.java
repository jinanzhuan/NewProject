package com.test.newproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.newproject.R;
import com.test.newproject.activity.CalendarIndexActivity;
import com.test.newproject.activity.DoubleWaveActivity;
import com.test.newproject.activity.ProgressActivity;
import com.test.newproject.activity.RulerActivity;
import com.test.newproject.base.BaseFragment;

import butterknife.ButterKnife;
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
    @InjectView(R.id.btn_wave)
    Button mBtnWave;
    @InjectView(R.id.btn_ruler)
    Button mBtnRuler;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnProgress.setOnClickListener(this);
        mBtnCalendar.setOnClickListener(this);
        mBtnWave.setOnClickListener(this);
        mBtnRuler.setOnClickListener(this);
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
            case R.id.btn_wave://双波纹效果
                DoubleWaveActivity.start(mContext);
                break;
            case R.id.btn_ruler:
                RulerActivity.actionStart(mContext);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
