package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.transition.TransitionManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.decorators.RangeDayDecorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

public class SelectionModesActivity extends BaseActivity implements OnDateSelectedListener, OnRangeSelectedListener, CompoundButton.OnCheckedChangeListener {
    @InjectView(R.id.calendar_view_single)
    MaterialCalendarView single;
    @InjectView(R.id.calendar_view_multi)
    MaterialCalendarView multi;
    @InjectView(R.id.calendar_view_range)
    MaterialCalendarView range;
    @InjectView(R.id.calendar_view_none)
    MaterialCalendarView none;
    @InjectView(R.id.calendar_mode)
    CheckBox mCalendarMode;
    @InjectView(R.id.parent)
    LinearLayout parent;

    private RangeDayDecorator decorator;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Override
    public int getLayoutId() {
        return R.layout.activity_selection_modes;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, SelectionModesActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        decorator = new RangeDayDecorator(this);
    }

    @Override
    public void initListener() {
        super.initListener();
        single.setOnDateChangedListener(this);
        multi.setOnDateChangedListener(this);
        range.setOnDateChangedListener(this);
        range.setOnRangeSelectedListener(this);
        range.addDecorator(decorator);
        none.setOnDateChangedListener(this);
        mCalendarMode.setOnCheckedChangeListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        final String text = selected ? FORMATTER.format(date.getDate()) : "No Selection";
        Toast.makeText(SelectionModesActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
        if (dates.size() > 0) {
            decorator.addFirstAndLast(dates.get(0), dates.get(dates.size() - 1));
            range.invalidateDecorators();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(parent);
        }
        final CalendarMode mode = isChecked ? CalendarMode.WEEKS : CalendarMode.MONTHS;
        single.state().edit().setCalendarDisplayMode(mode).commit();
        multi.state().edit().setCalendarDisplayMode(mode).commit();
        range.state().edit().setCalendarDisplayMode(mode).commit();
        none.state().edit().setCalendarDisplayMode(mode).commit();
    }
}
