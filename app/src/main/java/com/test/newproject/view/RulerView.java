package com.test.newproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.test.newproject.R;


public class RulerView extends View {

    private int mMinVelocity;
    private Scroller mScroller;  //Scroller是一个专门用于处理滚动效果的工具类   用mScroller记录/计算View滚动的位置，再重写View的computeScroll()，完成实际的滚动
    private VelocityTracker mVelocityTracker; //主要用跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率。
    private int mWidth;
    private int mHeight;

    private float mSelectorValue = 50.0f; // 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
    private float mMaxValue = 200;        // 最大数值
    private float mMinValue = 100.0f;     //最小的数值
    private float mPerValue = 1;          //最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1
    // 在demo中 身高mPerValue为1  体重mPerValue 为0.1

    private float mLineSpaceWidth = 5;    //  尺子刻度2条线之间的距离
    private float mLineWidth = 4;         //  尺子刻度的宽度
    private float mLineMaxHeight = 420;   //  尺子刻度分为3中不同的高度。 mLineMaxHeight表示最长的那根(也就是 10的倍数时的高度)
    private float mLineMidHeight = 30;    //  mLineMidHeight  表示中间的高度(也就是 5  15 25 等时的高度)
    private float mLineMinHeight = 17;    //  mLineMinHeight  表示最短的那个高度(也就是 1 2 3 4 等时的高度)
    private float mMiddlePointerWidth;    // 中间标尺的宽度

    private float mTextMarginTop = 10;    //o
    private float mTextSize =30;         //尺子刻度下方数字 textsize

    private boolean mAlphaEnable = false;  // 尺子 最左边 最后边是否需要透明 (透明效果更好点)

    private float mTextHeight;            //尺子刻度下方数字  的高度

    private Paint mTextPaint;             // 尺子刻度下方数字( 也就是每隔10个出现的数值) paint
    private Paint mLinePaint;             //  尺子刻度  paint

    private int mTotalLine;               //共有多少条 刻度
    private int mMaxOffset;               //所有刻度 共有多长
    private float mOffset;                // 默认状态下，mSelectorValue所在的位置  位于尺子总刻度的位置
    private int mLastX, mMove;
    private OnValueChangeListener mListener;  // 滑动后数值回调
    private int mMiddlePointer;           //中间标尺
    private int mPerLineCount;             //主刻度间刻度数量

    private int mPointerType;              //中间标尺类型
    private float mPointerTop;             //箭头距离上部的距离

    private int mLineColor = Color.GRAY;   //刻度的颜色
    private int mTextColor=Color.BLACK;    //文字的颜色
    private int mPointerColor = Color.RED;    //中间标尺的颜色
    private int mBackgroundColor = Color.WHITE;    //背景渐变色颜色（中间颜色）
    private int mStartBackgroundColor = Color.WHITE; //背景色渐变色开始颜色
    private int mEndBackgroundColor = Color.WHITE; //背景色渐变色开始颜色


    public RulerView(Context context) {
        this(context, null);

    }

    public RulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        Log.d("zkk---","init");
        mScroller = new Scroller(context);


        this.mLineSpaceWidth = myfloat(25.0F);
        this.mLineWidth = myfloat(2.0F);
        this.mLineMaxHeight = myfloat(100.0F);
        this.mLineMidHeight = myfloat(60.0F);
        this.mLineMinHeight = myfloat(40.0F);
        this.mTextHeight = myfloat(40.0F);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RulerView);

        mAlphaEnable = typedArray.getBoolean(R.styleable.RulerView_alphaEnable, mAlphaEnable);

        mLineSpaceWidth = typedArray.getDimension(R.styleable.RulerView_lineSpaceWidth, mLineSpaceWidth);
        mLineWidth = typedArray.getDimension(R.styleable.RulerView_lineWidth, mLineWidth);
        mLineMaxHeight = typedArray.getDimension(R.styleable.RulerView_lineMaxHeight, mLineMaxHeight);
        mLineMidHeight = typedArray.getDimension(R.styleable.RulerView_lineMidHeight, mLineMidHeight);
        mLineMinHeight = typedArray.getDimension(R.styleable.RulerView_lineMinHeight, mLineMinHeight);
        mMiddlePointerWidth = typedArray.getDimension(R.styleable.RulerView_middlePointerWidth, 0);
        mLineColor = typedArray.getColor(R.styleable.RulerView_lineColor, mLineColor);

        mTextSize = typedArray.getDimension(R.styleable.RulerView_textSize,  mTextSize);
        mTextColor = typedArray.getColor(R.styleable.RulerView_textColor, mTextColor);
        mTextMarginTop = typedArray.getDimension(R.styleable.RulerView_textMarginTop, mTextMarginTop);

        mSelectorValue = typedArray.getFloat(R.styleable.RulerView_selectorValue, 0.0f);
        mMinValue = typedArray.getFloat(R.styleable.RulerView_minValue, 0.0f);
        mMaxValue = typedArray.getFloat(R.styleable.RulerView_maxValue, 100.0f);
        mPerValue = typedArray.getFloat(R.styleable.RulerView_perValue, 0.1f);
        mPerLineCount = typedArray.getInteger(R.styleable.RulerView_perLineCount, 10);
        mMiddlePointer = typedArray.getResourceId(R.styleable.RulerView_middlePointer, R.drawable.user_info_ruler_height);
        mPointerType = typedArray.getInteger(R.styleable.RulerView_pointerType, 0);
        mPointerTop = typedArray.getFloat(R.styleable.RulerView_pointerTop, 0);
        mPointerColor = typedArray.getColor(R.styleable.RulerView_pointerColor, mPointerColor);
        mBackgroundColor = typedArray.getColor(R.styleable.RulerView_backgroundColor, mBackgroundColor);
        mStartBackgroundColor = typedArray.getColor(R.styleable.RulerView_startBackgroundColor, mStartBackgroundColor);
        mEndBackgroundColor = typedArray.getColor(R.styleable.RulerView_endBackgroundColor, mEndBackgroundColor);



        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextHeight = getFontHeight(mTextPaint);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setColor(mLineColor);



//        setValue(mSelectorValue, mMinValue, mMaxValue, mPerValue);

    }


    public static int  myfloat(float paramFloat){
        return (int)(0.5F + paramFloat * 1.0f);
    }

    private float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }


    /**
     *
     * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
     * @param minValue   最大数值
     * @param maxValue   最小的数值
     * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
     */
    public void setValue(float selectorValue, float minValue, float maxValue, float per) {
        this.mSelectorValue = selectorValue;
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;
        this.mPerValue = per;
        this.mTotalLine = ((int) ((mMaxValue - mMinValue) / mPerValue)) + 1;


        mMaxOffset = (int) (-(mTotalLine - 1) * mLineSpaceWidth);
        mOffset = (mMinValue - mSelectorValue) / mPerValue * mLineSpaceWidth;
        Log.d("zkk===","mOffset--           "+mOffset  +"         =====mMaxOffset    "+mMaxOffset
                +"  mTotalLine  " +mTotalLine);
        invalidate();
        setVisibility(VISIBLE);
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        mListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            mWidth = w;
            mHeight = h;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        drawTopAndBottomLine(canvas);
        float left, height;
        String value;
        int alpha = 0;
        float scale;
        int srcPointX = mWidth / 2;
        for (int i = 0; i < mTotalLine; i++) {
            left = srcPointX + mOffset + i * mLineSpaceWidth;

            if (left < 0 || left > mWidth) {
                continue;  //  先画默认值在正中间，左右各一半的view。  多余部分暂时不画(也就是从默认值在中间，画旁边左右的刻度线)
            }

            if(mPerLineCount % 10 == 0) {
                if (i % mPerLineCount == 0) {
                    height = mLineMaxHeight;
                } else if (i % (mPerLineCount/2) == 0) {
                    height = mLineMidHeight;
                } else {
                    height = mLineMinHeight;
                }
            }else {
                if (i % mPerLineCount == 0) {
                    height = mLineMaxHeight;
                } else {
                    height = mLineMinHeight;
                }
            }

            if (mAlphaEnable) {
                scale = 1 - Math.abs(left - srcPointX) / srcPointX;
                alpha = (int) (255 * scale * scale);

                mLinePaint.setAlpha(alpha);
                if(scale < 0.5) {
                    scale = 0.5f;
                }
                mTextPaint.setTextSize(mTextSize*scale);
                if(scale == 1) {
                    mTextPaint.setColor(getResources().getColor(R.color.blue));
                }else {
                    mTextPaint.setColor(mTextColor);
                }
            }

            canvas.drawLine(left, 20, left, height + 10, mLinePaint);


            if (i % mPerLineCount == 0) {
                value = String.valueOf((int) (mMinValue + i * mPerValue));
                if (mAlphaEnable) {
                    mTextPaint.setAlpha(alpha);
                }
                canvas.drawText(value, left - mTextPaint.measureText(value) / 2,
                        height + mTextMarginTop + mTextHeight, mTextPaint);    // 在为整数时,画 数值
            }

        }

        drawMiddleLine(canvas);
    }

    /**
     * 绘制顶部和底部的线条
     * @param canvas
     */
    private void drawTopAndBottomLine(Canvas canvas) {
        // 画笔
        Paint paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
        LinearGradient backGradient = new LinearGradient(40, (mHeight -20)/2, mWidth-40, (mHeight -20)/2, new int[]{mStartBackgroundColor, mBackgroundColor , mEndBackgroundColor}, null, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);

        canvas.drawRect(0, 20, mWidth, mHeight - 20, paint);


        Paint linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        LinearGradient lineGradient = new LinearGradient(40, (mHeight -20)/2, mWidth-40, (mHeight -20)/2, new int[]{mStartBackgroundColor, Color.parseColor("#c7c7c7") ,mEndBackgroundColor}, null, Shader.TileMode.CLAMP);
        linePaint.setShader(lineGradient);

        canvas.drawLine(0, 20, mWidth, 20, linePaint);
        canvas.drawLine(0, mHeight - 20, mWidth, mHeight - 20, linePaint);
    }

    /**
     * 绘制中间标尺
     * @param canvas
     */
    private void drawMiddleLine(Canvas canvas) {
        // 画笔
        Paint paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
        paint.setStrokeWidth(mMiddlePointerWidth);
        paint.setColor(mPointerColor);

        if(mPointerType == 0) {
            canvas.drawLine(mWidth/2, mMiddlePointerWidth/2, mWidth/2, mLineMaxHeight + 10, paint);
            canvas.drawCircle(mWidth/2, mMiddlePointerWidth/2, mMiddlePointerWidth/2, paint);
            canvas.drawCircle(mWidth/2, mLineMaxHeight + 10, mMiddlePointerWidth/2, paint);
        }else if(mPointerType == 1) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mMiddlePointer);
            Rect scanRect = new Rect(mWidth/2-10, 0, mWidth/2+10, (int) (mLineMaxHeight+15));
            canvas.drawBitmap(bitmap, null, scanRect, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zkk---","onTouchEvent-");

        int action = event.getAction();
        int xPosition = (int) event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.forceFinished(true);
                mLastX = xPosition;
                mMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countMoveEnd();
                countVelocityTracker();
                return false;
            default:
                break;
        }

        mLastX = xPosition;
        return true;
    }

    private void countVelocityTracker() {
        Log.d("zkk---","countVelocityTracker-");
        mVelocityTracker.computeCurrentVelocity(1000);  //初始化速率的单位
        float xVelocity = mVelocityTracker.getXVelocity(); //当前的速度
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }


    /**
     * 滑动结束后，若是指针在2条刻度之间时，改变mOffset 让指针正好在刻度上。
     */
    private void countMoveEnd() {

        mOffset -= mMove;
        if (mOffset <= mMaxOffset) {
            mOffset = mMaxOffset;
        } else if (mOffset >= 0) {
            mOffset = 0;
        }

        mLastX = 0;
        mMove = 0;

        mSelectorValue = mMinValue + Math.round(Math.abs(mOffset) * 1.0f / mLineSpaceWidth) * mPerValue;
        mOffset = (mMinValue - mSelectorValue) / mPerValue * mLineSpaceWidth;



        notifyValueChange();
        postInvalidate();
    }


    /**
     * 滑动后的操作
     */
    private void changeMoveAndValue() {
        mOffset -= mMove;

        if (mOffset <= mMaxOffset) {
            mOffset = mMaxOffset;
            mMove = 0;
            mScroller.forceFinished(true);
        } else if (mOffset >= 0) {
            mOffset = 0;
            mMove = 0;
            mScroller.forceFinished(true);
        }
        mSelectorValue = mMinValue + Math.round(Math.abs(mOffset) * 1.0f / mLineSpaceWidth) * mPerValue;


        notifyValueChange();
        postInvalidate();
    }

    private void notifyValueChange() {
        if (null != mListener) {
            mListener.onValueChange(mSelectorValue);
        }
    }


    /**
     * 滑动后的回调
     */
    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    @Override
    public void computeScroll() {
        Log.d("zkk---","computeScroll-");
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {     //mScroller.computeScrollOffset()返回 true表示滑动还没有结束
            if (mScroller.getCurrX() == mScroller.getFinalX()) {
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}