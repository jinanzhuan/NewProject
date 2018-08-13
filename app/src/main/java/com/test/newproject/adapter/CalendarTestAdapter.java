package com.test.newproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.newproject.R;
import com.test.newproject.base.BaseReclerAdapter;
import com.test.newproject.bean.TestBean;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/30
 *     desc   :
 *     modify :
 * </pre>
 */

public class CalendarTestAdapter extends BaseReclerAdapter<TestBean> {

    public CalendarTestAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_test, null);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends ViewHolder {
        @InjectView(R.id.tv_name)
        TextView mTvName;
        @InjectView(R.id.tv_content)
        TextView mTvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(TestBean bean, int position) {
            mTvName.setText(bean.getName());
            mTvContent.setText(bean.getContent());
        }
    }
}
