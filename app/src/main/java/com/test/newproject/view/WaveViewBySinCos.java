package com.test.newproject.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.test.newproject.R;

/**
 * Created by allen on 2017/6/20.
 * /**
 * y=A*sin(ωx+φ)+k
 * <p>
 * A—振幅越大，波形在y轴上最大与最小值的差值越大
 * ω—角速度， 控制正弦周期(单位角度内震动的次数)
 * φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
 * k—偏距，反映在坐标系上则为图像的上移或下移。
 */

public class WaveViewBySinCos extends View {

    private Context mContext;

    /**
     * 振幅
     */
    private int A;

    /**
     * 第二个波形的振幅
     */
    private int A2;

    /**
     * 偏距
     */
    private int K;

    /**
     * 波形的颜色
     */
    private int waveColor = 0xaaFF7E37;

    /**
     * 第二个波形的颜色
     */
    private int wave2Color = 0xaaFF7E37;

    /**
     * 初相
     */
    private float φ;

    /**
     * 第二个波纹的初相
     */
    private float φ2;

    /**
     * 波形移动的速度
     */
    private float waveSpeed = 3f;

    /**
     * 第二个波形移动的速度
     */
    private float wave2Speed = 3f;

    /**
     * 角速度
     */
    private double ω;

    /**
     * 开始位置相差多少个周期
     */
    private double startPeriod;

    /**
     * 第二个波形开始位置相差多少个周期
     */
    private double startPeriod2;

    /**
     * 是否直接开启波形
     */
    private boolean waveStart;

    /**
     * 是否展示双波浪
     */
    private boolean showDoubleWave;

    private Path path;
    private Path path2;
    private Paint paint;
    private Paint paint2;

    private static final int SIN = 0;
    private static final int COS = 1;

    private int waveType;

    private static final int TOP = 0;
    private static final int BOTTOM = 1;

    private int waveFillType;

    private ValueAnimator valueAnimator;

    public WaveViewBySinCos(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAttr(attrs);
        K = A;
        initPaint();

        initAnimation();
    }

    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RadarWaveView);

        waveType = typedArray.getInt(R.styleable.RadarWaveView_waveType, SIN);
        waveFillType = typedArray.getInt(R.styleable.RadarWaveView_waveFillType, BOTTOM);
        A = typedArray.getDimensionPixelOffset(R.styleable.RadarWaveView_waveAmplitude, dp2px(10));
        A2 = typedArray.getDimensionPixelOffset(R.styleable.RadarWaveView_wave2Amplitude, dp2px(10));
        waveColor = typedArray.getColor(R.styleable.RadarWaveView_waveColor, waveColor);
        wave2Color = typedArray.getColor(R.styleable.RadarWaveView_wave2Color, waveColor);
        waveSpeed = typedArray.getFloat(R.styleable.RadarWaveView_waveSpeed, waveSpeed);
        wave2Speed = typedArray.getFloat(R.styleable.RadarWaveView_wave2Speed, waveSpeed);
        startPeriod = typedArray.getFloat(R.styleable.RadarWaveView_waveStartPeriod, 0);
        startPeriod2 = typedArray.getFloat(R.styleable.RadarWaveView_wave2StartPeriod, 0);
        waveStart = typedArray.getBoolean(R.styleable.RadarWaveView_waveStart, false);
        showDoubleWave = typedArray.getBoolean(R.styleable.RadarWaveView_showDoubleWave, false);

        typedArray.recycle();
    }

    private void initPaint() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(waveColor);
        //第二个波形的初始化
        if(showDoubleWave) {
            path2 = new Path();
            paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint2.setAntiAlias(true);
            paint2.setStyle(Paint.Style.FILL_AND_STROKE);
            paint2.setColor(wave2Color);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ω = 2 * Math.PI / getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (waveType) {
            case SIN:
                drawSin(canvas);
                break;
            case COS:
                drawCos(canvas);
                break;
        }

    }


    /**
     * 根据cos函数绘制波形
     *
     * @param canvas
     */
    private void drawCos(Canvas canvas) {

        switch (waveFillType) {
            case TOP:
                fillTop(canvas);
                break;
            case BOTTOM:
                fillBottom(canvas);
                break;
        }
    }

    /**
     * 根据sin函数绘制波形
     *
     * @param canvas
     */
    private void drawSin(Canvas canvas) {

        switch (waveFillType) {
            case TOP:
                fillTop(canvas);
                break;
            case BOTTOM:
                fillBottom(canvas);
                break;
        }

    }

    /**
     * 填充波浪上面部分
     */
    private void fillTop(Canvas canvas) {

        φ -= waveSpeed / 100;
        float y;

        path.reset();
        path.moveTo(0, getHeight());

        for (float x = 0; x <= getWidth(); x += 20) {
            y = (float) (A * Math.sin(ω * x + φ + Math.PI * startPeriod) + K);
            path.lineTo(x, getHeight() - y);
        }

        path.lineTo(getWidth(), 0);
        path.lineTo(0, 0);
        path.close();

        canvas.drawPath(path, paint);

        if(showDoubleWave) {
            φ2 -= wave2Speed / 100;
            path2.reset();
            path2.moveTo(0, getHeight());

            for (float x = 0; x <= getWidth(); x += 20) {
                y = (float) (A2 * Math.sin(ω * x + φ2 + Math.PI * startPeriod2) + K);
                path2.lineTo(x, getHeight() - y);
            }

            path2.lineTo(getWidth(), 0);
            path2.lineTo(0, 0);
            path2.close();

            canvas.drawPath(path2, paint2);
        }

    }

    /**
     * 填充波浪下面部分
     */
    private void fillBottom(Canvas canvas) {

        φ -= waveSpeed / 100;
        float y;

        path.reset();
        path.moveTo(0, 0);

        for (float x = 0; x <= getWidth(); x += 20) {
            y = (float) (A * Math.sin(ω * x + φ + Math.PI * startPeriod) + K);
            path.lineTo(x, y);
        }

        //填充矩形
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();

        canvas.drawPath(path, paint);
        
        if(showDoubleWave) {
            φ2 -= wave2Speed / 100;
            path2.reset();
            path2.moveTo(0, 0);

            for (float x = 0; x <= getWidth(); x += 20) {
                y = (float) (A2 * Math.sin(ω * x + φ2 + Math.PI * startPeriod2) + K);
                path2.lineTo(x, y);
            }

            //填充矩形
            path2.lineTo(getWidth(), getHeight());
            path2.lineTo(0, getHeight());
            path2.close();

            canvas.drawPath(path2, paint2);
        }

    }

    private void initAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, getWidth());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**
                 * 刷新页面调取onDraw方法，通过变更φ 达到移动效果
                 */
                invalidate();
            }
        });
        if (waveStart) {
            valueAnimator.start();
        }
    }

    public void startAnimation() {
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    public void stopAnimation() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public void pauseAnimation() {
        if (valueAnimator != null) {
            valueAnimator.pause();
        }
    }

    public void resumeAnimation() {
        if (valueAnimator != null) {
            valueAnimator.resume();
        }
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