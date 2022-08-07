package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

//https://github.com/chrisbanes/PhotoView 라이브러리 가져와서씀
public class Input extends AppCompatActivity {
    static String departure_text;
    static String arrival_text;
    String text;
    static int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        EditText departure=(EditText) findViewById(R.id.Edit1);// 출발역 입력칸
        EditText arrival=(EditText) findViewById(R.id.Edit2); // 도착역 입력칸

        Intent intent = getIntent();
        Log.d("역이름","1");
        intent = getIntent();
        Log.d("역이름","2");
        text= intent.getStringExtra("selected_item"); //search 액티비티에서 역이름 받아오기
        Log.d("역이름","역이름"+text);

        if(count==1) {
            Log.d("c","출발역출력"+count);
            Toast.makeText(getApplicationContext(),"count1", Toast.LENGTH_SHORT).show();
            departure_text = text;
            departure.setText(departure_text);
            count = 0;
            Log.d("증가","카운트증가"+count);
        }
        else if(count==2){

            Log.d("c","도착역출력"+count);
            Toast.makeText(getApplicationContext(),"count1이상", Toast.LENGTH_SHORT).show();
            Log.d("역이름","4");
            arrival_text = text;
            Log.d("역이름","5");
            arrival.setText(arrival_text);
            count = 0;
        }

        /*

        text= intent.getStringExtra("selected_item"); //search 액티비티에서 역이름 받아오기

        departure.setText(text); // 받아온 역이름 출발역 텍스트박스에 출력
        arrival.setText(text); // 받아온 역이름 출발역 텍스트박스에 출력*/



        // 이미지 줌인 줌 아웃
        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.map2);

        // 출발역 텍스트박스 클릭시 액티비티 전환
        departure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"de", Toast.LENGTH_SHORT).show();
                count=1;
                Log.d("departure","출발역count"+count);
                Intent intent = new Intent(getApplicationContext(), Search.class); //search 액티비티로 전환
                startActivity(intent);


            }
        });

        // 도착역 텍스트박스 클릭시 액티비티 전환
        arrival.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"arr", Toast.LENGTH_SHORT).show();
                count=2;
                Log.d("arrival","도착역count"+count);
                Intent intent = new Intent(getApplicationContext(), Search.class); //search 액티비티로 전환
                startActivity(intent);
            }
        });



    }

}