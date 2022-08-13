package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RouteTime extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_time);

        //xml 아이디 - textview 지정
        duration_textview = findViewById(R.id.duration);
        numStep_textview = findViewById(R.id.numStep);
        transferNum_textview = findViewById(R.id.transferNum);
        stationName_textview = findViewById(R.id.stationName);
        hourminute_textview = findViewById(R.id.hourminute);
        scheduleName_textview = findViewById(R.id.scheduleName);
        countStation_textview = findViewById(R.id.countStation);
        congest_textview = findViewById(R.id.congest);
        TransferTime_textview = findViewById(R.id.TransferTime);
        lineId_textview = findViewById(R.id.lineId);

        AssetManager assetManager = getAssets();

                //assets/ test.json 파일 읽기 위한 InputStream
                try {
                    InputStream is = assetManager.open("test.json");
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

                    String duration_t = "";
                    String numStep_t = "";
                    String transferNum_t = "";
                    String stationName_t = "";
                    String hourminute_t= "";
                    String scheduleName_t= "";
                    String countStation_t= "";
                    String congest_t= "";
                    String TransferTime_t= "";
                    int lineId_t = 0;

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jo = jsonArray.getJSONObject(i);

                        //마지막 역의 값을 갖고오는 object (도착역)
                        JSONObject jf = jsonArray.getJSONObject(0);
                        //첫번째 역의 값을 갖고오는 object (출발역)
                        JSONObject jl = jsonArray.getJSONObject(jsonArray.length()-1);

                        //string 예시
                        //String stationName = jo.getString("stationName");

                        JSONObject transfer_jo = jo.getJSONObject("transfer");

                        Boolean isTransfer = jo.getBoolean("isTransfer");
                        if(isTransfer == true){

                        }
                        //출발역 정보

                        int lindId = jl.getInt("lineId"); //호선
                        String stationName = jl.getString("stationName"); //출발역 이름

                        JSONObject schedule_jl = jl.getJSONObject("schedule");
                        int hour = schedule_jl.getInt("hour");  //출발역 시
                        int minute = schedule_jl.getInt("minute"); //출발역 분
                        String scheduleName = schedule_jl.getString("scheduleName"); //방면

                        hourminute_t = hour +":"+minute;
                        lineId_t = lindId;

                        //상단 정보
                        JSONObject schedule_jf = jf.getJSONObject("schedule");
                        int duration = schedule_jf.getInt("duration");  //소요시간
                        int numStep = schedule_jf.getInt("numStep");  //경유역
                        int transferNum = schedule_jf.getInt("transferNum");  //환승횟수

                        //소요시간
                        if(duration<60)
                            duration_t = duration + "분";
                        else {
                            int hour_d = duration / 60;
                            int minute_d = duration % 60;
                            duration_t =  hour_d + "시간" + minute_d +"분";
                        }

                        //경유역 개수
                        numStep_t = "경유역 " + numStep + "개";
                        //환승 횟수
                        transferNum_t = "환승" + transferNum + "회";

                    }
                    //화면에 출력
                    //소요시간, 경유역, 환승횟수
                    duration_textview.setText(duration_t);
                    numStep_textview.setText(numStep_t);
                    transferNum_textview.setText(transferNum_t);

                    //출발역 시간,분
                    hourminute_textview.setText(hourminute_t);
                    transferNum_textview.setText(lineId_t);

                    //출발역 정보

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

    }
}




