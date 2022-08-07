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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// w 평일, a 토요일, u 일요일
// hh시 mm분

//https://github.com/chrisbanes/PhotoView 라이브러리 가져와서씀
public class Input extends AppCompatActivity {
    static String departure_text; //출발역
    static String arrival_text;  //도착역
    String formatedNow; //시간
    char week; //요일

    String jsonData;

    String text;
    static int count =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //요일
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int e = calendar.get(Calendar.DAY_OF_WEEK);

        //시간

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        formatedNow = formatter.format(now);

        if (e == '2' || e == '3' || e == '4' || e == '5' || e == '6')
            week = 'w';
        else if (e == '7')
            week = 'a';
        else week = 'u';

       /* Log.d("현재시간날짜","요일: "+week);
        Log.d("현재시간날짜","now: "+formatedNow);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        EditText departure = (EditText) findViewById(R.id.Edit1);// 출발역 입력칸
        EditText arrival = (EditText) findViewById(R.id.Edit2); // 도착역 입력칸

        Intent intent = getIntent();
        intent = getIntent();
        text = intent.getStringExtra("selected_item"); //search 액티비티에서 역이름 받아오기

        if (count == 1) {
            arrival.setText(arrival_text);
            departure_text = text;
            departure.setText(departure_text);
            count = 0;
        } else if (count == 2) {
            departure.setText(departure_text);
            arrival_text = text;
            arrival.setText(arrival_text);
            count = 0;
        }

        // 이미지 줌인 줌 아웃
        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.map2);

        // 출발역 텍스트박스 클릭시 액티비티 전환
        departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 1;
                Intent intent = new Intent(getApplicationContext(), Search.class); //search 액티비티로 전환
                startActivity(intent);

            }
        });

        // 도착역 텍스트박스 클릭시 액티비티 전환
        arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 2;
                Intent intent = new Intent(getApplicationContext(), Search.class); //search 액티비티로 전환
                startActivity(intent);
            }
        });

        Button search_button = (Button) findViewById(R.id.button1);
        search_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setDefaultJsonData();
                Intent intent = new Intent(getApplicationContext(), RouteTime.class);
                startActivity(intent);
            }
        });
    }

    public void setDefaultJsonData(String departure_text, String arrival_text, String formatedNow, char week) {
        this.departure_text = departure_text;
        this.arrival_text = arrival_text;
        this.formatedNow =formatedNow;
        this.week = week;
    }

    public String getdeparture() {
        return departure_text;
    }
    public String getarrival() {
        return arrival_text;
    }
    public String getformatedNow() {
        return formatedNow;
    }
    public String getdeparture() {
        return departure_text;
    }



   /* public void setDefaultJsonData(){
        try {
            //TODO JSONObject 객체 생성 및 데이터 삽입
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("departure", "departure_text");
            jsonObject.put("arrival", arrival_text);
            jsonObject.put("time",formatedNow);
            jsonObject.put("day", week);

            //TODO 저장된 JSONObject 데이터 출력 실시
            jsonData = jsonObject.toString(); //데이터 파싱 위해서 변수에 담습니다
            Log.d("---","---");
            Log.d("//===========//","================================================");
            Log.d("","\n"+"[저장된 데이터 : "+jsonObject.toString()+"]");
            Log.d("//===========//","================================================");
            Log.d("---","---");

            getDefaultJsonDataParse();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getDefaultJsonDataParse(){
        try {

            //TODO 초기 저장된 데이터를 JSONObject에 담는다
            JSONObject jsonObject = new JSONObject(jsonData);
            Log.d("---","---");
            Log.d("//===========//","================================================");
            Log.d("","\n"+"[A_Main > getDefaultJsonDataParse() 메소드 : 기본 JsonObject 형태 데이터 파싱 실시]]");
            Log.d("","\n"+"[저장된 데이터 : "+ jsonObject.toString() +"]");
            Log.d("//===========//","================================================");
            Log.d("---","---");

            //TODO 필요한 JSONObject 데이터를 파싱한다
            String departure = "";
            String arrival = "";
            String time = "";
            String day = "";

            if(jsonObject.has("departure") == true){ //TODO JSON 데이터에서 name 이란 key 값이 존재하는 경우
                departure = String.valueOf(jsonObject.get("departure")); //TODO name 키값 데이터를 가져온다
            }
            if(jsonObject.has("arrival") == true){ //TODO JSON 데이터에서 name 이란 key 값이 존재하는 경우
                arrival = String.valueOf(jsonObject.get("arrival")); //TODO name 키값 데이터를 가져온다
            }
            if(jsonObject.has("time") == true){ //TODO JSON 데이터에서 name 이란 key 값이 존재하는 경우
                time = String.valueOf(jsonObject.get("time")); //TODO name 키값 데이터를 가져온다
            }
            if(jsonObject.has("day") == true){ //TODO JSON 데이터에서 name 이란 key 값이 존재하는 경우
                day = String.valueOf(jsonObject.get("day")); //TODO name 키값 데이터를 가져온다
            }

            Log.d("---","---");
            Log.d("//===========//","================================================");
            Log.d("","\n"+"[A_Main > getDefaultJsonDataParse() 메소드 : 기본 JsonObject 형태 데이터 파싱 실시]]");
            Log.d("","\n"+"[파싱 name : "+String.valueOf(departure)+"]");
            Log.d("","\n"+"[파싱 age : "+String.valueOf(arrival)+"]");
            Log.d("","\n"+"[파싱 man : "+String.valueOf(time)+"]");
            Log.d("","\n"+"[파싱 man : "+String.valueOf(day)+"]");
            Log.d("//===========//","================================================");
            Log.d("---","---");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }*/

    }