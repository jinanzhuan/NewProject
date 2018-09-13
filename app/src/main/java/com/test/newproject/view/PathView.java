package com.test.newproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/3
 *     desc   :
 *     modify :
 * </pre>
 */

public class PathView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
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
        canvas.translate(mWidth/2, mHeight/2);
        Path path = new Path();

        //========lineTo==========

        //path.lineTo(200, 200);
        //path.lineTo(200, 0);

        //=========moveTo==========

        //path.lineTo(200, 200);
        //path.moveTo(200, 100);
        //path.lineTo(200, 0);


        //=========setLastPoint==========

        //path.lineTo(200, 200);
        //path.setLastPoint(200, 100);
        //path.lineTo(200, 0);
        //canvas.drawPoint(200, 200, mPaint);

        //===========close============

        //path.lineTo(200, 200);
        //path.lineTo(200, 0);
        //path.close();

        //===========addRect===========

        path.addRect(-200,-200,200,200, Path.Direction.CCW);

        path.setLastPoint(-300,300);


        canvas.drawPath(path, mPaint);
    }
}
