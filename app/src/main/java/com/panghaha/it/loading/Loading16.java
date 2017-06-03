package com.panghaha.it.loading;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wang.avi.AVLoadingIndicatorView;

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
public class Loading16 extends Activity {

    private Button show,hide;
    private AVLoadingIndicatorView avLoadingIndicatorView;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading16);

        show = (Button) findViewById(R.id.show);
        hide = (Button) findViewById(R.id.hide);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avi);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avLoadingIndicatorView.show();
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avLoadingIndicatorView.hide();
            }
        });


    }
}
