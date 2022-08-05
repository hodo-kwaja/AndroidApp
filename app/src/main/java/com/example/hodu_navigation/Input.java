package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.github.chrisbanes.photoview.PhotoView;

//https://github.com/chrisbanes/PhotoView 라이브러리 가져와서씀
public class Input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        // 이미지 줌인 줌 아웃
        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.map2);

        // 출발역 텍스트박스 클릭시 액티비티 전환
        EditText departure=(EditText) findViewById(R.id.Edit1);
        departure.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Search.class);

                startActivity(intent);

                Intent intent1 = getIntent();
                String str = intent1.getStringExtra("str");

                departure.setText(str);

            }
        });

        // 도착역 텍스트박스 클릭시 액티비티 전환
        EditText arrival=(EditText) findViewById(R.id.Edit2);
        arrival.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

    }
}