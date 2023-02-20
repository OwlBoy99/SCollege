package com.example.scollege;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class UserScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frame_layout);

        drawerLayout =findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_profile:
                Toast.makeText(this, "Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_video:
                Toast.makeText(this, "Video Lectures",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_timetable:
                Toast.makeText(this, "Time Table",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                Toast.makeText(this, "EBooks",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_attendance:
                Toast.makeText(this, "Attendance",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_result:
                Toast.makeText(this, "Result",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_theme:
                Toast.makeText(this, "Themes",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_logout:
                Toast.makeText(this, "Logout",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}