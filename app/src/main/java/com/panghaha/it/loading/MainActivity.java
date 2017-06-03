package com.panghaha.it.loading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loading1,loading11,loading2,loading3,loading4,loading5,loading6,loading7,loading8
    ,loading9,loading10,loading12,loading13,loading14,loading15,loading16,loading17,loading18,loading19,loading20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading1 = (Button) findViewById(R.id.loading1);
        loading11 = (Button) findViewById(R.id.loading11);
        loading2 = (Button) findViewById(R.id.loading2);
        loading3 = (Button) findViewById(R.id.loading3);
        loading4 = (Button) findViewById(R.id.loading4);
        loading5 = (Button) findViewById(R.id.loading5);
        loading6 = (Button) findViewById(R.id.loading6);
        loading7 = (Button) findViewById(R.id.loading7);
        loading8 = (Button) findViewById(R.id.loading8);
        loading9 = (Button) findViewById(R.id.loading9);
        loading10 = (Button) findViewById(R.id.loading10);
        loading12 = (Button) findViewById(R.id.loading12);
        loading13 = (Button) findViewById(R.id.loading13);
        loading14 = (Button) findViewById(R.id.loading14);
        loading15 = (Button) findViewById(R.id.loading15);
        loading16 = (Button) findViewById(R.id.loading16);
        loading17 = (Button) findViewById(R.id.loading17);
        loading18 = (Button) findViewById(R.id.loading18);
        loading19 = (Button) findViewById(R.id.loading19);
        loading20 = (Button) findViewById(R.id.loading20);

        loading1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Loading1.class);
                startActivity(intent);
            }
        });
        loading11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(MainActivity.this,Loading11.class);
                startActivity(intent11);
            }
        });
        loading2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,Loading2.class);
                startActivity(intent2);
            }
        });
        loading3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this,Loading3.class);
                startActivity(intent3);
            }
        });
        loading4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this,Loading4.class);
                startActivity(intent4);
            }
        });
        loading5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,Loading5.class);
                startActivity(intent5);
            }
        });
        loading6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(MainActivity.this,Loading6.class);
                startActivity(intent6);
            }
        });
        loading7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(MainActivity.this,Loading7.class);
                startActivity(intent7);
            }
        });
        loading8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(MainActivity.this,Loading8.class);
                startActivity(intent8);
            }
        });

        loading9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(MainActivity.this,Loading9.class);
                startActivity(intent9);
            }
        });

        loading10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(MainActivity.this,Loading10.class);
                startActivity(intent10);
            }
        });

        loading12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(MainActivity.this,Loading12.class);
                startActivity(intent12);
            }
        });

        loading13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent13 = new Intent(MainActivity.this,Loading13.class);
                startActivity(intent13);
            }
        });

        loading14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent14 = new Intent(MainActivity.this,Loading14.class);
                startActivity(intent14);
            }
        });
        loading15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent15 = new Intent(MainActivity.this,Loading15.class);
                startActivity(intent15);
            }
        });
        loading16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent16 = new Intent(MainActivity.this,Loading16.class);
                startActivity(intent16);
            }
        });
        loading17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent17= new Intent(MainActivity.this,Loading17.class);
                startActivity(intent17);
            }
        });
        loading18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent18 = new Intent(MainActivity.this,Loading18.class);
                startActivity(intent18);
            }
        });
        loading19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent19 = new Intent(MainActivity.this,Loading19.class);
                startActivity(intent19);
            }
        });
        loading20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent20 = new Intent(MainActivity.this,Loading20.class);
                startActivity(intent20);
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//
//            case R.id.loading1:
//                Intent intent = new Intent(MainActivity.this,Loading1.class);
//                startActivity(intent);
//            case R.id.loading2:
//                Intent intent2 = new Intent(MainActivity.this,Loading2.class);
//                startActivity(intent2);
//            case R.id.loading3:
//                Intent intent3 = new Intent(MainActivity.this,Loading3.class);
//                startActivity(intent3);
//            case R.id.loading4:
//                Intent intent4 = new Intent(MainActivity.this,Loading4.class);
//                startActivity(intent4);
//            case R.id.loading5:
//                Intent intent5 = new Intent(MainActivity.this,Loading5.class);
//                startActivity(intent5);
//            case R.id.loading6:
//                Intent intent6 = new Intent(MainActivity.this,Loading6.class);
//                startActivity(intent6);
//            case R.id.loading7:
//                Intent intent7 = new Intent(MainActivity.this,Loading7.class);
//                startActivity(intent7);
//            case R.id.loading11:
//                Intent intent11 = new Intent(MainActivity.this,Loading11.class);
//                startActivity(intent11);
//        }
//    }
}
