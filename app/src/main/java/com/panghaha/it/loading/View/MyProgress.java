package com.panghaha.it.loading.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;

import com.panghaha.it.loading.R;


/**
 * Description 组合装逼进度
 * Author: pang
 * Date: 2016-08-22
 * Time: 15:36
 */
public class MyProgress extends RelativeLayout {
	
	private Context context;
	private float textProgressSize;//圆形进度条中间文字
	private float horizonalProgressHeight;//横条高度
	private float circleProgressRadus;//圆形半径
	private int colorProgress;//进度条颜色,圆盘刻度颜色
	private int colorSecondProgress;//进度条背景颜色
	
	private boolean flagPaint = true;//绘图标志
	public int newProgress;
    private CircleProgress mCircleProgress;
    private HorizonalProgress mHorizonalProgress;
    //private int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();//最小滑动阀值

    public MyProgress(Context context) {
        this(context,null);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
        ViewTreeObserver vto = getViewTreeObserver();
	    vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	        @Override
	        public void onGlobalLayout() {
	        	if(flagPaint){
	        		flagPaint = false;
	        		initHorizonalProgress();
	        	}else{
	        		
	        	}
	        }
	  });
    }

    private void init(AttributeSet attrs){
    	flagPaint = true;
    	//获取自定义xml属性
    	TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myMagicProgress);
    	int n = typedArray.getIndexCount();
    	for (int i = 0; i < n; i++) {
    		int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.myMagicProgress_circleProgressRadus:
//				Log.e("tag", "typedArray.getDimension(attr, 0)="+typedArray.getDimension(attr, 0)+",typedArray.getDimension(i, 0)="+typedArray.getDimension(i, 0));
				circleProgressRadus = typedArray.getDimension(attr, 0);
				break;
			case R.styleable.myMagicProgress_horizonalProgressHeight:
				horizonalProgressHeight = typedArray.getDimension(attr, 0);
							break;
			case R.styleable.myMagicProgress_textProgressSize:
				textProgressSize = typedArray.getDimension(attr, 0);
				break;
			case R.styleable.myMagicProgress_colorProgress:
				colorProgress = typedArray.getColor(attr, Color.GRAY);
				break;
			case R.styleable.myMagicProgress_colorSecondProgress:
				colorSecondProgress = typedArray.getColor(attr, Color.GRAY);
				break;
			default:
				break;
			}
		}
    	
    	//横向进度条稍后设置参数，需要圆形进度条绘图完成，根据宽度绘制左右边距
        mHorizonalProgress = new HorizonalProgress(getContext());
        mHorizonalProgress.setProgressHeight((int) horizonalProgressHeight);
        mHorizonalProgress.setColorSecondProgress(colorSecondProgress);
        mHorizonalProgress.setColorProgress(colorProgress);
        
        int radus = (int) circleProgressRadus;
        mCircleProgress = new CircleProgress(getContext());
        mCircleProgress.setTextSize(textProgressSize);
        mCircleProgress.setColorBlue(colorProgress);
        LayoutParams cp_lp = new LayoutParams(radus,radus);
        cp_lp.addRule(RelativeLayout.CENTER_VERTICAL);
        mCircleProgress.setLayoutParams(cp_lp);

        addView(mHorizonalProgress);
        addView(mCircleProgress);

        initView();
        
    }
    private float mDownX;
    private float disX, llX;
    private void initView(){
        mCircleProgress.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = event.getX();
//                      Log.d("Tag",event.getX() + ":" + event.getRawX());
                        break;
                    case MotionEvent.ACTION_MOVE:
//                      Log.d("Tag",event.getX() + ":" + event.getRawX());
                         disX = event.getX() - mDownX;
                         llX = mCircleProgress.getX() + disX;
//                      Log.e("tag", "disX="+disX+",llX="+llX+",mHorizonalProgress.getWidth()="+mHorizonalProgress.getWidth());
                        //校正边界，反正滑块划出
                        llX = checkoundary(llX);
                        jindu = llX;
                        mCircleProgress.setX(llX);
                        //计算进度条百分比
                        newProgress = getProgress(llX);
                        //更新进度条
                        updateProgress(newProgress);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    /**
     * 绘制横向进度条
     */
    public void initHorizonalProgress(){
//      Log.e("tag", "mCircleProgress.getWidth()="+mCircleProgress.getWidth());
    	//设置边距，左右空出滑块半径的距离
        LayoutParams hp_lp = new LayoutParams(LayoutParams.MATCH_PARENT,(int) horizonalProgressHeight);
        hp_lp.leftMargin = mCircleProgress.getWidth()/2;
        hp_lp.rightMargin = mCircleProgress.getWidth()/2;
        hp_lp.addRule(RelativeLayout.CENTER_VERTICAL);
        mHorizonalProgress.setLayoutParams(hp_lp);
    }
    
    /**
     * 校正边界
     * @return
     */
    public float checkoundary(float llX){
    	if(llX<0){
        	llX = 0f;
        }else if(llX>mHorizonalProgress.getWidth()){
        	llX = mHorizonalProgress.getWidth();
        }
    	return llX;
    }
    
    /**
     * 换算百分比
     */
    public int getProgress(float llX){
    	return (int) ((llX/mHorizonalProgress.getWidth())*100);
    }
    
    /**
     * 更新进度
     * @param newProgress
     */
    public void updateProgress(int newProgress){
//    	Log.e("tag", "newProgress="+newProgress);
    	mCircleProgress.setProgress(newProgress);
    	mHorizonalProgress.setProgress(newProgress);
    }
    private float jindu;
    /**外部调用获取进度*/
    public String getJindu(){

        return jindu+"";

    }
}
