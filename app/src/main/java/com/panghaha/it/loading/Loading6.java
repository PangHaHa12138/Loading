package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chenzy.owloading.OWLoadingView;

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
public class Loading6 extends Activity {

    private OWLoadingView owLoadingView;
    private Button btn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading6);

        owLoadingView = (OWLoadingView) findViewById(R.id.owloading);
        btn = (Button) findViewById(R.id.btn);

//        owLoadingView.setAutoStartAnim(true);//设置自动开启动画
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn.getText().equals("开始")){
                    owLoadingView.startAnim();
                    btn.setText("中止");
                }else{
                    owLoadingView.stopAnim();
                    btn.setText("开始");
                }
            }
        });
    }
}
