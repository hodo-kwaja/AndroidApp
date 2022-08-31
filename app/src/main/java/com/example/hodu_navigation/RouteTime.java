package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.graphics.Color;
import android.graphics.Typeface;

import android.widget.Button;


public class RouteTime extends AppCompatActivity {

    //뒤로가기 버튼 누르면 Input 액티비티로 이동
    @Override public void onBackPressed() {

        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),Input.class));

    }
    //textview 선언
    TextView duration_textview; //소요시간
    TextView numStep_textview;  //경유역
    TextView transferNum_textview;  //환승 횟수
    TextView stationName_textview;  //역 이름
    TextView lineId_textview;  //호선
    TextView hourminute_textview;  //시,분
    TextView scheduleName_textview;  //방면
    TextView countStation_textview;  //-개 역 이동
    TextView congest_textview;  //혼잡도
    TextView TransferTime_textview;  //환승 시간

    LinearLayout listView; // 레이아웃 객체 생성
    Button createTextView; // 버튼 객체 생성

    String duration_t = "";
    String numStep_t = "";
    String transferNum_t = "";
    String stationName_t = "";

    String countStation_t= "";
    String congest_t= "";
    int numStep_ = 0;

    String[] hourminute_t = new String[100];
    String[] transferTime_t = new String[100];
    String[] staitonName_tt = new String[100];
    String[] scheduleName_t = new String[100];
    String[] lineId_t = new String[100];
    String[] congestScore_t = new String[100];
    int[] countf= new int[100];
    int[] countl= new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_time);

        /////////////////////////////////////////////////////
        //최소환승 버튼 클릭시 화면 전환
        TextView min_transfer=(TextView) findViewById(R.id.min_transfer);
        min_transfer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RouteTransfer.class);
                startActivity(intent);
            }
        });

        //혼잡도 버튼 클릭시 화면 전환
        TextView congestion=(TextView) findViewById(R.id.congestion);
        congestion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RouteCongest.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////

        listView = findViewById(R.id. listView);

        //xml 아이디 - textview 지정
        duration_textview = findViewById(R.id.duration);
        numStep_textview = findViewById(R.id.numStep);
        transferNum_textview = findViewById(R.id.transferNum);

        /*hourminute_textview = findViewById(R.id.hourminute);
        stationName_textview = findViewById(R.id.stationName);
        scheduleName_textview = findViewById(R.id.scheduleName);
        countStation_textview = findViewById(R.id.countStation);
        congest_textview = findViewById(R.id.congest);
        TransferTime_textview = findViewById(R.id.TransferTime);
        lineId_textview = findViewById(R.id.lineId);
        */
        AssetManager assetManager = getAssets();

                //assets/ test.json 파일 읽기 위한 InputStream
                try {
                    //InputStream is = assetManager.open("test.json"); //환승 1회
                    InputStream is = assetManager.open("test1.json"); //환승 2회
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = reader.readLine();
                    }


                    String jsonData = buffer.toString();

                    //json 데이터가 ShortestPath 일 경우
                    JSONObject jsonObject = new JSONObject(jsonData);

                    JSONArray jsonArray = jsonObject.getJSONArray("ShortestPath");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //전체 역의 값을 갖고오는 object (전체, all)
                        JSONObject a = jsonArray.getJSONObject(i);
                        //첫번째 역의 값을 갖고오는 object (출발역, first)
                        JSONObject f = jsonArray.getJSONObject(0);
                        //마지막 역의 값을 갖고오는 object (도착역, last)
                        JSONObject l = jsonArray.getJSONObject(jsonArray.length()-1);


                        String stationName_a = a.getString("stationName");
                        String stationName_f = f.getString("stationName");
                        String stationName_l = l.getString("stationName");

                        JSONObject schedule_a = a.getJSONObject("schedule");
                        JSONObject schedule_f = f.getJSONObject("schedule");
                        JSONObject schedule_l = l.getJSONObject("schedule");

                        JSONObject transfer_a = a.getJSONObject("transfer");
                        JSONObject transfer_f = f.getJSONObject("transfer");
                        JSONObject transfer_l = l.getJSONObject("transfer");

                        //전체역 담는 배열
                        staitonName_tt[i] = stationName_a;

                        //전체역 환승 여부 체크
                        Boolean isTransfer = transfer_a.getBoolean("isTransfer");
                        int transferTime = transfer_a.getInt("transferTime");
                        int transferNum = transfer_a.getInt("transferNum");

                        //탑승 시간
                        int hour = schedule_a.getInt("hour");
                        int minute = schedule_a.getInt("minute");

                        //방면
                        String scheduleName= schedule_a.getString("scheduleName");
                        scheduleName_t[i] = scheduleName + "행";
                        hourminute_t[i] = hour +":" + minute;

                        //호선
                        String lineId= a.getString("lineId");
                        lineId_t[i] = lineId;

                        //혼잡도
                        int congestScore = schedule_a.getInt("congestScore");
                        String congestScore_s = Integer.toString(congestScore);
                        congestScore_t[i]= congestScore_s;

                        Log.d("congest", congestScore_s);

                        //환승이 true 인 역의 숫자 저장
                       if(isTransfer == true) {
                           countl[i] = i;
                            if(transferNum == 0 && transferTime != 0||transferNum == 1 && transferTime != 0||transferNum == 2 && transferTime != 0||transferNum == 3 && transferTime != 0||transferNum == 4 && transferTime != 0) {
                                countf[i] = i;
                                if (transferTime < 60)
                                    transferTime_t[i] = transferTime + "초";
                                else {
                                    int minute_t = transferTime / 60;
                                    int second_t = transferTime % 60;

                                    if (second_t == 0) {
                                        transferTime_t[i] = "도보 "+ minute_t + "분";
                                    }
                                    else {
                                        transferTime_t[i] = "도보 "+minute_t + "분" + second_t + "초";
                                    }
                                    Log.d("transfer", transferTime_t[i]);
                                }
                            }
                       }


                        //상단 정보
                        int duration_l = schedule_l.getInt("duration");  //소요시간
                        int numStep_l = schedule_l.getInt("numStep");  //경유역
                        int transferNum_l = transfer_l.getInt("transferNum");  //환승횟수

                        if(transferNum_l==1){
                            numStep_ = numStep_l+2;
                        }
                        else if(transferNum_l==2){
                            numStep_ = numStep_l+3;
                        }
                        else if(transferNum_l==3){
                            numStep_=numStep_l+4;
                        }
                        else if(transferNum_l==4){
                            numStep_=numStep_l+5;
                        }

                        //소요시간(시간,분 으로 변경)
                        if(duration_l<60)
                            duration_t = duration_l + "분";
                        else {
                            int hour_d = duration_l / 60;
                            int minute_d = duration_l % 60;
                            duration_t =  hour_d + "시간" + minute_d +"분";
                        }

                        //경유역 개수
                        numStep_t = "경유역 " + numStep_l + "개";
                        //환승 횟수
                        transferNum_t = "환승" + transferNum_l + "회";

                    }
                    createBigView();

                    /*for(int i=0;i<numStep_;i++) {
                        createSmallView();
                    }*/

                    //화면에 출력
                    //소요시간, 경유역, 환승횟수
                    duration_textview.setText(duration_t);
                    numStep_textview.setText(numStep_t);
                    transferNum_textview.setText(transferNum_t);

                    /*//출발역 시간,분
                    hourminute_textview.setText(hourminute_t);
                    transferNum_textview.setText(lineId_t);*/

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


    }

    @SuppressLint("ResourceType")

    private void createBigView(){

        for(int i=0;i<numStep_;i++) {
            TextView textViewNm = new TextView(getApplicationContext());
            TextView textViewTime= new TextView(getApplicationContext());
            TextView textViewTransfer = new TextView(getApplicationContext());
            TextView textViewscheduleName = new TextView(getApplicationContext());
            TextView textViewlineId = new TextView(getApplicationContext());
            TextView textViewcongestScore  = new TextView(getApplicationContext());
            textViewNm.setText(staitonName_tt[i]);


            if(i==0){
                textViewlineId.setText(lineId_t[i]);
                textViewTime.setText(hourminute_t[i]);
                textViewscheduleName.setText(scheduleName_t[i]);
                textViewlineId.setGravity(Gravity.CENTER);
                textViewlineId.setTypeface(null, Typeface.BOLD);
                textViewcongestScore.setText(congestScore_t[i]);
                listView.addView(textViewlineId);
                listView.addView(textViewTime);
                listView.addView(textViewcongestScore);
                listView.addView(textViewNm);
                listView.addView(textViewscheduleName);

                textViewTime.setTextSize(13);//열차시간 텍스트 크기 지정
                textViewNm.setTextSize(23); //출발역 텍스트 크기 지정

            }
            else if(i==countf[i]){
                textViewTime.setText(hourminute_t[i]);
                textViewTransfer.setText(transferTime_t[i]);
                listView.addView(textViewTime);
                listView.addView(textViewNm);
                listView.addView(textViewTransfer);

                textViewNm.setTextSize(23);//환승역(첫번째) 텍스트 크기 지정
                textViewTime.setTextSize(13);//열차시간 텍스트 크기 지정

            }
            else if(i==countl[i]){
                //환승역(두번째) 텍스트 크기 지정
                textViewlineId.setText(lineId_t[i]);
                textViewTime.setText(hourminute_t[i]);
                textViewscheduleName.setText(scheduleName_t[i]);
                textViewlineId.setGravity(Gravity.CENTER);
                textViewlineId.setTypeface(null, Typeface.BOLD);
                listView.addView(textViewlineId);
                listView.addView(textViewTime);
                listView.addView(textViewNm);
                listView.addView(textViewscheduleName);

                textViewNm.setTextSize(23);
                textViewTime.setTextSize(13);//열차시간 텍스트 크기 지정
            }
            else if(i==numStep_-1){
                //도착역 텍스트 크기 지정
                textViewTime.setText(hourminute_t[i]);
                listView.addView(textViewTime);
                listView.addView(textViewNm);
                textViewTime.setTextSize(13);//열차시간 텍스트 크기 지정
                textViewNm.setTextSize(23);

            }
            else{
                //경유역 텍스트 크기 지정
                textViewcongestScore.setText(congestScore_t[i]);
                listView.addView(textViewcongestScore);
                textViewNm.setTextSize(15);
                listView.addView(textViewNm);
            }

            //4. 텍스트뷰 글자타입 설정
            textViewNm.setTypeface(null, Typeface.BOLD);
            textViewNm.setTextColor(Color.rgb(0, 0, 0));
            textViewcongestScore.setTextSize(10);
            textViewTransfer.setTypeface(null, Typeface.BOLD);
            textViewTransfer.setTextColor(Color.GRAY);

            //5. 텍스트뷰 ID설정
            textViewNm.setId(i);
            textViewTransfer.setId(i);
            textViewTime.setId(i);
            textViewscheduleName.setId(i);
            textViewlineId.setId(i);


            //6. 레이아웃 설정
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param.leftMargin = 320; //출발,도착역
            param.bottomMargin = 100;
            param.topMargin = -65;

            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param1.leftMargin =150; //열차시간
            param1.topMargin = -55;
            param1.bottomMargin= 0;

            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param2.leftMargin = 320; //환승시간
            param2.bottomMargin = 70;
            param2.topMargin = -20;

            LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param3.leftMargin = 40; //호선
            //param3.bottomMargin = 10;
            //param3.topMargin = -60;
            param3.width=80;
            param3.height=80;

            LinearLayout.LayoutParams param4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param4.leftMargin = 320; //방면
            param4.bottomMargin = 90;
            param4.topMargin = -80;

            LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param5.leftMargin = 260; //혼잡도
            //param5.bottomMargin = -30;
            param5.topMargin = -45;
            param5.width=40;
            param5.height=40;




            // 7. 설정한 레이아웃 텍스트뷰에 적용
            textViewNm.setLayoutParams(param);
            textViewTime.setLayoutParams(param1);
            textViewTransfer.setLayoutParams(param2);
            textViewlineId.setLayoutParams(param3);
            textViewscheduleName.setLayoutParams(param4);
            textViewcongestScore.setLayoutParams(param5);

            //8. 텍스트뷰 백그라운드색상 설정
            textViewNm.setBackgroundColor(Color.rgb(255, 255, 255));
            textViewTransfer.setBackgroundColor(Color.rgb(255, 255, 255));
            textViewTime.setBackgroundColor(Color.rgb(255, 255, 255));
            textViewscheduleName.setBackgroundColor(Color.rgb(255, 255, 255));
            textViewlineId.setBackgroundColor(Color.rgb(255, 255, 255));
            //혼잡도
            if(congestScore_t[i].equals("3")) {
                textViewcongestScore.setBackgroundColor(Color.RED);
                textViewcongestScore.setTextColor(Color.RED);
                textViewcongestScore.setBackgroundResource(R.drawable.round_red);
            }
            else if(congestScore_t[i].equals("2")) {
                textViewcongestScore.setBackgroundColor(Color.YELLOW);
                textViewcongestScore.setTextColor(Color.YELLOW);
                textViewcongestScore.setBackgroundResource(R.drawable.round_yellow);
            }
            else if(congestScore_t[i].equals("1")) {
                textViewcongestScore.setBackgroundColor(Color.GREEN);
                textViewcongestScore.setTextColor(Color.GREEN);

                textViewcongestScore.setBackgroundResource(R.drawable.round_green);
            }
            /*else if(congestScore_t[i]==0) {
                textViewcongestScore.setBackgroundColor(Color.YELLOW);
                textViewcongestScore.setTextColor(Color.YELLOW);
            }*/

            textViewlineId.setGravity(Gravity.CENTER);
            textViewlineId.setTypeface(null, Typeface.BOLD);

            if(lineId_t[i].equals("1")) {
                textViewlineId.setTextColor(Color.rgb(255,255,255));
                textViewlineId.setBackgroundColor(Color.rgb(13,54,146));
                textViewlineId.setBackgroundResource(R.drawable.line_1);
            }
            else if(lineId_t[i].equals("2")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(51,162,61));
                textViewlineId.setBackgroundResource(R.drawable.line_2);
            }
            else if(lineId_t[i].equals("3")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(254,91,16));
                textViewlineId.setBackgroundResource(R.drawable.line_3);
            }
            else if(lineId_t[i].equals("4")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(0,162,209));
                textViewlineId.setBackgroundResource(R.drawable.line_4);
            }
            else if(lineId_t[i].equals("5")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(139,80,164));
                textViewlineId.setBackgroundResource(R.drawable.line_5);
            }
            else if(lineId_t[i].equals("6")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(197,92,29));
                textViewlineId.setBackgroundResource(R.drawable.line_6);
            }
            else if(lineId_t[i].equals("7")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(84,100,13));
                textViewlineId.setBackgroundResource(R.drawable.line_7);
            }
            else if(lineId_t[i].equals("8")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(241,46,130));
                textViewlineId.setBackgroundResource(R.drawable.line_8);
            }
            else if(lineId_t[i].equals("9")){
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(170,152,114));
                textViewlineId.setBackgroundResource(R.drawable.line_9);
            }
            else if(lineId_t[i].equals("21")){//인천1호선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#759CCE"));
                textViewlineId.setBackgroundResource(R.drawable.line_21);
            }
            else if(lineId_t[i].equals("22")){//인천2호선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#F5A251"));
                textViewlineId.setBackgroundResource(R.drawable.line_22);
            }
            else if(lineId_t[i].equals("102")){//자기부상철도
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#FFAE43"));
                textViewlineId.setBackgroundResource(R.drawable.line_102);
            }
            else if(lineId_t[i].equals("107")){//용인에버라인
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#51E800"));
                textViewlineId.setBackgroundResource(R.drawable.line_107);
            }
            else if(lineId_t[i].equals("109")){//신분당선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#BA0C2F"));
                textViewlineId.setBackgroundResource(R.drawable.line_109);
            }
            else if(lineId_t[i].equals("110")){//의정부경전철
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#FFA100"));
                textViewlineId.setBackgroundResource(R.drawable.line_110);
            }
            else if(lineId_t[i].equals("113")){//우이신설선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#C7D138"));
                textViewlineId.setBackgroundResource(R.drawable.line_113);
            }
            else if(lineId_t[i].equals("114")){//서해선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#84BD00"));
                textViewlineId.setBackgroundResource(R.drawable.line_114);
            }
            else if(lineId_t[i].equals("115")){//김포골드라인
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.parseColor("#AD8605"));
                textViewlineId.setBackgroundResource(R.drawable.line_115);
            }
            else if(lineId_t[i].equals("104")){ //경의중앙선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(115,199,166));
                textViewlineId.setBackgroundResource(R.drawable.line_104);
            }
            else if(lineId_t[i].equals("116")){//수인분당선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(255,140,0));
                textViewlineId.setBackgroundResource(R.drawable.line_116);
            }
            else if(lineId_t[i].equals("108")){//경춘선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(50,198,166));
                textViewlineId.setBackgroundResource(R.drawable.line_108);
            }
            else if(lineId_t[i].equals("112")){//경강선
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(0,84,166));
                textViewlineId.setBackgroundResource(R.drawable.line_112);
            }
            else if(lineId_t[i].equals("101")){//공항철도
                textViewlineId.setTextColor((Color.rgb(255,255,255)));
                textViewlineId.setBackgroundColor(Color.rgb(54,129,183));
                textViewlineId.setBackgroundResource(R.drawable.line_101);
            }

            //9. 생성및 설정된 텍스트뷰 레이아웃에 적용

        }
    }

    /*private void createSmallView(){

        //1. 텍스트뷰 객체생성
        TextView textViewNm = new TextView(getApplicationContext());

        //2. 텍스트뷰에 들어갈 문자설정
        textViewNm.setText(stationName_t);

        //3. 텍스트뷰 글자크기 설정
        textViewNm.setTextSize(15);//텍스트 크기

        //4. 텍스트뷰 글자타입 설정
        textViewNm.setTypeface(null, Typeface.NORMAL);

        //5. 텍스트뷰 ID설정
        textViewNm.setId(0);

        //6. 레이아웃 설정
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        param.leftMargin = 30;
        param.bottomMargin=12;
        param.topMargin=12;


        // 7. 설정한 레이아웃 텍스트뷰에 적용
        textViewNm.setLayoutParams(param);

        //8. 텍스트뷰 백그라운드색상 설정
        textViewNm.setBackgroundColor(Color.rgb(255,255,255));

        //9. 생성및 설정된 텍스트뷰 레이아웃에 적용
        listView.addView(textViewNm);
    }*/

}




