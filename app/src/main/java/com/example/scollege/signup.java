package com.example.scollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scollege.R;
import com.example.scollege.main;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    EditText Username,Email1,pass1;
    boolean valid =true;
    Button btn1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CheckBox isAdminBox,isStudentBox;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn1 = findViewById(R.id.btn1);
        fAuth =FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();
        Button btn6 = findViewById(R.id.btn6);
        TextView btn2=findViewById(R.id.txtview2);
        Username  = findViewById(R.id.Username);
        Email1 = findViewById(R.id.Email1);
        pass1 = findViewById(R.id.pass1);
        isAdminBox = findViewById(R.id.isTeacher);
        isStudentBox = findViewById(R.id.isStudent);

        isStudentBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isAdminBox.setChecked(false);
                }
            }
        });
        isAdminBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isStudentBox.setChecked(false);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,main.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(Username);
                checkField(Email1);
                checkField(pass1);

                if(!(isAdminBox.isChecked() || isStudentBox.isChecked())){
                    Toast.makeText(signup.this, "Select the Account type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(valid){
                    fAuth.createUserWithEmailAndPassword(Email1.getText().toString(),pass1.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(signup.this,"AccountCreated",Toast.LENGTH_SHORT).show();
                            DocumentReference df =fStore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("Username",Username.getText().toString());
                            userInfo.put("UserEmail",Email1.getText().toString());
                            if(isAdminBox.isChecked()){
                                userInfo.put("isAdmin","1");
                            }
                            if(isStudentBox.isChecked()){
                                userInfo.put("isUser","1");
                            }
                            df.set(userInfo);
                            if(isAdminBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(),AdminScreen.class));
                                finish();
                            }
                            if(isStudentBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(),UserScreen.class));
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(signup.this,"Failed to create a Account",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }

        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),main.class));
            }
        });
        pass1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=pass1.getRight()-pass1.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=pass1.getSelectionEnd();
                        if(passwordVisible){
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        pass1.setSelection(selection);
                        return true;
                    }
                }



                return false;
            }
        });
    }
    public boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()){
            textField.setError("Empty");
            valid = false;
        }else{
            valid=true;
        }
        return valid;
    }


}