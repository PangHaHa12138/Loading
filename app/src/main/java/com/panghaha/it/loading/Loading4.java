package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

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
 */
public class Loading4 extends Activity {
    private BubbleSeekBar mBbubbleSeekBar;
    private TextView numtext;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading4);

        mBbubbleSeekBar = (BubbleSeekBar) findViewById(R.id.bubbleseekbar);
        numtext = (TextView) findViewById(R.id.numjd);


/**
 *  如果嵌套scrollview 会有随着scrollview滑动气泡离进度条越来越高的bug 下面的方法可以修正偏移量
 *
 *      scrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
        @Override
        public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        // 调用修正偏移方法
        mBbubbleSeekBar.correctOffsetWhenContainerOnScrolling();

    }
    });

 */
        //进度条
        mBbubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {

//                LogUtil.d("进度1---->>", progress+"");
                numtext.setText("当前进度："+progress);
            }

            @Override
            public void getProgressOnActionUp(int progress, float progressFloat) {
            }

            @Override
            public void getProgressOnFinally(int progress, float progressFloat) {
            }
        });

//            mBbubbleSeekBar.setProgress(proooo);
    }
}
