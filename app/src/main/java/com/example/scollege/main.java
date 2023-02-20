package com.example.scollege;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class main extends AppCompatActivity {
    private EditText email,
    pass;
    private Button btn;
    Switch active;
    boolean valid=true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btn = findViewById(R.id.btn);
        fAuth =FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();

        TextView btn10 = findViewById(R.id.txtsignup1);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main.this, com.example.scollege.signup.class));
            }
        });

        TextView btn4 = findViewById(R.id.forgot_Pass);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main.this, forgotPass.class));
            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(email);
                checkField(pass);
                if(valid){
                    fAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(main.this,"LoggedIn Successfully",Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(main.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess"+documentSnapshot.getData());
                if(documentSnapshot.getString("isAdmin")!=null){
                    startActivity(new Intent(getApplicationContext(),AdminScreen.class));
                    finish();
                }
                if(documentSnapshot.getString("isUser")!=null){
                    startActivity(new Intent(getApplicationContext(),UserScreen.class));
                    finish();
                }
            }
        });
    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Empty");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}