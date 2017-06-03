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

import com.panghaha.it.loading.R;


/**
 * Description 圆形装逼进度条
 * Author: tu
 * Date: 2016-08-19
 * Time: 14:33
 */
public class CircleProgress extends View{

	private float textSize = getResources().getDimension(R.dimen.text_size_14);
    private float dotX, dotY;//圆点xy
    private int viewWidth;//view的宽度
    private int viewHigth;//view的高度
    private Paint mPaint,mPaintArc;//画笔  212 62 96
//    private int colorBg = Color.argb(255,54,68,76);//背景圆颜色
    private int colorBg = Color.WHITE;//背景圆颜色
//    private int colorWhite = Color.argb(255,255,255,255);//文字颜色
    private int colorWhite = Color.BLACK;//文字颜色
//    private int colorWhite = getResources().getColor(R.color.titbar_color);//文字颜色
//    private int colorBlack = Color.argb(255,34,49,59);//第二刻度颜色
    private int colorBlack = Color.WHITE;//第二刻度颜色
    private int colorBlue = Color.argb(255,94,248,249);//刻度颜色
//    private int colorBlue = Color.WHITE;//刻度颜色
    private int pandding = 10;
    private RectF rectF;
    private float radius = 10;//半径
    private float scaleLineLenth = 5;//刻度线长
    private int scaleAngle = 10;//刻度间隔
    private int scaleWidth = 5;//刻度宽度
    private int curProgress = 0;//0 ~ 100进度 当前进度
    private int oldProgress = 0;

	public void setColorBlue(int colorBlue) {
		this.colorBlue = colorBlue;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public int getCurProgress() {
        return curProgress;
    }

    public void setCurProgress(int curProgress) {
        this.curProgress = curProgress;
        invalidate();
    }

    public CircleProgress(Context context) {
        this(context,null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化画笔
     */
    private void init(Context context) {

        //初始化坐标画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//
        mPaintArc = new Paint(Paint.ANTI_ALIAS_FLAG);//
        mPaint.setColor(colorWhite);
        mPaintArc.setColor(colorBg);
        mPaint.setAntiAlias(true);
        mPaintArc.setAntiAlias(true);
        mPaint.setTextSize(15);
        mPaint.setStyle(Paint.Style.STROKE);//空心
        //当前进度

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
        dotX = width / 2;
        viewWidth = width;
        //高度测量
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getMeasuredHeight();
        }
        viewHigth = height;
        dotY = height / 2;
        radius = dotX-(getPaddingLeft() + getPaddingRight())/2;
        scaleLineLenth = radius/3;
        rectF = new RectF(dotX - radius, dotY - radius, dotX + radius, dotY + radius);
        setMeasuredDimension(width, height);
    }
    private void drawProgress(Canvas canvas){
    	if(mPaintArc == null){
    		return;
    	}
        //圆
        mPaintArc.setStyle(Paint.Style.FILL);
        canvas.drawCircle(dotX, dotY, radius, mPaintArc);
        //中心进度值
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//实心
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(textSize);
        mPaint.setColor(colorWhite);
        canvas.drawText(curProgress + "%",dotX,
                dotY+getResources().getDimension(R.dimen.text_size_14)/2
                ,mPaint);
        //黑色刻度  12点钟方向为起始点（-90°），正时针方法绘制
        for (int angle = -90; angle <= 270; angle += scaleAngle){
            float xY[] = caculCoordinate(angle);
            if(xY != null) {
                mPaint.setStrokeWidth(scaleWidth);
                mPaint.setColor(colorBlack);
                canvas.drawLine(xY[0], xY[1],xY[2],xY[3], mPaint);
            }
        }
        //进度算法
        //360 除与 scaleAngle（进度间隔10） = 36； 再拿进度总数100换算当前进度
        //算出当前进度占几个刻度
        int curProgressCount = curProgress * (360/scaleAngle) /100;
        int angleStart = -90;
        for (int count = 0; count < curProgressCount;count ++){
            float xY[] = caculCoordinate(angleStart);
            if(xY != null) {
                mPaint.setStrokeWidth(scaleWidth);
                mPaint.setColor(colorBlue);
                canvas.drawLine(xY[0], xY[1],xY[2],xY[3], mPaint);
            }
            angleStart += scaleAngle;
        }

    }

    /**
     * 根据圆心角 计算圆周上的坐标
     * @param angle
     * @return xY[0] startX; xY[1] startY; xY[2] endX; xY[3] endY;
     */
    private float[] caculCoordinate(int angle){
        //angle >180     angle = angle -180
        float xY[] = new float[4];
        //角度处理
        int tempAngle = Math.abs(angle);
        float tempScaleLineLenth = scaleLineLenth;

         if(270 > tempAngle && tempAngle >= 180) {
            tempAngle = tempAngle - 180;
            xY[0] = dotX - getCoordinateX(tempAngle,radius);
            xY[1] = dotY - getCoordinateY(tempAngle,radius);

            xY[2] = xY[0] + getCoordinateX(tempAngle,tempScaleLineLenth);
            xY[3] = xY[1] + getCoordinateY(tempAngle,tempScaleLineLenth);
        }else if(180 > tempAngle && tempAngle > 90){
            tempAngle = 180 - tempAngle;
            xY[0] = dotX - getCoordinateX(tempAngle,radius);
            xY[1] = dotY + getCoordinateY(tempAngle,radius);

            xY[2] = xY[0] + getCoordinateX(tempAngle,tempScaleLineLenth);
            xY[3] = xY[1] - getCoordinateY(tempAngle,tempScaleLineLenth);
        }else if(90 >= tempAngle && tempAngle >= 0){
            xY[0] = dotX + getCoordinateX(tempAngle,radius);
            xY[1] = angle < 0 ? dotY - getCoordinateY(tempAngle,radius) : dotY + getCoordinateY(tempAngle,radius);

            xY[2] = xY[0] - getCoordinateX(tempAngle,tempScaleLineLenth);
            xY[3] = angle < 0 ? xY[1] + getCoordinateY(tempAngle,tempScaleLineLenth) : xY[1] - getCoordinateY(tempAngle,tempScaleLineLenth);
        }

        return xY;
    }


    /**
     * 获取圆周上y值相对值
     * @param tempAngle
     * @param radius 算开始坐标是传半径，算结束坐标时传刻度线的长度
     * @return
     */
    private float getCoordinateY(int tempAngle,float radius){

        //利用正弦函数算出y坐标
        return (float) (Math.sin(tempAngle*Math.PI/180)*(radius - 15)); //10 是离圆弧的距离
    }
    /**
     * 获取圆周上X值相对值
     * @param tempAngle
     * @return
     */
    private float getCoordinateX(int tempAngle,float radius){

        //利用余弦函数算出y坐标
        return (float) (Math.cos(tempAngle*Math.PI/180)*(radius - 15));
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
