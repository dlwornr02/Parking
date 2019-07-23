package com.example.dlwor.parking;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsageActivity extends Activity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    public ListView usage_lv;

    public ArrayList<HashMap<String, String>> mUsageList = new ArrayList<HashMap<String, String>>();;

    CommunicationWithServer cws;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_usage);
        usage_lv = (ListView) findViewById(R.id.usage_lv);

        cws = new CommunicationWithServer(this);
        cws.execute("usage",MainActivity.ID);
    }
    public void showList()
    {
        ListAdapter adapter = new SimpleAdapter(
                UsageActivity.this, mUsageList, R.layout.usage_item,
                new String[]{"CheckIn","CheckOut", "Place"},
                new int[]{R.id.c_in_item, R.id.c_out_item, R.id.place_item}
                );
        usage_lv.setAdapter(adapter);
    }
}