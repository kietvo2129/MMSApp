package com.licheedev.serialtool.QC_check;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.licheedev.serialtool.R;

import java.util.ArrayList;


public class QcDetailAdapter extends RecyclerView.Adapter<QcDetailAdapter.QcDetailViewHolder> {
    private ArrayList<QcDetailItem> items;

    public QcDetailAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemChecker(int position);
        void onItemEditText(int position);
    }

    public void setOnItemClickListener(QcDetailAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public QcDetailAdapter(ArrayList<QcDetailItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public QcDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_qc_check_qc_detail, parent, false);
        QcDetailViewHolder evh = new QcDetailViewHolder(v, listener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull QcDetailViewHolder holder, int position) {
        QcDetailItem currentItem = items.get(position);

        holder.cbDetail.setChecked(currentItem.isStCheck());
        holder.sub.setText(currentItem.getSub());
        holder.check.setText(currentItem.getCheck());
        holder.qty.setText(currentItem.getQty());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class QcDetailViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbDetail;
        private TextView sub;
        private TextView check;
        private TextView qty;

        public QcDetailViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            
            cbDetail = v.findViewById(R.id.td_cbCheck);
            sub     = v.findViewById(R.id.td_sub);
            check   = v.findViewById(R.id.td_check);
            qty     = v.findViewById(R.id.td_qty);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            cbDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemChecker(position);
                        }
                    }
                }
            });

            qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemEditText(position);
                        }
                    }
                }
            });
        }
    }
}
