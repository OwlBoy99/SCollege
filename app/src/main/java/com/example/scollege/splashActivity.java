package com.example.scollege;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread td = new Thread(){
            public void run(){
                try {
                    sleep( 2000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(splashActivity.this, page.class);
                    startActivity(intent);
                    finish();
                }

            }
        };td.start();

    }
}