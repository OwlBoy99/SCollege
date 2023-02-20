package com.example.scollege.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.scollege.NoticeData;
import com.example.scollege.R;
import com.example.scollege.notice.DeleteNoticeActivity;
import com.example.scollege.notice.NoticeAdaptar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {
    private RecyclerView deleteuserNoticeRecycler;
    private ProgressBar progressbar;
    private ArrayList<UserNoticeData> list;
    private UserNoticeAdaptar adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        deleteuserNoticeRecycler = view.findViewById(R.id.deleteuserNoticeRecycler);
        progressbar = view.findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        deleteuserNoticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        deleteuserNoticeRecycler.setHasFixedSize(true);
        getUserNotice();
        return view;
    }

    private void getUserNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    UserNoticeData data = snapshot1.getValue(UserNoticeData.class);
                    list.add(data);
                }
                adapter = new UserNoticeAdaptar(getContext(),list);
                adapter.notifyDataSetChanged();
                progressbar.setVisibility(View.GONE);
                deleteuserNoticeRecycler.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}