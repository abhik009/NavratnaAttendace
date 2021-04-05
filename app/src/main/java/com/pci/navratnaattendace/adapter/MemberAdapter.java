package com.pci.navratnaattendace.adapter;

import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pci.navratnaattendace.R;
import com.pci.navratnaattendace.db.Member;
import java.util.List;
import es.dmoral.toasty.Toasty;

public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Member> mDataset;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public MemberAdapter(Context ctx, List<Member> memberList, OnItemClickListener onItemClickListener,OnItemLongClickListener onItemLongClickListener) {
        this.context = ctx;
        this.mDataset = memberList;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private TextView serial, memName, memVillage, memMobile;
        private OnItemClickListener onItemClickListener;
        private OnItemLongClickListener onItemLongClickListener;
        private Member member;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onRowClick, OnItemLongClickListener onRowLongClick) {
            super(itemView);
            this.onItemClickListener = onRowClick;
            this.onItemLongClickListener = onRowLongClick;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            serial = itemView.findViewById(R.id.itemOne);
            memName = itemView.findViewById(R.id.itemTwo);
            memVillage = itemView.findViewById(R.id.itemThree);
            memMobile = itemView.findViewById(R.id.itemFour);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClicked(member,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClicked(member,getAdapterPosition());
            return true;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_member_item, parent, false);
        return new ViewHolder(view, onItemClickListener,onItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mDataset != null) {
            viewHolder.member = mDataset.get(position);
            viewHolder.serial.setText(String.valueOf(position+1));
            viewHolder.memName.setText(mDataset.get(position).getMemName());
            viewHolder.memVillage.setText(mDataset.get(position).getMemVillage());
            viewHolder.memMobile.setText(String.valueOf(mDataset.get(position).getMemMobile()));
        } else {
            Toasty.error(context, "Dataset is null", Toasty.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(Member member, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClicked(Member member, int position);
    }
}
