package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.panghaha.it.loading.View.MyProgress;

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
public class Loading3 extends Activity {

    private EditText et_progress;
    private MyProgress myProgress;
    private TextView textnum;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading3);

        et_progress = (EditText) findViewById(R.id.et_progress);
        myProgress = (MyProgress) findViewById(R.id.myprogress);
//        textnum = (TextView) findViewById(R.id.numjd);

//        textnum.setText("当前进度："+myProgress.getJindu());
    }

    public void onClick(View v){
        if(TextUtils.isEmpty(et_progress.getText().toString()))
            return;
        //更新进度条
        myProgress.updateProgress(Integer.parseInt(et_progress.getText().toString()));

    }
}
