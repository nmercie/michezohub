package com.manavkumar.michezohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnLogout;
    ImageView ath, bad, bas, cric, gol, rug, soc, squa, swim, tab, ten, vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing
        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        ath = findViewById(R.id.Athletics);
        bad = findViewById(R.id.Badminton);
        bas = findViewById(R.id.Basketball);
        cric = findViewById(R.id.Cricket);
        gol = findViewById(R.id.Golf);
        rug = findViewById(R.id.Rugby);
        soc = findViewById(R.id.Soccer);
        squa = findViewById(R.id.Squash);
        swim = findViewById(R.id.Swimming);
        tab = findViewById(R.id.Tabletennis);
        ten = findViewById(R.id.Tennis);
        vol = findViewById(R.id.Volleyball);

        ath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Athletics.class);
                startActivity(intent);
            }
        });

        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Badminton.class);
                startActivity(intent);
            }
        });

        bas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Basketball.class);
                startActivity(intent);
            }
        });

        cric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cricket.class);
                startActivity(intent);
            }
        });

        gol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Golf.class);
                startActivity(intent);
            }
        });

        rug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Rugby.class);
                startActivity(intent);
            }
        });

        soc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Soccer.class);
                startActivity(intent);
            }
        });

        squa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Squash.class);
                startActivity(intent);
            }
        });

        swim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Swimming.class);
                startActivity(intent);
            }
        });

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tabletennis.class);
                startActivity(intent);
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tennis.class);
                startActivity(intent);
            }
        });

        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Volleyball.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}