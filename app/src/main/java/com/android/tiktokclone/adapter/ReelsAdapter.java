package com.android.tiktokclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.tiktokclone.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ReelsAdapter extends RecyclerView.Adapter<ReelsAdapter.ReelsNameHolder> {
    ArrayList<String> reelsArrayList;
    Context context;

    public ReelsAdapter(ArrayList<String> itemsArrayList, Context context) {
        this.reelsArrayList = itemsArrayList;
        this.context = context;
    }

    public ArrayList<String> getReelsArrayList() {
        return reelsArrayList;
    }

    public void setReelsArrayList(ArrayList<String> reelsArrayList) {
        this.reelsArrayList = reelsArrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReelsNameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reels_single_item, parent, false);
        ReelsNameHolder reelsNameHolder = new ReelsNameHolder(v);
        return reelsNameHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReelsNameHolder holder, int position) {
        final String reelsModel = reelsArrayList.get(position);
        holder.textTV.setText(reelsModel);

    }

    @Override
    public int getItemCount() {
        return reelsArrayList.size();
    }

    public class ReelsNameHolder extends RecyclerView.ViewHolder {

        TextView textTV;

        public ReelsNameHolder(@NonNull View itemView) {
            super(itemView);
            textTV = itemView.findViewById(R.id.textTV);
        }
    }
}
