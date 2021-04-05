package com.pci.navratnaattendace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pci.navratnaattendace.R;
import com.pci.navratnaattendace.db.Shg;
import java.util.List;

public class ShgDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shg> mDataset;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public ShgDetailAdapter(Context context, List<Shg> shgList, OnItemClickListener onItemClickListener, OnItemLongClickListener onItemLongClickListener) {
        this.mContext = context;
        this.mDataset = shgList;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private TextView serial, shgName, shgReg, shgTotal;
        private OnItemClickListener onItemClickListener;
        private OnItemLongClickListener onItemLongClickListener;
        private Shg shg;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onRowClick, OnItemLongClickListener onItemLongClick) {
            super(itemView);
            this.onItemClickListener = onRowClick;
            this.onItemLongClickListener = onItemLongClick;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            serial = itemView.findViewById(R.id.itemOne);
            shgName = itemView.findViewById(R.id.itemTwo);
            shgReg = itemView.findViewById(R.id.itemThree);
            shgTotal = itemView.findViewById(R.id.itemFour);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClicked(shg);
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClicked(shg,getAdapterPosition());
            return true;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_detail_item, parent, false);
        return new ViewHolder(view, onItemClickListener,onItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mDataset != null) {
            viewHolder.shg = mDataset.get(position);
            viewHolder.serial.setText(String.valueOf(position+1));
            viewHolder.shgName.setText(mDataset.get(position).getShgName());
            viewHolder.shgTotal.setText(String.valueOf(mDataset.get(position).getShgTMember()));
        } else {
            Toast.makeText(mContext, "Dataset is null", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        public void onItemClicked(Shg shg);
    }
    public interface OnItemLongClickListener {
        public void onItemLongClicked(Shg shg, int position);
    }
}
