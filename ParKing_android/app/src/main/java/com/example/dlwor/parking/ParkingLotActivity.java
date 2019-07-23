package com.example.dlwor.parking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dlwor.parking.LoginActivity;
import com.example.dlwor.parking.R;

public class ParkingLotActivity extends Activity {
    public static int layout_id;
    CommunicationWithServer cws;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_id);

        cws = new CommunicationWithServer(this);

        if(layout_id==MainActivity.layout_List.get(0).get(0))
        {
            for(int i = 1 ;i<63;i++) {
                TextView tv = (TextView) findViewById(MainActivity.layout_List.get(0).get(i));
                tv.setText(String.valueOf(i));
                tv.setBackgroundColor(Color.GREEN);
            }
            cws.execute("notify_usinglot","한세대학교,도서관");
        }
        else if(layout_id==MainActivity.layout_List.get(1).get(0))
        {
            for(int i = 1 ;i<21;i++) {
                TextView tv = (TextView) findViewById(MainActivity.layout_List.get(1).get(i));
                tv.setText(String.valueOf(i));
                tv.setBackgroundColor(Color.GREEN);
            }
            cws.execute("notify_usinglot","한세대학교,본관");
        }
    }

    public void showUsinglots(int...using)
    {
        if(layout_id==MainActivity.layout_List.get(0).get(0))
        {
            for(int i : using) {
                TextView tv = (TextView) findViewById(MainActivity.layout_List.get(0).get(i));
                tv.setBackgroundColor(Color.RED);
            }
        }
        else if(layout_id==MainActivity.layout_List.get(1).get(0))
        {
            for(int i : using) {
                TextView tv = (TextView) findViewById(MainActivity.layout_List.get(1).get(i));
                tv.setBackgroundColor(Color.RED);
            }
        }
    }
}
