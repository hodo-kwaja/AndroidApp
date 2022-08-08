package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hodu_navigation.R;

public class RouteTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_time);

        //최소환승 버튼 클릭시 화면 전환
        TextView min_transfer=(TextView) findViewById(R.id.min_transfer);
        min_transfer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RouteTransfer.class);
                startActivity(intent);
            }
        });

        //혼잡도 버튼 클릭시 화면 전황
        TextView congestion=(TextView) findViewById(R.id.congestion);
        congestion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RouteCongest.class);
                startActivity(intent);
            }
        });


    }
}