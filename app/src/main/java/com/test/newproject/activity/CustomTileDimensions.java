package com.test.newproject.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/26
 *     desc   :
 *     modify :
 * </pre>
 */

public class CustomTileDimensions extends BaseActivity {
    @InjectView(R.id.calendarView)
    MaterialCalendarView widget;

    private int currentTileWidth;
    private int currentTileHeight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_tile;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CustomTileDimensions.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        currentTileWidth = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
        currentTileHeight = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;

        widget.addDecorator(new TodayDecorator());
    }

    @OnClick(R.id.custom_tile_match_parent)
    public void onMatchParentClick() {
        widget.setTileSize(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @OnClick(R.id.custom_tile_width_match_parent)
    public void onWidthMatchParentClick() {
        widget.setTileWidth(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @OnClick(R.id.custom_tile_height_match_parent)
    public void onHeightMatchParentClick() {
        widget.setTileHeight(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @OnClick(R.id.custom_tile_width_size)
    public void onWidthClick() {
        final NumberPicker view = new NumberPicker(this);
        view.setMinValue(24);
        view.setMaxValue(64);
        view.setWrapSelectorWheel(false);
        view.setValue(currentTileWidth);
        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        currentTileWidth = view.getValue();
                        widget.setTileWidthDp(currentTileWidth);
                    }
                })
                .show();
    }

    @OnClick(R.id.custom_tile_height_size)
    public void onHeightClick() {
        final NumberPicker view = new NumberPicker(this);
        view.setMinValue(24);
        view.setMaxValue(64);
        view.setWrapSelectorWheel(false);
        view.setValue(currentTileHeight);
        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        currentTileHeight = view.getValue();
                        widget.setTileHeightDp(currentTileHeight);
                    }
                })
                .show();
    }

    private class TodayDecorator implements DayViewDecorator {

        private final CalendarDay today;
        private final Drawable backgroundDrawable;

        public TodayDecorator() {
            today = CalendarDay.today();
            backgroundDrawable = getResources().getDrawable(R.drawable.today_circle_background);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return today.equals(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(backgroundDrawable);
        }
    }
}
