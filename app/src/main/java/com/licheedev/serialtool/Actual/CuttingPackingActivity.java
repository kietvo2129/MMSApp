package com.licheedev.serialtool.Actual;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.licheedev.serialtool.DMActivity;
import com.licheedev.serialtool.Product.LotProductMappingActivity;
import com.licheedev.serialtool.Product.ProductMaster;
import com.licheedev.serialtool.Product.ProductMasterAdaptor;
import com.licheedev.serialtool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CuttingPackingActivity extends AppCompatActivity {

    ListView lvDanhSanhproduct;

    ArrayList<CuttingPackingMaster> cuttingPackingMasterArrayList;
    TextView tv_lotCode, tv_bobbinCode;

String ulrweb = DMActivity.ulrweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutting_packing);
        setTitle("Cutting Packing");
        lvDanhSanhproduct = findViewById(R.id.lvDanhSanhproduct);
        tv_lotCode = findViewById(R.id.tv_lotCode);
        tv_bobbinCode = findViewById(R.id.tv_bobbinCode);
        tv_lotCode.setText(LotProductMappingActivity.LotCode);

        loadData();



    }

    class getDataCuttingPacking extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return NoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cuttingPackingMasterArrayList = new ArrayList<>();
            try {
                String rowNum, mt_sts_cd1, orgin_mt_cd, reg_id, reg_dt, chg_id, chg_dt,
                        mt_cd, bb_no, remark, gr_qty, wmtid, mt_no, date, lct_cd, mt_type, gr_qty1,
                        mt_nm, mt_cd_mt, unit_cd, gr_qty_mt, del_yn, to_lct_cd, from_lct_cd, type_name,
                        status, location_status, lct_nm;
                JSONArray jsonArray = new JSONArray(s);
                if (jsonArray.length() == 0) {
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    rowNum = jsonObject.getString("RowNum").replace("null", "");
                    mt_sts_cd1 = jsonObject.getString("mt_sts_cd1").replace("null", "");
                    orgin_mt_cd = jsonObject.getString("orgin_mt_cd").replace("null", "");

                    reg_id = jsonObject.getString("reg_id").replace("null", "");
                    reg_dt = jsonObject.getString("reg_dt").replace("null", "");
                    chg_id = jsonObject.getString("chg_id").replace("null", "");
                    chg_dt = jsonObject.getString("chg_dt").replace("null", "");
                    mt_cd = jsonObject.getString("mt_cd").replace("null", "");
                    bb_no = jsonObject.getString("bb_no").replace("null", "");
                    remark = jsonObject.getString("remark").replace("null", "");
                    gr_qty = jsonObject.getString("gr_qty").replace("null", "0");
                    wmtid = jsonObject.getString("wmtid").replace("null", "");
                    mt_no = jsonObject.getString("mt_no").replace("null", "");
                    date = jsonObject.getString("date").replace("null", "");
                    lct_cd = jsonObject.getString("lct_cd").replace("null", "");
                    mt_type = jsonObject.getString("mt_type").replace("null", "");
                    gr_qty1 = jsonObject.getString("gr_qty1").replace("null", "");
                    mt_nm = jsonObject.getString("mt_nm").replace("null", "");
                    mt_cd_mt = jsonObject.getString("mt_cd_mt").replace("null", "");
                    unit_cd = jsonObject.getString("unit_cd").replace("null", "");
                    gr_qty_mt = jsonObject.getString("gr_qty_mt").replace("null", "");
                    del_yn = jsonObject.getString("del_yn").replace("null", "");
                    to_lct_cd = jsonObject.getString("to_lct_cd").replace("null", "");
                    from_lct_cd = jsonObject.getString("from_lct_cd").replace("null", "");
                    type_name = jsonObject.getString("type_name").replace("null", "");
                    status = jsonObject.getString("status").replace("null", "");
                    location_status = jsonObject.getString("location_status").replace("null", "");
                    lct_nm = jsonObject.getString("lct_nm").replace("null", "");

                    cuttingPackingMasterArrayList.add(new CuttingPackingMaster(rowNum, mt_sts_cd1, orgin_mt_cd, reg_id, reg_dt, chg_id, chg_dt, mt_cd, bb_no, remark, gr_qty, wmtid
                            , mt_no, date, lct_cd, mt_type, gr_qty1, mt_nm, mt_cd_mt, unit_cd, gr_qty_mt, del_yn, to_lct_cd
                            , from_lct_cd, type_name, status, location_status, lct_nm, false));


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            setRec();
        }
    }

    public void setRec() {

        CuttingPackingMasterAdaptor cuttingPackingMasterAdaptor;
        cuttingPackingMasterAdaptor = new CuttingPackingMasterAdaptor(
                CuttingPackingActivity.this, R.layout.item_cutting_packing, cuttingPackingMasterArrayList);
        lvDanhSanhproduct.setAdapter(cuttingPackingMasterAdaptor);
    }


    private String NoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cutting_packing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cutting_packing:

                int num = 0;
                for (int i = 0; i < cuttingPackingMasterArrayList.size(); i++) {
                    if (cuttingPackingMasterArrayList.get(i).checked){
                        num+=1;
                    }
                }
                if (num !=0){
                    popup_input_GroupQty();
                }else {

                    Baoloi("Please select at least 1 line",CuttingPackingActivity.this);

                }



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static void Baoloi(String text, Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
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

    private void popup_input_GroupQty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CuttingPackingActivity.this);
        builder.setTitle("Input Group Qty");
        builder.setCancelable(false);
        View viewInflated = LayoutInflater.from(CuttingPackingActivity.this).inflate(R.layout.number_input_layout, null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);

        final int[] numinput = {0};
        String idchon = "";
        int Tongtoida = 0;
        for (int i = 0; i < cuttingPackingMasterArrayList.size(); i++) {
            if (cuttingPackingMasterArrayList.get(i).checked) {
                Tongtoida += Integer.parseInt(cuttingPackingMasterArrayList.get(i).getGr_qty());
                idchon += cuttingPackingMasterArrayList.get(i).getWmtid() + ",";
            }
        }

        int finalTongtoida = Tongtoida;
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (input.getText().toString() == null || input.length() == 0) {
                    numinput[0] = 0;
                } else {
                    numinput[0] = Integer.parseInt(input.getText().toString().trim());

                    if (numinput[0] > finalTongtoida) {
                        input.setError("Maximum");
                        input.setText(finalTongtoida + "");
                    }
                }

            }
        });

        builder.setView(viewInflated);
        String finalIdchon = idchon;
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (numinput[0] == 0) {

                } else {
                    dialog.dismiss();
                    new setDataCuttingPacking().execute(ulrweb+"ActualWO/Gopcacmalaichodu_api?mt_lot=" +
                            LotProductMappingActivity.LotCode +
                            "&soluong=" +
                            numinput[0]+"&all_id="+finalIdchon.substring(0, finalIdchon.length() - 1));
                    Log.d("setDataCuttingPacking", ulrweb+"ActualWO/Gopcacmalaichodu_api?mt_lot=" +
                            LotProductMappingActivity.LotCode +
                            "&soluong=" +
                            numinput[0] + "&all_id=" + finalIdchon.substring(0, finalIdchon.length() - 1));

                }
            }
        });


        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    class setDataCuttingPacking extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return NoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                Toast.makeText(CuttingPackingActivity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                loadData();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(CuttingPackingActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadData() {

        new getDataCuttingPacking().execute(ulrweb+"ActualWO/searchcuttingpacking?style_no=" +
                ActualFragment.Productcode +
                "&_search=false&nd=1598501047805&rows=50&page=1&sidx=&sord=asc");
        Log.d("getDataCuttingPacking", ulrweb+"ActualWO/searchcuttingpacking?style_no=" +
                ActualFragment.Productcode +
                "&_search=false&nd=1598501047805&rows=50&page=1&sidx=&sord=asc");
    }

}