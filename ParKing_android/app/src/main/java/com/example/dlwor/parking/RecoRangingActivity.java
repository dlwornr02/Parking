/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Perples, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.dlwor.parking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
public class RecoRangingActivity extends RecoActivity implements RECORangingListener {

    private ListView mRegionListView;
    private ArrayList<RECOBeacon> mRangedBeacons;
    TextView tv_status;
    Button c_out_btn;
    Button c_in_btn;

    public static boolean parking_useable=true;
    public static String status;
    CommunicationWithServer cws;

    RECOBeacon most_Proximity_beacon;

    boolean first=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco_ranging);

        tv_status = (TextView)findViewById(R.id.status_tv);
        c_out_btn = (Button)findViewById(R.id.check_out_btn);
        c_in_btn = (Button)findViewById(R.id.check_in_btn);

        c_out_btn.setVisibility(View.GONE);
        c_in_btn.setVisibility(View.GONE);

        c_in_btn.setClickable(false);


        if(parking_useable) {
            mRecoManager.setRangingListener(this);
            mRecoManager.bind(this);
        }
        else
        {
            tv_status.setText(MainActivity.ID + "님은 " + status);
            c_in_btn.setVisibility(View.GONE);
            c_out_btn.setVisibility(View.VISIBLE);
        }
    }

    public void onClick_check_in_btn(View v)
    {
        cws = new CommunicationWithServer(this);
        cws.execute("check_in_doing",String.valueOf(most_Proximity_beacon.getMinor()),MainActivity.ID);
    }

    public void onClick_check_out_btn(View v)
    {
        cws = new CommunicationWithServer(this);
        cws.execute("check_out_doing",MainActivity.ID);
    }
    public void update_status(String s)
    {
        if(s.contains("in_doing_success")) {
            this.stop(mRegions);
            String[] sb = s.split("#");
            tv_status.setText( sb[1] +"의 " + sb[2] +"번 자리\n"+ "체크인 하셧습니다.");
            c_out_btn.setVisibility(View.VISIBLE);
            c_in_btn.setVisibility(View.GONE);
        } else if (s.contains("out_doing_success")) {
            tv_status.setText("CheckOut하셧습니다. 이용해주셔서 감사합니다.");
            c_out_btn.setVisibility(View.GONE);
            c_in_btn.setVisibility(View.GONE);
        }
    }
    public void check_in_able(String s)
    {
        if(s.contains("cancel")) {
            tv_status.setText("주차장내로 이동해주세요.");
        } else if (s.contains("able_true")) {
            String[] sb = s.split("#");
            tv_status.setText("가장 가까운 주차공간 : \n" + sb[1] + "의 " + sb[2] + "번 자리입니다. (사용가능)");
            c_in_btn.setVisibility(View.VISIBLE);
            c_in_btn.setClickable(true);
        }else if (s.contains("able_false")) {
            String[] sb = s.split("#");
            tv_status.setText("가장 가까운 주차공간 : \n" + sb[1] + "의 " + sb[2] + "번 자리입니다. (사용중)");
            c_in_btn.setVisibility(View.GONE);
            c_in_btn.setClickable(false);
        }

    }
    public void choice_ParkingLot(View v)
    {
        final Intent intent = new Intent(this,ParkingLotActivity.class);
        final CharSequence[] items = {"한세대,도서관","한세대,본관"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("주차장을 선택하세요")
                .setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        ParkingLotActivity.layout_id = MainActivity.layout_List.get(index).get(0);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

    }
    @Override
    protected void onResume() {
        super.onResume();

        //mRangingListAdapter = new RecoRangingListAdapter(this);
        //mRegionListView.setAdapter(mRangingListAdapter);

        mRegionListView = (ListView)findViewById(R.id.list_ranging);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stop(mRegions);
        this.unbind();
    }

    private void unbind() {
        try {
            mRecoManager.unbind();
        } catch (RemoteException e) {
            Log.i("RECORangingActivity", "Remote Exception");
            e.printStackTrace();
        }
    }


    @Override
    public void onServiceConnect() {
        Log.i("RECORangingActivity", "onServiceConnect()");
        mRecoManager.setDiscontinuousScan(MainActivity.DISCONTINUOUS_SCAN);
        this.start(mRegions);
        //Write the code when RECOBeaconManager is bound to RECOBeaconService
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<RECOBeacon> recoBeacons, RECOBeaconRegion recoRegion) {
        Log.i("RECORangingActivity", "didRangeBeaconsInRegion() region: " + recoRegion.getUniqueIdentifier() + ", number of beacons ranged: " + recoBeacons.size());

        mRangedBeacons = new ArrayList<RECOBeacon>(recoBeacons);

        if(!recoBeacons.isEmpty())
        {
            if(most_Proximity_beacon==null) {
                most_Proximity_beacon = mRangedBeacons.get(0);
            }
            int tempMinor = most_Proximity_beacon.getMinor();

            for (RECOBeacon rb : recoBeacons) {
                if (most_Proximity_beacon.getAccuracy() > rb.getAccuracy()) {
                    most_Proximity_beacon = rb;
                }
            }
            if(tempMinor!=most_Proximity_beacon.getMinor() || first==true) {
                cws = new CommunicationWithServer(this);
                cws.execute("check_in_able", String.valueOf(most_Proximity_beacon.getMinor()));
                if(first==true){first = false;}
            }
        }

//        mRangingListAdapter.updateAllBeacons(recoBeacons);
//        mRangingListAdapter.notifyDataSetChanged();
        //Write the code when the beacons in the region is received
    }

    @Override
    protected void start(ArrayList<RECOBeaconRegion> regions) {

        /**
         * There is a known android bug that some android devices scan BLE devices only once. (link: http://code.google.com/p/android/issues/detail?id=65863)
         * To resolve the bug in our SDK, you can use setDiscontinuousScan() method of the RECOBeaconManager.
         * This method is to set whether the device scans BLE devices continuously or discontinuously.
         * The default is set as FALSE. Please set TRUE only for specific devices.
         *
         * mRecoManager.setDiscontinuousScan(true);
         */

        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.startRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stop(ArrayList<RECOBeaconRegion> regions) {
        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceFail(RECOErrorCode errorCode) {
        return;
    }

    @Override
    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion region, RECOErrorCode errorCode) {
        Log.i("RECORangingActivity", "error code = " + errorCode);
        return;
    }
}
