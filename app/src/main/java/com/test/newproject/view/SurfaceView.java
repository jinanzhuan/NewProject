package com.test.newproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.test.newproject.R;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/22
 *     desc   :
 *     modify :
 * </pre>
 */

public class SurfaceView extends View {

    private Context mContext;
    private Paint mPaint;
    private float mArcHeight = 20f;
    private float mHeight;
    private float mWidth;
    private int mColor = Color.WHITE;

    public SurfaceView(Context context) {
        this(context, null);
    }

    public SurfaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SurfaceView);
        init(typedArray);
    }

    private void init(TypedArray typedArray) {
        mArcHeight = typedArray.getDimension(R.styleable.SurfaceView_arcHeight, dp2px(10));
        mColor = typedArray.getColor(R.styleable.SurfaceView_rectColor, Color.WHITE);
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建一个新的画布Layer
        Path path = new Path();
        int width = (int) (mWidth / 2);
        path.moveTo(0,0);
        path.quadTo(width, mArcHeight, mWidth, 0);
        path.lineTo(getWidth(), mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
