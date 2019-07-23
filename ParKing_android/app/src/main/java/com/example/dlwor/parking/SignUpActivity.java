package com.example.dlwor.parking;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

    CommunicationWithServer cws;
    EditText id_tb;
    EditText pw_tb;
    EditText pwc_tb;
    EditText name_tb;
    EditText email_tb;
    EditText pn_tb;
    boolean overlap_check = false;
    boolean pw_check = false;
    boolean space_check = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id_tb = (EditText) findViewById(R.id.id_tb);
        pw_tb = (EditText) findViewById(R.id.pw_tb);
        pwc_tb = (EditText) findViewById(R.id.pwc_tb);
        name_tb = (EditText) findViewById(R.id.name_tb);
        email_tb = (EditText) findViewById(R.id.email_tb);
        pn_tb = (EditText) findViewById(R.id.pn_tb);

        id_tb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                overlap_check = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClick_confirm_btn(View v)
    {
        if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
            cws.cancel(true);
        }

        pw_check = (pw_tb.getText().toString().equals(pwc_tb.getText().toString()) && !(pw_tb.getText().toString().equals(""))) ? true : false;

        space_check = (!(id_tb.getText().toString().equals(""))&&
                !(pw_tb.getText().toString().equals(""))&&
                !(pwc_tb.getText().toString().equals(""))&&
                !(email_tb.getText().toString().equals(""))&&
                !(name_tb.getText().toString().equals(""))&&
                !(pn_tb.getText().toString().equals(""))) ? true : false;


        if(overlap_check && pw_check && space_check)
        {
            cws = new CommunicationWithServer(this);
            cws.execute("signup",id_tb.getText().toString(),
                    pw_tb.getText().toString(),
                    name_tb.getText().toString(),
                    email_tb.getText().toString(),
                    pn_tb.getText().toString());/////////////////////////////////////////////////////
        }
        else if(space_check==false)
        {
            Toast.makeText(getApplicationContext(),"빈칸을 모두 기입해주세요.", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(overlap_check==false)
            {
                Toast.makeText(getApplicationContext(),"ID 중복검사를 해주세요.", Toast.LENGTH_LONG).show();
            }
            else if(overlap_check==true && pw_check==false)
            {
                Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClick_cancel_btn(View v)
    {
        if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
            cws.cancel(true);
        }
        finish();
    }

    public void onClick_overlapcheck_btn(View v)
    {

        if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
            cws.cancel(true);
        }
        if(!(id_tb.getText().toString().equals(""))) {
            cws = new CommunicationWithServer(this);
            cws.execute("overlapCheck", id_tb.getText().toString());
        }
        else
        {
            Toast.makeText(getApplicationContext(),"ID를 입력해주세요.", Toast.LENGTH_LONG).show();
        }
    }
    public void result(int r)
    {
        if(cws!=null && cws.getStatus()== AsyncTask.Status.RUNNING) {
            cws.cancel(true);
        }
        if(r==0) //ID중복됨
        {
            Toast.makeText(getApplicationContext(),"중복된 ID입니다.", Toast.LENGTH_LONG).show();
            overlap_check = false;
        }
        else if(r==1) //ID사용가능
        {
            Toast.makeText(getApplicationContext(),"사용가능한 ID입니다.", Toast.LENGTH_LONG).show();
            overlap_check = true;
        }else if(r==2) //회원가입 실패
        {
            Toast.makeText(getApplicationContext(),"다시 시도해 주세요.", Toast.LENGTH_LONG).show();
        }
        else if(r==3) //회원가입 성공
        {
            Toast.makeText(getApplicationContext(),"회원가입 성공! 로그인해주세요.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
    @Override
    public void onBackPressed() {
    }
}
