package com.example.scollege.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.scollege.NoticeData;
import com.example.scollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteNoticeActivity extends AppCompatActivity {
    private RecyclerView deleteNoticeRecycler;
    private ProgressBar progressbar;
    private ArrayList<NoticeData> list;
    private NoticeAdaptar adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        deleteNoticeRecycler = findViewById(R.id.deleteNoticeRecycler);
        progressbar = findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecycler.setHasFixedSize(true);
        getNotice();
    }

    private void getNotice() {
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list = new ArrayList<>();
            for (DataSnapshot snapshot1 : snapshot.getChildren()){
                NoticeData data = snapshot1.getValue(NoticeData.class);
                list.add(data);
            }
            adapter = new NoticeAdaptar(DeleteNoticeActivity.this,list);
            adapter.notifyDataSetChanged();
            progressbar.setVisibility(View.GONE);
            deleteNoticeRecycler.setAdapter(adapter);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(DeleteNoticeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }

}