package com.example.dlwor.parking;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    EditText id_et;
    EditText pw_et;
    CommunicationWithServer cws;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_et = (EditText) findViewById(R.id.editID);
        pw_et = (EditText) findViewById(R.id.editPSW);
    }

    public void onClick_signup_btn(View v)
    {
        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            setResult(RESULT_CANCELED);
            if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
                cws.cancel(true);
            }
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기=꺼짐", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick_login_btn(View v)
    {
        //서버랑 연동해서 ID/PW비교후

        if(id_et.getText().toString().equals("") || pw_et.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "입력된 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
                cws.cancel(true);
            }

            cws = new CommunicationWithServer(this);
            cws.execute("LoginCheck",id_et.getText().toString(),pw_et.getText().toString());
        }
    }
    public void permissionCheck(boolean permission)
    {
        if(permission)
        {
            setResult(RESULT_OK);
            if(cws.getStatus()== AsyncTask.Status.RUNNING) {
                cws.cancel(true);
            }
            MainActivity.ID = id_et.getText().toString().trim();

            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "입력된 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
            if(cws.getStatus()== AsyncTask.Status.RUNNING) {
                cws.cancel(true);
            }
        }
    }
}
