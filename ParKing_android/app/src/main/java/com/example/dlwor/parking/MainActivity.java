package com.example.dlwor.parking;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static ArrayList<ArrayList<Integer>> layout_List = new ArrayList<ArrayList<Integer>>();


    public static final String RECO_REG_TIME = "registration_reco_time";
    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";
    public static final boolean SCAN_RECO_ONLY = true;
    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;
    public static final boolean DISCONTINUOUS_SCAN = false;

    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    public static final String APP_NBAME = "RecoBeaCon";
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUSET_LOGIN = 2;
    private static final int REQUEST_LOCATION = 10;


    public static String ID = "";
    CommunicationWithServer cws;

    public TextView info_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_List.add(new ArrayList<Integer>());
        layout_List.add(new ArrayList<Integer>());

        layout_List.get(0).add(R.layout.activity_parking_lot_hansei_libary);
        layout_List.get(1).add(R.layout.activity_parking_lot_hansei_mainbuilding);

        for(int i = 1 ;i<63;i++) {
            MainActivity.layout_List.get(0).add(getResources().getIdentifier("hansei_library_" + i, "id", getApplicationContext().getPackageName().toString()));
        }
        for(int i = 1 ;i<21;i++) {
            MainActivity.layout_List.get(1).add(getResources().getIdentifier("hansei_mainbuilding_" + i, "id", getApplicationContext().getPackageName().toString()));
        }


        info_tv = (TextView) findViewById(R.id.info_tv);
        info_tv.setText("간편주차는 주차장 내 빈 공간 앞에서 클릭해주세요.\n\n" + "주차예약은 선결제시스템 입니다.");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, REQUSET_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==REQUSET_LOGIN && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "정상 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            if(mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
            }
        } else if (requestCode==REQUSET_LOGIN && resultCode == RESULT_CANCELED) {
            finish();
        }

        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_CANCELED) {
            //사용자가 블루투스 요청을 허용하지 않았을 경우, 어플리케이션은 종료됩니다.
            finish();
            return;
        }
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 꺼집니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void simple_parking_Btn_Click(View v) {
        cws = new CommunicationWithServer(this);
        cws.execute("simple_parking",ID);
    }


    public void simple_parking_OK(String s)
    {
        RecoRangingActivity.parking_useable = true;
        Intent intent = new Intent(this,RecoRangingActivity.class);
        startActivity(intent);
    }
    public void simple_parking_Cancel(String s)
    {
        RecoRangingActivity.parking_useable = false;
        RecoRangingActivity.status = s;
        Intent intent = new Intent(this,RecoRangingActivity.class);
        startActivity(intent);
    }



    public void reserv_parking_btn_Click(View v) {
        choice_ParkingLot();
    }
    public void search_empty_btn_Click(View v) {
        choice_ParkingLot();
    }
    public void usage_details_btn_Click(View v) {
        Intent intent = new Intent(this,UsageActivity.class);
        startActivity(intent);
    }

    public void logout_Click(View v) {
        SimpleDialog("로그아웃 하시겠습니까?");
    }

    private void choice_ParkingLot()
    {
        final Intent intent = new Intent(this,ParkingLotActivity.class);
        final CharSequence[] items = {"한세대,도서관","한세대,본관"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("주차장을 선택하세요")
                .setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        ParkingLotActivity.layout_id = layout_List.get(index).get(0);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

    }
    private void SimpleDialog(String message) {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(message).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //decision = true;
                        ID = "";
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivityForResult(intent, REQUSET_LOGIN);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        alert.setTitle("로그아웃");
        // Icon for AlertDialog
        alert.setIcon(R.mipmap.icon);
        alert.show();
    }

}
