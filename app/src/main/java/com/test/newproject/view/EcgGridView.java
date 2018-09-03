package com.test.newproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


/**
 * <pre>
 *     author : created by ljn
 *     mPaint-mail : liujinan@edreamtree.com
 *     time   : 2018/8/30
 *     desc   :
 *     modify :
 * </pre>
 */

public class EcgGridView extends View {
    private Path mVerticalPath;
    private Path mHorizontalPath;
    private int mWidth;
    private int mHeight;
    @NonNull
    private final Paint mPaint;
    private float f;
    private float g;

    public EcgGridView(Context context) {
        this(context, null);
    }

    public EcgGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EcgGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPaint = new Paint();
        this.setBackgroundColor(-1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(1f);
        this.mPaint.setColor(Color.rgb(224, 224, 224));
        canvas.drawPath(this.mVerticalPath, this.mPaint);
        this.mPaint.setColor(Color.rgb(224, 224, 224));
        this.mPaint.setStrokeWidth(2.5f);
        canvas.drawPath(this.mHorizontalPath, this.mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Object v0 = this.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics v1 = new DisplayMetrics();
        ((WindowManager)v0).getDefaultDisplay().getMetrics(v1);
        this.f = v1.xdpi / 2.54f * 1f;
        this.g = v1.ydpi / 2.54f * 1f;
        this.g = this.f;
        this.mWidth = w;
        this.mHeight = h;
        this.a();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void a() {
        int v1 = 0;
        float v9 = 2f;
        float v8 = 10f;
        if(this.mVerticalPath == null) {
            this.mVerticalPath = new Path();
            this.mHorizontalPath = new Path();
        }

        this.mVerticalPath.rewind();
        this.mHorizontalPath.rewind();
        int v4 = (int)((((float)this.mWidth)) / (this.f / v8)) + 1;
        int v0 = 0;
        float v2 = 0f;
        while(v0 < v4) {
            if(v0 % 5 != 0) {
                this.mVerticalPath.moveTo(v2, 0f);
                this.mVerticalPath.lineTo(v2, ((float)this.mHeight));
            }

            v2 += this.f / v8;
            ++v0;
        }

        v4 = (int)((((float)this.mHeight)) / (this.g / v8)) + 1;
        v0 = 0;
        v2 = 0f;
        while(v0 < v4 / 2) {
            if(v0 % 5 != 0) {
                this.mVerticalPath.moveTo(0f, (((float)(this.mHeight / 2))) + v2);
                this.mVerticalPath.lineTo(((float)this.mWidth), (((float)(this.mHeight / 2))) + v2);
                this.mVerticalPath.moveTo(0f, (((float)(this.mHeight / 2))) - v2);
                this.mVerticalPath.lineTo(((float)this.mWidth), (((float)(this.mHeight / 2))) - v2);
            }

            v2 += this.g / v8;
            ++v0;
        }

        int v2_1 = (((int)((((float)(this.mWidth * 2))) / this.f))) + 1;
        float v0_1 = 0f;
        while(v1 < v2_1) {
            this.mHorizontalPath.moveTo(v0_1, 0f);
            this.mHorizontalPath.lineTo(v0_1, ((float)this.mHeight));
            v0_1 += this.f / v9;
            ++v1;
        }

        this.mHorizontalPath.moveTo(0f, ((float)(this.mHeight / 2)));
        this.mHorizontalPath.lineTo(((float)this.mWidth), ((float)(this.mHeight / 2)));
        v2_1 = (((int)((((float)(this.mHeight * 2))) / this.g))) + 2;
        float v1_1 = this.g / v9;
        for(v0 = 1; v0 < v2_1 / 2; ++v0) {
            this.mHorizontalPath.moveTo(0f, (((float)(this.mHeight / 2))) + v1_1);
            this.mHorizontalPath.lineTo(((float)this.mWidth), (((float)(this.mHeight / 2))) + v1_1);
            this.mHorizontalPath.moveTo(0f, (((float)(this.mHeight / 2))) - v1_1);
            this.mHorizontalPath.lineTo(((float)this.mWidth), (((float)(this.mHeight / 2))) - v1_1);
            v1_1 += this.g / v9;
        }
    }
}
