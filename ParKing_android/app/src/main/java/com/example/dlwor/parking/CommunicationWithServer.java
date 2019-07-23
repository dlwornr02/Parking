package com.example.dlwor.parking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommunicationWithServer extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    String errorString = null;
    Activity currentActivity;
    ArrayList<HashMap<String, String>> mArrayList;

    ListView usage_lv;

    private static String TAG = "phpquerytest";


    String mJsonString;


    CommunicationWithServer(Activity a) {
        currentActivity = a;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(currentActivity,
                "Please Wait", null, true, true);
    }

    @Override
    protected String doInBackground(String... params) {
        String kindOftask = params[0];
        String serverURL = "http://taekhoon.tk:12122/main.php";
        String postParameters = "task=" + kindOftask; // + "&address=" + a; //ex)
        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();

            if (kindOftask.equals("LoginCheck")) {

                postParameters+="&id=" + params[1] + "&pw=" + params[2];

            } else if (kindOftask.equals("overlapCheck")) {

                postParameters+="&id=" + params[1];

            } else if (kindOftask.equals("signup")) {

                postParameters+="&id=" + params[1] + "&pw=" + params[2]+ "&name=" + params[3] + "&email=" + params[4] + "&pn=" + params[5];

            }else if (kindOftask.equals("usage")) {
                postParameters+="&id=" + params[1];
            }else if (kindOftask.equals("notify_usinglot")) {
                postParameters+="&place=" + params[1];
            }else if (kindOftask.equals("simple_parking")) {
                postParameters+="&id=" + params[1];
            }else if (kindOftask.equals("check_in_able")) {
                postParameters+="&minor=" + params[1];
            }else if (kindOftask.equals("check_in_doing")) {
                postParameters+="&minor=" + params[1]+"&id=" + params[2];
            }else if (kindOftask.equals("check_out_doing")) {
                postParameters+="&id=" + params[1];
            }


            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();

            Log.d(TAG, "response code - " + responseStatusCode);

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {

                inputStream = httpURLConnection.getErrorStream();

            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            return sb.toString().trim();
        } catch (Exception e) {
            Log.d(TAG, "InsertData: Error ", e);
            errorString = e.toString();
            return null;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressDialog.dismiss();
        //mTextViewResult.setText(result);

        Log.d(TAG, "response - " + result);
        if (result.equals("")) {
            //mTextViewResult.setText(errorString);
        } else {
            mJsonString = result;
            if (result.contains("login_OK")) {
                ((LoginActivity) currentActivity).permissionCheck(true);
            } else if (result.contains("login_Cancel")) {
                ((LoginActivity) currentActivity).permissionCheck(false);
            }else if (result.contains("simple_parking_OK")) {
                ((MainActivity) currentActivity).simple_parking_OK("OK");
            } else if (result.contains("simple_parking_Cancel")) {
                String[] s = result.split("#");
                ((MainActivity) currentActivity).simple_parking_Cancel(s[1]  + "의 " + s[2]+"번째 자리 사용중이십니다.\n"+ "checkIn시간 : " + s[3]);
            }else if (result.contains("check_in_able_true")) {
                ((RecoRangingActivity) currentActivity).check_in_able(result);
            } else if (result.contains("check_in_able_false")) {
                ((RecoRangingActivity) currentActivity).check_in_able(result);
            }else if (result.contains("check_in_able_cancel")) {
                ((RecoRangingActivity) currentActivity).check_in_able(result);
            }else if (result.contains("check_in_doing_success")) {
                ((RecoRangingActivity) currentActivity).update_status(result);
            }else if (result.contains("check_out_doing_success")) {
                ((RecoRangingActivity) currentActivity).update_status(result);
            }
            else if (result.contains("overlapCheck_Cancel")) {
                ((SignUpActivity) currentActivity).result(0);
            } else if (result.contains("overlapCheck_OK")) {
                ((SignUpActivity) currentActivity).result(1);
            } else if (result.contains("signup_Cancel")) {
                ((SignUpActivity) currentActivity).result(2);
            } else if (result.contains("signup_OK")) {
                ((SignUpActivity) currentActivity).result(3);
            } else if (result.contains("에러")) {
                Toast.makeText(currentActivity.getApplicationContext(),"서버에러 다시시도해주세요ㅠ", Toast.LENGTH_LONG).show();
            } else if (result.contains("usagelist")) {
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("usagelist");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);

                        String id = item.getString("CheckIn");
                        String name = item.getString("CheckOut");
                        String address = item.getString("Place");
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("CheckIn", id);
                        hashMap.put("CheckOut", name);
                        hashMap.put("Place", address);

                        ((UsageActivity)currentActivity).mUsageList.add(hashMap);
                    }
                    ((UsageActivity)currentActivity).showList();
                } catch (JSONException e) {
                    Log.d(TAG, "showResult : ", e);
                }
            }else if (result.contains("usinglotIs")) {
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("usinglotIs");

                    int [] usinglots = new int[jsonArray.length()];

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);

                        int no = item.getInt("no");
                        usinglots[i] = no;
                    }
                    ((ParkingLotActivity)currentActivity).showUsinglots(usinglots);
                } catch (JSONException e) {
                    Log.d(TAG, "showResult : ", e);
                }
            }
        }
    }
}