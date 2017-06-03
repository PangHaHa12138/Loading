package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.panghaha.it.loading.utils.ToastUtil;

/***
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the more protecting ━━━
 * <p/>
 * Created by PangHaHa12138 on 2017/6/3.
 *
 *  绿色固定位置进度条
 */
public class Loading1 extends Activity {
    private ProgressBar progesss;
    private TextView progesssValue;
    private LinearLayout full;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading1);

        progesss = (ProgressBar) findViewById(R.id.progesss1);
        progesssValue = (TextView) findViewById(R.id.progesss_value1);
        full = (LinearLayout) findViewById(R.id.full);

        initview();

    }

    private void initview() {

        progesss.setProgress(66);
        progesssValue.setText(new StringBuffer().append(progesss.getProgress()).append("%"));

        setPosWay1();
//        ToastUtil.showToast("进度为66");
//        Toast.makeText(this,"进度为:--66",Toast.LENGTH_SHORT).show();

        //        full.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int w = getWindowManager().getDefaultDisplay().getWidth();
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        x1 = (int) event.getRawX();
//                        progesss.setProgress(100 * x1 / w);
//                        setPos();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        x2 = (int) event.getRawX();
//                        dx = x2 - x1;
//                        if (Math.abs(dx) > w / 100) { //改变条件 调整进度改变速度
//                            x1 = x2; // 去掉已经用掉的距离， 去掉这句 运行看看会出现效果
//                            progesss.setProgress(progesss.getProgress() + dx * 100 / w);
//                            setPos();
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return true;
//            }
//        });


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setPos();
        }
    }
    private void setPosWay1() {
        progesssValue.post(new Runnable() {
            @Override
            public void run() {
                setPos();
            }
        });
    }

    /**
     * 设置进度显示在对应的位置
     */
    public void setPos() {
        int w = getWindowManager().getDefaultDisplay().getWidth();
        Log.e("w=====", "" + w);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
        int pro = progesss.getProgress();
        int tW = progesssValue.getWidth();
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
            params.leftMargin = 0;
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);

    }
}
