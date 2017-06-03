package com.panghaha.it.loading.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.panghaha.it.loading.R;


/**
 * Created by pang on 2017/3/28.
 *
 *
 * 绘制百分比的圆，一共有三部分，分别是里面的文字、背景圆、圆环；
 * 思路：首先需要三支画笔, 设置画笔对应的属性等；
 *
 */
public class PercentCircle extends View {



    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private Paint mRingPaint;

    private int mCircleX;
    private int mCircleY;

    private float mCurrentAngle;
    private RectF mArcRectF;
    private float mStartSweepValue;

    private int mTargetPercent;
    private int mCurrentPercent;

    private int mDefaultRadius = 60;
    private int mRadius;

    private int mDefaultBackgroundColor = 0xffafb4db;
    private int mBackgroundColor;

    private int mDefaultRingColor = 0xff6950a1;
    private int mRingColor;

    private int mDefaultTextSize;
    private int mTextSize;


    private int mDefaultTextColor = 0xffffffff;
    private int mTextColor;

    public PercentCircle(Context context) {
        super(context);
        init(context);

    }

    public PercentCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 自定义属性，attrs
        // 使用TypedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentageRing);

        // 背景圆的半径
        mRadius = typedArray.getInt(R.styleable.PercentageRing_radius, mDefaultRadius);

        // 背景圆的颜色
        mBackgroundColor = typedArray.getColor(R.styleable.PercentageRing_circleBackground, mDefaultBackgroundColor);

        // 文字的颜色 默认白色
        mTextColor = typedArray.getColor(R.styleable.PercentageRing_textColor, mDefaultTextColor);

        // 外圆环的颜色
        mRingColor = typedArray.getColor(R.styleable.PercentageRing_ringColor, mDefaultRingColor);

        // Be sure to call recycle() when done with them
        typedArray.recycle();

        init(context);
    }

    public PercentCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        //圆环开始角度 -90° 正北方向
        mStartSweepValue = -90;
        //当前角度
        mCurrentAngle = 0;
        //当前百分比
        mCurrentPercent = 0;

        //设置中心园的画笔
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        //设置文字的画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth((int) (0.025*mRadius));
        mTextPaint.setTextSize(mRadius/2);   //文字大小为半径的一半
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        //设置外圆环的画笔
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth((float) (0.075*mRadius));

        //获得文字的字号 因为要设置文字在圆的中心位置
        mTextSize = (int) mTextPaint.getTextSize();
    }

    // 主要是测量wrap_content时候的宽和高，因为宽高一样，只需要测量一次宽即可，高等于宽
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(widthMeasureSpec));
    }

    // 当wrap_content的时候，view的大小根据半径大小改变，但最大不会超过屏幕
    private int measure(int measureSpec){
        int result = 0;
        //1、先获取测量模式 和 测量大小
        //2、如果测量模式是MatchParent 或者精确值，则宽为测量的宽
        //3、如果测量模式是WrapContent ，则宽为 直径值 与 测量宽中的较小值；否则当直径大于测量宽时，会绘制到屏幕之外；
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if( specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            //result = 2*mRadius;
            //result =(int) (1.075*mRadius*2);
            result =(int) (mRadius*2 + mRingPaint.getStrokeWidth()*2);
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //1、如果半径大于圆心的横坐标，需要手动缩小半径的值，否则画到屏幕之外；
        //2、改变了半径，则需要重新设置字体大小；
        //3、改变了半径，则需要重新设置外圆环的宽度
        //4、画背景圆的外接矩形，用来画圆环；
        mCircleX = getMeasuredWidth()/2;
        mCircleY = getMeasuredHeight()/2;
        if(mRadius > mCircleX){
            mRadius = mCircleX;
            mRadius = (int) (mCircleX-0.075*mRadius);
            mTextPaint.setStrokeWidth((float) (0.025*mRadius));
            mTextPaint.setTextSize(mRadius/2);
            mRingPaint.setStrokeWidth((float) (0.075*mRadius));
            mTextSize = (int) mTextPaint.getTextSize();
        }
        mArcRectF = new RectF(mCircleX-mRadius, mCircleY-mRadius, mCircleX+mRadius, mCircleY+mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1、画中间背景圆
        //2、画文字
        //3、画圆环
        //4、判断进度，重新绘制
        canvas.drawCircle(mCircleX, mCircleY, mRadius, mBackgroundPaint);
        canvas.drawText(String.valueOf(mCurrentPercent)+"%", mCircleX, mCircleY+mTextSize/4, mTextPaint);
        canvas.drawArc(mArcRectF, mStartSweepValue, mCurrentAngle, false, mRingPaint);

        if(mCurrentPercent < mTargetPercent){
            //当前百分比+1
            mCurrentPercent+=1;
            //当前角度+360
            mCurrentAngle+=3.6;
            //每10ms重画一次
            postInvalidateDelayed(10);
        }

        //canvas.drawRect(mArcRectF, mRingPaint);
    }

    public void setTargetPercent(int targetPercent){
        mTargetPercent = targetPercent;
    }
}