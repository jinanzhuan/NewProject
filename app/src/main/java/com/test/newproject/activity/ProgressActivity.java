package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.view.CompletedView;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/24
 *     desc   :
 *     modify :
 * </pre>
 */

public class ProgressActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.cv_progress)
    CompletedView mCvProgress;
    @InjectView(R.id.btn_start)
    Button mBtnStart;
    @InjectView(R.id.btn_pause)
    Button mBtnPause;
    private int mCurrentProgress;
    private boolean isOngoing;
    private Thread mThread;
    private int mMaxProgress;

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ProgressActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        mMaxProgress = 50;
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnStart.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start :
                isOngoing = true;
                mThread = new Thread(new ProgressRunable());
                mThread.start();
                break;
            case R.id.btn_pause :
                isOngoing = false;
                break;
        }
    }

    class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (mCurrentProgress < mMaxProgress && isOngoing) {
                mCurrentProgress += 1;
                mCvProgress.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(mCurrentProgress >= 100) {
                    mCurrentProgress = 0;
                }
            }
        }
    }
}
