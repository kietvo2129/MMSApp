package com.licheedev.serialtool.Product;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.licheedev.serialtool.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

//import com.example.wms.R;
//import com.example.wms.WO.WorkOrderMaster;

public class LotCompositeMasterAdaptor extends BaseAdapter {
     private Context context;
     private int layout;
     private List<LotCompositeMaster> lotCompositeMasters;

    int bbCodenum;

    public LotCompositeMasterAdaptor(Context context, int layout, List<LotCompositeMaster> lotCompositeMasters) {
        this.context = context;
        this.layout = layout;
        this.lotCompositeMasters = lotCompositeMasters;
    }

    @Override
    public int getCount() {
        return lotCompositeMasters.size();
    }

    @Override
    public Object getItem(int position) {
        return lotCompositeMasters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lotCompositeMasters.indexOf(getItem(position));
    }

    private class ViewHolder{
        TextView MLNO,MTNO,USE;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final LotCompositeMaster lotCompositeMaster = lotCompositeMasters.get(position);
        if( convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout , null);
            holder = new ViewHolder();
            // anh xa view holder
            holder.MLNO = convertView.findViewById(R.id.MLNO);
            holder.MTNO = convertView.findViewById(R.id.MTNO);
            holder.USE = convertView.findViewById(R.id.USE);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.MLNO.setText(lotCompositeMaster.getMt_cd());
        holder.MTNO.setText(lotCompositeMaster.getMt_no());
        holder.USE.setText(lotCompositeMaster.getUse_yn());
        return convertView;
    }
}
