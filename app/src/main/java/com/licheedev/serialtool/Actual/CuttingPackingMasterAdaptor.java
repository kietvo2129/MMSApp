package com.licheedev.serialtool.Actual;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.licheedev.serialtool.R;

import java.util.Date;
import java.util.List;

//import com.example.wms.R;
//import com.example.wms.WO.WorkOrderMaster;

public class CuttingPackingMasterAdaptor extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CuttingPackingMaster> CuttingPackingMasters;


    public CuttingPackingMasterAdaptor(Context context, int layout, List<CuttingPackingMaster> CuttingPackingMasters) {
        this.context = context;
        this.layout = layout;
        this.CuttingPackingMasters = CuttingPackingMasters;
    }

    @Override
    public int getCount() {
        return CuttingPackingMasters.size();
    }

    @Override
    public Object getItem(int position) {
        return CuttingPackingMasters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return CuttingPackingMasters.indexOf(getItem(position));
    }

    private class ViewHolder {
        TextView No, MTCd, MLNo,GroupQty,MTNo,Type,Status,Location,LocationStatus,Departure
        ,Destination,BobbinNo,Description,MTName,OriginMLNo,CreateName,CreateDate,ChangeName,ChangeDate;
        CheckBox checkbox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final CuttingPackingMaster CuttingPackingMaster = CuttingPackingMasters.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            // anh xa view holder
            holder.No = convertView.findViewById(R.id.No);
            holder.MTCd = convertView.findViewById(R.id.MTCd);
            holder.MLNo = convertView.findViewById(R.id.MLNo);
            holder.GroupQty = convertView.findViewById(R.id.GroupQty);
            holder.MTNo = convertView.findViewById(R.id.MTNo);
            holder.Type = convertView.findViewById(R.id.Type);
            holder.Status = convertView.findViewById(R.id.Status);
            holder.Location = convertView.findViewById(R.id.Location);
            holder.LocationStatus = convertView.findViewById(R.id.LocationStatus);
            holder.Departure = convertView.findViewById(R.id.Departure);
            holder.Destination = convertView.findViewById(R.id.Destination);
            holder.BobbinNo = convertView.findViewById(R.id.BobbinNo);
            holder.Description = convertView.findViewById(R.id.Description);
            holder.MTName = convertView.findViewById(R.id.MTName);
            holder.OriginMLNo = convertView.findViewById(R.id.OriginMLNo);
            holder.CreateName = convertView.findViewById(R.id.CreateName);
            holder.CreateDate = convertView.findViewById(R.id.CreateDate);
            holder.ChangeName = convertView.findViewById(R.id.ChangeName);
            holder.ChangeDate = convertView.findViewById(R.id.ChangeDate);
            holder.checkbox = convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.checkbox.setChecked(CuttingPackingMaster.checked);

        holder.No.setText(CuttingPackingMaster.getRowNum());
        holder.MTCd.setText(CuttingPackingMaster.getMt_cd_mt());
        holder.MLNo.setText(CuttingPackingMaster.getMt_cd());
        holder.GroupQty.setText(CuttingPackingMaster.getGr_qty());
        holder.MTNo.setText(CuttingPackingMaster.getMt_no());
        holder.Type.setText(CuttingPackingMaster.getMt_type());
        holder.Status.setText(CuttingPackingMaster.getStatus());
        holder.Location.setText(CuttingPackingMaster.getLct_nm());
        holder.LocationStatus.setText(CuttingPackingMaster.getLocation_status());
        holder.Departure.setText(CuttingPackingMaster.getFrom_lct_cd());
        holder.Destination.setText(CuttingPackingMaster.getTo_lct_cd());
        holder.BobbinNo.setText(CuttingPackingMaster.getBb_no());
        holder.Description.setText(CuttingPackingMaster.getRemark());
        holder.MTName.setText(CuttingPackingMaster.getMt_nm());
        holder.OriginMLNo.setText(CuttingPackingMaster.getOrgin_mt_cd());



        holder.CreateName.setText(CuttingPackingMaster.getReg_id());
        holder.CreateDate.setText(partDate(CuttingPackingMaster.getReg_dt()));
        holder.ChangeName.setText(CuttingPackingMaster.getChg_id());
        holder.ChangeDate.setText(partDate(CuttingPackingMaster.getChg_dt()));
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !CuttingPackingMaster.isChecked();
                CuttingPackingMaster.checked = newState;
            }
        });

        return convertView;
    }

    private String partDate(String val) {

        String longV = val.replace("/Date(","").replace(")/","");
        long millisecond = Long.parseLong(longV);
        String dateString = DateFormat.format("yyyy-MM-dd", new Date(millisecond)).toString();
        return dateString;
    }


}
