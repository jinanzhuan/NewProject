package com.test.newproject.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/27
 *     desc   :
 *     modify :
 * </pre>
 */

public class ObjectAnimationActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.iv_scenery)
    ImageView mIvScenery;
    @InjectView(R.id.btn_start)
    Button mBtnStart;
    @InjectView(R.id.btn_stop)
    Button mBtnStop;
    private ObjectAnimator mAnimator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_object_animation;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ObjectAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        float translationY = mIvScenery.getTranslationY();
        Log.e("TAG", "translationY=" + translationY);
        mAnimator = ObjectAnimator.ofFloat(mIvScenery, "translationY", translationY, 50, translationY);
        mAnimator.setDuration(3000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setStartDelay(500);
        mAnimator.start();
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start :
                if(mAnimator.isPaused()) {
                    mAnimator.start();
                }
                break;
            case R.id.btn_stop :
                if(mAnimator.isStarted()) {
                    mAnimator.pause();
                }
                break;
        }
    }
}
