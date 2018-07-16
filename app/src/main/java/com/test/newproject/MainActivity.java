package com.test.newproject;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.newproject.base.BaseActivity;
import com.test.newproject.fragment.AboutMeFragment;
import com.test.newproject.fragment.DoctorFragment;
import com.test.newproject.fragment.HomeFragment;
import com.test.newproject.fragment.MessageFragment;
import com.test.newproject.view.NoScrollViewPager;

import butterknife.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.vp_fragment)
    NoScrollViewPager mVpFragment;
    @InjectView(R.id.ll_home)
    LinearLayout mLlHome;
    @InjectView(R.id.ll_doctor)
    LinearLayout mLlDoctor;
    @InjectView(R.id.ll_message)
    LinearLayout mLlMessage;
    @InjectView(R.id.ll_about_me)
    LinearLayout mLlAboutMe;
    @InjectView(R.id.ll_group)
    LinearLayout mLlGroup;
    @InjectView(R.id.tv_measure)
    TextView mTvMeasure;
    @InjectView(R.id.iv_home)
    ImageView mIvHome;
    @InjectView(R.id.tv_home)
    TextView mTvHome;
    @InjectView(R.id.iv_doctor)
    ImageView mIvDoctor;
    @InjectView(R.id.tv_doctor)
    TextView mTvDoctor;
    @InjectView(R.id.iv_message)
    ImageView mIvMessage;
    @InjectView(R.id.tv_message)
    TextView mTvMessage;
    @InjectView(R.id.iv_about_me)
    ImageView mIvAboutMe;
    @InjectView(R.id.tv_about_me)
    TextView mTvAboutMe;
    int[] id = {R.id.iv_home, R.id.iv_doctor, R.id.iv_message, R.id.iv_about_me};
    int[] drawbleID = {R.drawable.doctor_homemenuhomeblue,
            R.drawable.patient_home_menu_doctor_blue, R.drawable.doctor_homemenumessageblue,
            R.drawable.doctor_homemenumyblue};
    int[] drawbleIDuntouch = {R.drawable.doctor_homemenuhomegray,
            R.drawable.patient_home_menu_doctor_gray, R.drawable.doctor_homemenumessagegray,
            R.drawable.doctor_homemenumygray};
    private int mSelectId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        super.initListener();
        mLlHome.setOnClickListener(this);
        mLlDoctor.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
        mLlAboutMe.setOnClickListener(this);
        mTvMeasure.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        initViewPager();
        skip2page(0);
    }

    private void initViewPager() {
        mVpFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new DoctorFragment();
                    case 2:
                        return new MessageFragment();
                    case 3:
                        return new AboutMeFragment();
                    default:
                        return new HomeFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                skip2page(0);
                break;
            case R.id.ll_doctor:
                skip2page(1);
                break;
            case R.id.ll_message:
                skip2page(2);
                break;
            case R.id.ll_about_me:
                skip2page(3);
                break;
            case R.id.tv_measure:
                showMeasureDialog();
                break;
        }
    }

    private void showMeasureDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("提示")
                .setMessage("请先绑定硬件设备!")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();

    }

    public void skip2page(int i) {
        int mColor = getResources().getColor(R.color.gray_dark);
        mTvHome.setTextColor(mColor);
        mTvDoctor.setTextColor(mColor);
        mTvMessage.setTextColor(mColor);
        mTvAboutMe.setTextColor(mColor);
        switch (i) {
            case 0:
                updateState(mIvHome);
                mTvHome.setTextColor(Color.parseColor("#4395bb"));
                break;
            case 1:
                updateState(mIvDoctor);
                mTvDoctor.setTextColor(Color.parseColor("#4395bb"));
                break;
            case 2:
                updateState(mIvMessage);
                mTvMessage.setTextColor(Color.parseColor("#4395bb"));
                break;
            case 3:
                updateState(mIvAboutMe);
                mTvAboutMe.setTextColor(Color.parseColor("#4395bb"));
                break;
        }
        mSelectId = i;
        try {
            mVpFragment.setCurrentItem(i, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateState(ImageView check) {
        for (int i = 0; i < id.length; i++) {
            ImageView iv = (ImageView) mContext.findViewById(id[i]);
            if (check.getId() == id[i]) {
                iv.setImageDrawable(getResources().getDrawable(drawbleID[i]));
            } else {
                iv.setImageDrawable(getResources().getDrawable(drawbleIDuntouch[i]));
            }

        }
    }
}
