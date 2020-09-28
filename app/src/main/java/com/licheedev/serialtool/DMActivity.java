package com.licheedev.serialtool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.licheedev.serialtool.utils.AidlUtil;
import com.licheedev.serialtool.utils.BluetoothUtil;
import com.licheedev.serialtool.utils.ESCUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DMActivity extends AppCompatActivity{

    TextView btnsignup,tv_urlweb;
    EditText edtuser;
    EditText edtpass;
    CheckBox check;
    SharedPreferences sharedPreferences; // lư thông tin check box
    String user;
    String pass;
    String chuoitrave;
    private long back;
    private Toast backToast;
    public static String ulrweb ="";

    SharedPreferences luu_Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);
        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE);

        luu_Url = getSharedPreferences("dataUrl", MODE_PRIVATE);
        ulrweb = luu_Url.getString("url","http://192.168.1.253:80/");

        Anhxa();
        edtuser.setText(sharedPreferences.getString("TK",""));
        edtpass.setText(sharedPreferences.getString("MK",""));
        check.setChecked(sharedPreferences.getBoolean("checked", false));

        String url = luu_Url.getString("url","http://192.168.1.253:80/");
        tv_urlweb.setText(url);
        tv_urlweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showChangeUrl();

            }
        });


        findViewById(R.id.logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DMActivity.this, ulrweb+"", Toast.LENGTH_SHORT).show();
            }
        });


        //xulydangnhap();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                btnsignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        user = edtuser.getText().toString();
                        pass = edtpass.getText().toString();
                        Log.d("Login",ulrweb+"Product/" + "Login?" + "user=" + user + "&password=" + pass+"&type=" + "MMS");
                        if(user.length()>0&&pass.length()>0){
                            //Toast.makeText(DMActivity.this, "Logging in to the system", Toast.LENGTH_SHORT).show();
                            new docJSON().execute(ulrweb+"Product/" + "Login?" + "user=" + user + "&password=" + pass+"&type=" + "MMS");/// gui du lieu len server
                        }else{
                            Toast.makeText(DMActivity.this, "Complete user and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void showChangeUrl() {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DMActivity.this, android.R.layout.select_dialog_singlechoice);
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(DMActivity.this);
        builderSingle.setTitle("Select One Line:");
        arrayAdapter.add("http://192.168.1.253:80/");
        arrayAdapter.add("http://192.168.1.251:83/");
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                ulrweb = arrayAdapter.getItem(i);
                tv_urlweb.setText(arrayAdapter.getItem(i));
                SharedPreferences.Editor editor = luu_Url.edit();
                editor.putString("url",arrayAdapter.getItem(i));
                editor.commit();
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    class docJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            try {
                //JSONArray mangJSON = new JSONArray(s);
                JSONObject trave = new JSONObject(s);
                chuoitrave = trave.getString("result");
                if(chuoitrave == "true"){
                    if(check.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("TK",user);
                        editor.putString("MK",pass);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    } else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("TK");
                        editor.remove("MK");
                        editor.remove("checked");
                        editor.commit();
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("TK",user);
                    editor.commit();
                    Intent intent = new Intent(DMActivity.this, MainLayout.class);
                    startActivity(intent);
                } else {
                    Canhbaoloi("The User name or Password you entered were invalid.");
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Canhbaoloi(getString(R.string.sever_error));

            }
        }
    }

    @Override
    public void onBackPressed() {
        AppExit();
        //super.onBackPressed();
    }

    public void AppExit()
    {
        //this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
    private void Anhxa() {
        btnsignup   =  findViewById(R.id.btnsignup);
        edtuser     =  findViewById(R.id.edtuser);
        edtpass     =  findViewById(R.id.edtpass);
        check       =  findViewById(R.id.check);
        tv_urlweb =  findViewById(R.id.tv_urlweb);
    }
    private void Canhbaoloi(String text) {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Warning!!!");
        alertDialog.setMessage(text); //"The data you entered does not exist on the server !!!");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }


}
