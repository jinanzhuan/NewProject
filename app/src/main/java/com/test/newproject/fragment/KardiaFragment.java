package com.test.newproject.fragment;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.test.newproject.R;
import com.test.newproject.base.BaseFragment;
import com.test.newproject.ecgmonitor.f;
import com.test.newproject.ecgmonitor.j;
import com.test.newproject.ecgmonitor.q;
import com.test.newproject.utils.PermissionUtils;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

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

public class KardiaFragment extends BaseFragment implements View.OnClickListener, q {
    @InjectView(R.id.instructions_step1_img)
    ImageView mInstructionsStep1Img;
    @InjectView(R.id.instructions_step1_txt)
    TextView mInstructionsStep1Txt;
    @InjectView(R.id.new_badge)
    ImageView mNewBadge;
    @InjectView(R.id.instructions_step2_img)
    ImageView mInstructionsStep2Img;
    @InjectView(R.id.instructions_step2_txt)
    TextView mInstructionsStep2Txt;
    @InjectView(R.id.voice_settings_link)
    TextView mVoiceSettingsLink;
    @InjectView(R.id.btn_grant_permission)
    Button mBtnGrantPermission;
    @InjectView(R.id.instructions)
    LinearLayout mInstructions;
    @InjectView(R.id.pbar_rec)
    ProgressBar mPbarRec;
    @InjectView(R.id.lbl_elapsedtime)
    TextView mLblElapsedtime;
    @InjectView(R.id.rec_view)
    LinearLayout mRecView;
    @InjectView(R.id.img_signal)
    ImageView mImgSignal;
    @InjectView(R.id.lbl_heartrate)
    TextView mLblHeartrate;
    @InjectView(R.id.lbl_bpm)
    TextView mLblBpm;
    @InjectView(R.id.img_heartbeat)
    ImageView mImgHeartbeat;
    @InjectView(R.id.layout_status)
    FrameLayout mLayoutStatus;
    @InjectView(R.id.btn_musclefilter)
    Switch mBtnMusclefilter;
    @InjectView(R.id.btn_mainsfilter)
    Switch mBtnMainsfilter;
    @InjectView(R.id.btn_baselinefilter)
    Switch mBtnBaselinefilter;
    @InjectView(R.id.btn_enhancedfilter)
    Switch mBtnEnhancedfilter;
    @InjectView(R.id.layout_onscreencontrols)
    LinearLayout mLayoutOnscreencontrols;
    @InjectView(R.id.img_e)
    ImageView mImgE;
    @InjectView(R.id.lbl_ecg_limit_reached)
    TextView mLblEcgLimitReached;
    @InjectView(R.id.img_ribbon)
    ImageView mImgRibbon;
    @InjectView(R.id.mic_icon_off)
    ImageView mMicIconOff;
    @InjectView(R.id.mic_icon_on)
    ImageView mMicIconOn;
    @InjectView(R.id.mic_message)
    LinearLayout mMicMessage;
    @InjectView(R.id.imageHands)
    ImageView mImageHands;
    @InjectView(R.id.record_ecg_btn)
    Button mRecordEcgBtn;

    private boolean mIsClose;
    private j x;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_kardia;
    }

    @Override
    public void initView() {
        super.initView();
        
    }

    @Override
    public void initListener() {
        super.initListener();
        mVoiceSettingsLink.setOnClickListener(this);
        mBtnGrantPermission.setOnClickListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getView() != null && mImageHands != null) {
            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mImageHands.setVisibility(View.GONE);
            }else {
                mImageHands.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mImageHands.setVisibility(View.GONE);
        }else {
            mImageHands.setVisibility(View.VISIBLE);
        }
        if(PermissionUtils.hasPermission(mContext, Manifest.permission.RECORD_AUDIO)) {
            mBtnGrantPermission.setVisibility(View.GONE);
        }else {
            mBtnGrantPermission.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voice_settings_link :
                changeRecordingState(!mIsClose);
                break;
            case R.id.btn_grant_permission:
                requestPermission();
                break;
        }
    }

    private void requestPermission() {
        PermissionUtils.hasPermission(this, 100, Manifest.permission.RECORD_AUDIO);
    }

    private void changeRecordingState(boolean isClose) {
        mIsClose = isClose;
        SharedPreferences preferences = mContext.getSharedPreferences("sp", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("voice_recording", isClose).apply();
        switchRecordState();
    }

    private void switchRecordState() {
        if(!mIsClose) {
            mVoiceSettingsLink.setText(getResources().getString(R.string.ecg_real_time_turn_off_voice));
            mInstructionsStep1Img.setVisibility(View.VISIBLE);
            mNewBadge.setVisibility(View.VISIBLE);
            mInstructionsStep2Img.setVisibility(View.VISIBLE);
            mInstructionsStep2Txt.setVisibility(View.VISIBLE);
        }else {
            mVoiceSettingsLink.setText(getResources().getString(R.string.ecg_real_time_turn_on_voice));
            mInstructionsStep1Img.setVisibility(View.GONE);
            mNewBadge.setVisibility(View.GONE);
            mInstructionsStep2Img.setVisibility(View.GONE);
            mInstructionsStep2Txt.setVisibility(View.GONE);
        }
    }

    @Override
    public void a(boolean arg1, boolean arg2) {

    }

    @Override
    public void a(int arg1) {

    }

    @Override
    public void a(short[] arg1, int arg2, int arg3) {

    }

    @Override
    public long a(f arg1) {
        return 0;
    }

    @Override
    public void a(int arg1, boolean arg2) {

    }

    @Override
    public void a(long arg1) {

    }

    @Override
    public void b(int arg7) {
        int v0 = arg7 / 3600;
        int v1 = (arg7 - v0 * 3600) / 60;
        mLblElapsedtime.setText(this.getString(R.string.fmt_hr_min_2_digit, new Object[]{Integer.valueOf(v1), Integer.valueOf(arg7 - v1 * 60 - v0 * 3600)}));
        if(this.x.e() > 0) {
            mPbarRec.setProgress(arg7 * 100 / this.x.e());
        }

        if(arg7 >= this.x.f()) {
            mLblElapsedtime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.greendot, 0, 0, 0);
        }
    }

    @Override
    public void b(long arg1) {

    }

    @Override
    public void c(int arg1) {

    }

    @Override
    public void d() {

    }

    @Override
    public void e(boolean arg1) {

    }

    @Override
    public void e() {

    }

    @Override
    public void f(boolean arg1) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, List<String> grantPermissions) {
                mBtnGrantPermission.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(int requestCode, List<String> deniedPermissions) {
                PermissionUtils.FailedForPermissions(KardiaFragment.this, requestCode, deniedPermissions, "测心电需要打开麦克风");
            }
        });
    }
}
