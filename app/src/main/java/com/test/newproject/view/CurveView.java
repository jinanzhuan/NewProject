package com.test.newproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/22
 *     desc   :
 *     modify :
 * </pre>
 */

public class CurveView extends View {

    private Paint mPaint;
    private float mRadius = 3500f;
    private float mHeight;
    private int mColor;

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#78BFF5"));
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建一个新的画布Layer
        int layerId = canvas.saveLayer(0, 0, getWidth(),getHeight() , null, Canvas.ALL_SAVE_FLAG);
        int width = getWidth() / 2;
        float height = (float) Math.sqrt(mRadius*mRadius - width*width);
        canvas.drawCircle(width, -height, mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawRect(0, 0, getWidth(), mRadius-height+20, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
