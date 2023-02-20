package com.example.scollege;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.scollege.faculty.UpdateFaculty;
import com.example.scollege.notice.DeleteNoticeActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminScreen extends AppCompatActivity implements View.OnClickListener {

    CardView Notice,addEbook,addFaculty,deletedNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
        Notice = findViewById(R.id.addNotice);
        deletedNotice = findViewById(R.id.deletedNotice);
        addEbook = findViewById(R.id.addEbook);
        addFaculty = findViewById(R.id.addFaculty);
        Notice.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        addFaculty.setOnClickListener(this);
        deletedNotice.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addNotice:
                Intent intent = new Intent(AdminScreen.this,Notice.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(AdminScreen.this, UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.addFaculty:
                intent = new Intent(AdminScreen.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deletedNotice:
                intent = new Intent(AdminScreen.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Logout")){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),main.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}