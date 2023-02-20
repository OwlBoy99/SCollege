package com.example.scollege.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.scollege.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView itDepartment,csDepartment,pDepartment,zDepartment,cDepartment;
    private LinearLayout itNoData,csNoData,pNoData,zNoData,cNoData;
    private List<TeacherData> list1,list2,list3,list4,list5;
    private TeacherAdapter adapter;


    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        cDepartment = findViewById(R.id.cDepartment);
        csDepartment = findViewById(R.id.csDepartment);
        itDepartment = findViewById(R.id.itDepartment);
        pDepartment = findViewById(R.id.pDepartment);
        zDepartment = findViewById(R.id.zDepartment);

        itNoData =findViewById(R.id.itNoData);
        csNoData =findViewById(R.id.csNoData);
        cNoData =findViewById(R.id.cNoData);
        pNoData =findViewById(R.id.pNoData);
        zNoData =findViewById(R.id.zNoData);
        reference= FirebaseDatabase.getInstance().getReference().child("teacher");

        itDepartment();
        csDepartment();
        pDepartment();
        cDepartment();
        zDepartment();

        fab =findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this,AddTeacher.class));
            }
        });
    }

    private void itDepartment() {
        dbRef = reference.child("Information Technology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    itNoData.setVisibility(View.VISIBLE);
                    itDepartment.setVisibility(View.GONE);
                }else{
                    itNoData.setVisibility(View.GONE);
                    itDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    itDepartment.setHasFixedSize(true);
                    itDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1,UpdateFaculty.this,"Information Technology");
                    itDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void csDepartment() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2,UpdateFaculty.this,"Computer Science");
                    csDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void pDepartment() {
        dbRef = reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    pNoData.setVisibility(View.VISIBLE);
                    pDepartment.setVisibility(View.GONE);
                }else{
                    pNoData.setVisibility(View.GONE);
                    pDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    pDepartment.setHasFixedSize(true);
                    pDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3,UpdateFaculty.this,"Physics");
                    pDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void cDepartment() {
        dbRef = reference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    cNoData.setVisibility(View.VISIBLE);
                    cDepartment.setVisibility(View.GONE);
                }else{
                    cNoData.setVisibility(View.GONE);
                    cDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    cDepartment.setHasFixedSize(true);
                    cDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4,UpdateFaculty.this,"Chemistry");
                    cDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void zDepartment() {
        dbRef = reference.child("Zoology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    zNoData.setVisibility(View.VISIBLE);
                    zDepartment.setVisibility(View.GONE);
                }else{
                    zNoData.setVisibility(View.GONE);
                    zDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    zDepartment.setHasFixedSize(true);
                    zDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list5,UpdateFaculty.this,"Zoology");
                    zDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}















