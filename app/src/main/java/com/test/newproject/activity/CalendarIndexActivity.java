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
 *     time   : 2018/7/26
 *     desc   :
 *     modify :
 * </pre>
 */

public class CalendarIndexActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.btn_calendar)
    Button mBtnCalendar;
    @InjectView(R.id.btn_selection_modes)
    Button mBtnSelectionModes;
    @InjectView(R.id.btn_multiple_size)
    Button mBtnMultipleSize;
    @InjectView(R.id.btn_dynamic_setters)
    Button mBtnDynamicSetters;
    @InjectView(R.id.btn_custom_tile)
    Button mBtnCustomTile;
    @InjectView(R.id.btn_test)
    Button mBtnTest;
    @InjectView(R.id.btn_basic_decorated)
    Button mBtnBasicDecorated;

    @Override
    public int getLayoutId() {
        return R.layout.activity_calendar_index;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CalendarIndexActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnDynamicSetters.setOnClickListener(this);
        mBtnCustomTile.setOnClickListener(this);
        mBtnCalendar.setOnClickListener(this);
        mBtnSelectionModes.setOnClickListener(this);
        mBtnMultipleSize.setOnClickListener(this);
        mBtnBasicDecorated.setOnClickListener(this);
        mBtnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynamic_setters:
                DynamicSettersActivity.actionStart(mContext);
                break;
            case R.id.btn_custom_tile:
                CustomTileDimensions.actionStart(mContext);
                break;
            case R.id.btn_calendar:
                CalendarActivity.actionStart(mContext);
                break;
            case R.id.btn_selection_modes:
                SelectionModesActivity.actionStart(mContext);
                break;
            case R.id.btn_multiple_size:
                MultipleSizeActivity.actionStart(mContext);
                break;
            case R.id.btn_test:
                CalendarTestActivity.actionStart(mContext);
                break;
            case R.id.btn_basic_decorated:
                BasicActivityDecorated.actionStart(mContext);
                break;
        }
    }
}
