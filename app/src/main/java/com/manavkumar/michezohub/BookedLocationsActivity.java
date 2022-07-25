package com.manavkumar.michezohub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookedLocationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookedLocationsAdapter bookedLocationsAdapter;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_locations);

        databaseReference = ((AppController) getApplicationContext()).getDatabase();
        String uid = FirebaseAuth.getInstance().getUid();

        recyclerView = findViewById(R.id.rv_booked_locations);
        bookedLocationsAdapter = new BookedLocationsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookedLocationsAdapter);

        if (uid != null) {
            databaseReference.child("users").child(uid).child("locations")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<Map<String, String>> bookedLocations =
                                    (List<Map<String, String>>) snapshot.getValue();

                            bookedLocationsAdapter.setBookedLocations(bookedLocations != null ? bookedLocations : new ArrayList<>());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databaseReference.child("users").child(uid).child("locations")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            List<Map<String, String>> bookedLocations =
                                    (List<Map<String, String>>) task.getResult().getValue();

                            bookedLocationsAdapter.setBookedLocations(bookedLocations != null ? bookedLocations : new ArrayList<>());
                        }
                    });
        }
    }
}
