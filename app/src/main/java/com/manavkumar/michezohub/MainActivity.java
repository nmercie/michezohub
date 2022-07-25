package com.manavkumar.michezohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnLogout;
    ImageView ath, bad, bas, cric, gol, rug, soc, squa, swim, tab, ten, vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AppBarLayout appBarLayout = findViewById(R.id.toolbarnav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, findViewById(R.id.drawer_layout));

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_logout:
                        logout();
                        break;
                    case R.id.nav_booked_location:
                        startActivity(new Intent(MainActivity.this, BookedLocationsActivity.class));
                }
                return false;
            }
        });

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

        SharedPreferences preferences = ((AppController) getApplicationContext()).getPreferences();
        String name = preferences.getString("name", "");
        String email = preferences.getString("email", "");

        View headerView = navigationView.getHeaderView(0);

        TextView tvName = headerView.findViewById(R.id.title_nav_header);
        tvName.setText(name);

        TextView tvEmail = headerView.findViewById(R.id.textView);
        tvEmail.setText(email);
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