package com.test.newproject.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

import java.util.Calendar;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/23
 *     desc   :
 *     modify :
 * </pre>
 */

public class AlarmClockActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.btn_date)
    Button mBtnDate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_clock;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AlarmClockActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date :
                showDateDialog();
                break;
        }
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.Theme_AppCompat_DayNight, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int chooseY, int chooseM, int chooseD) {
                //设置储存数据的年月日
                //mYMDText.setText(chooseY + "年"+chooseM + "月" +chooseD);
                Toast.makeText(AlarmClockActivity.this, chooseY + "年"+chooseM + "月" +chooseD, Toast.LENGTH_SHORT).show();
            }
        },year,month,day);

        datePickerDialog.show();
    }
}
