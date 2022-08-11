package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.hodu_navigation.R;

public class RouteTime extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_time);

        tv = findViewById(R.id.text_view);
        Button json_button = (Button) findViewById(R.id.jsonparse);
        json_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetManager assetManager = getAssets();

                //assets/ test.json 파일 읽기 위한 InputStream
                try {
                    InputStream is = assetManager.open("test1.json");
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = reader.readLine();
                    }

                    String jsonData = buffer.toString();

                    //json 데이터가 []로 시작하는 배열일때..
                    JSONArray jsonArray = new JSONArray(jsonData);

                    String s = "";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);

                        int duration = jo.getInt("stationCode");
                       // int transferNum = jo.getInt("transferNum");
                        int numStep = jo.getInt("lineId");
                        /*String duration = jo.getString("stationCode");
                        String transferNum = jo.getString("transferNum");
                        String numStep = jo.getString("lineId");*/
                        String stationName = jo.getString("stationName");

                       JSONObject flag = jo.getJSONObject("transfer");
                        //int aa = flag.getInt("isTransfer");
                        Boolean aa = flag.getBoolean("isTransfer");
                        int bb = flag.getInt("transferDistance");
                        int cc = flag.getInt("transferTime");

                        /*String name = jo.getString("name");
                        String msg = jo.getString("msg");
                        JSONObject flag = jo.getJSONObject("flag");
                        int aa = flag.getInt("aa");
                        int bb = flag.getInt("bb");*/

                        s += stationName + " " + duration + " : " + numStep +"==>" + aa + "," + bb +","+ cc +"\n";
                    }
                    tv.setText(s);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}


    //public void clickBtn(View view) {

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_time);

        getJson();
    }

    public void getJson(){

        try {
            InputStream inputStream = getAssets().open("test.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;

            while (true){
                line = reader.readLine();

                Log.d("Json", "line : " + line);

                if(line == null){
                    break;
                }
            }
            reader.close();

        }catch (Exception e){

        }
    }

     */




