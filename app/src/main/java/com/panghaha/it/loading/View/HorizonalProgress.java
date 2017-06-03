package com.panghaha.it.loading.View;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Description 酷炫水平进度条
 * Author: pang
 * Date: 2016-08-22
 * Time: 14:59
 */
public class HorizonalProgress extends View {
    private int viewWidth;//view的宽度
    private int viewHigth;//view的高度
    private Paint mPaint;//画笔  212 62 96
    private int colorSecondProgress = Color.argb(255,229,237,245);//背景圆颜色，进度条背景色
    private int colorProgress = Color.argb(255,19,146,255);//背景圆颜色，一级进度条颜色
    private int progressHeight = 30;//进度条的高度
    private RectF rectF = new RectF();
    private int curProgress = 0; //必须小于等于100 大于0
    private int oldProgress = 0;
    
    public void setColorSecondProgress(int colorSecondProgress) {
		this.colorSecondProgress = colorSecondProgress;
	}
	public void setColorProgress(int colorProgress) {
		this.colorProgress = colorProgress;
	}
	public void setProgressHeight(int progressHeight) {
		this.progressHeight = progressHeight;
	}
	public void setCurProgress(int curProgress) {
        this.curProgress = curProgress;
        invalidate();
    }
    public HorizonalProgress(Context context) {
        this(context,null);
    }

    public HorizonalProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizonalProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        //初始化坐标画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//
        mPaint.setColor(colorSecondProgress);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);//空心
        curProgress = 0;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height;
        int width;

        //宽度测量
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getMeasuredWidth();
        }
        viewWidth = width;
        //高度测量
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getMeasuredHeight();
        }
        //进度条的高度根据
        viewHigth = progressHeight;

        setMeasuredDimension(width, viewHigth);
    }

    /**
     * 绘制进度
     * @param canvas
     */
    private  void drawProgress(Canvas canvas){
        rectF.left = 0;
        rectF.right = viewWidth;
        rectF.top = 0;
        rectF.bottom = viewHigth;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(colorSecondProgress);
        //灰色背景
        canvas.drawRoundRect(rectF,viewHigth/2,viewHigth/2,mPaint);

        //进度
        mPaint.setColor(colorProgress);
        rectF.right = curProgress * viewWidth / 100;
        canvas.drawRoundRect(rectF,viewHigth/2,viewHigth/2,mPaint);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawProgress(canvas);
    }
    public void setProgress(int progress){
        if (progress < 0 || progress > 100)
            return;
        ObjectAnimator o = ObjectAnimator.ofInt(this, "curProgress", oldProgress, progress);
        o.setDuration(1000);
        o.setInterpolator(new DecelerateInterpolator());
        o.start();
        oldProgress = progress;
    }
}
