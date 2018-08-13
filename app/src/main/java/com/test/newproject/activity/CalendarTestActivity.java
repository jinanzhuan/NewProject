package com.test.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.newproject.R;
import com.test.newproject.adapter.CalendarTestAdapter;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.bean.TestBean;

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

public class CalendarTestActivity extends BaseActivity {

    @InjectView(R.id.rv_list)
    RecyclerView mRvList;
    private CalendarTestAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_calendar_test;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CalendarTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CalendarTestAdapter(mContext);
        mRvList.setAdapter(mAdapter);
        mRvList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter.setData(TestBean.getList());
    }
}
