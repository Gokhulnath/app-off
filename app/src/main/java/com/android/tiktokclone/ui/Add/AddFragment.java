package com.android.tiktokclone.ui.Add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tiktokclone.R;
import com.android.tiktokclone.adapter.ReelsAdapter;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    private AddViewModel addViewModel;
    RecyclerView reelsRV;
    ReelsAdapter reelsAdapter;
    ArrayList<String> reels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        reelsRV = root.findViewById(R.id.reelsRV);
        reels = new ArrayList<String >();
        reelsAdapter = new ReelsAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        reelsRV.setLayoutManager(linearLayoutManager);
        reelsRV.setAdapter(reelsAdapter);
        reels.add("Reels 1");
        reels.add("Reels 2");
        reels.add("Reels 3");
        reels.add("Reels 4");

        reelsAdapter.setReelsArrayList(reels);
        reelsAdapter.notifyDataSetChanged();

        return root;
    }
}