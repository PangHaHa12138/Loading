package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;

import com.panghaha.it.loading.View.CirclePercentView;
import com.panghaha.it.loading.View.PercentCircle;

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
public class Loading8 extends Activity {

    private PercentCircle percentCircle,percentCircle2;

    private CirclePercentView circlePercentView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading8);

        percentCircle = (PercentCircle) findViewById(R.id.percentCircle);
        percentCircle2 = (PercentCircle) findViewById(R.id.percentCircle2);
        percentCircle.setTargetPercent(65);
        percentCircle2.setTargetPercent(88);


    }
}
