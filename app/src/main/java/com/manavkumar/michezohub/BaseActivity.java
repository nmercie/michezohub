package com.manavkumar.michezohub;

import static com.manavkumar.michezohub.Constants.DEFAULT_NUM_SLOTS;
import static com.manavkumar.michezohub.Constants.REMAINING_SLOTS;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.manavkumar.michezohub.model.Category;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {
    private int position;
    private String name;
    private List<Map<String, String>> categories;
    private DatabaseReference database;
    private String[] items;
    private ArrayAdapter<String> adapter;
    private Spinner spinner;
    private TextView availableSlots;
    private String category;
    private int layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayout());
        category = this.getCategory();

        database = ((AppController) getApplication()).getDatabase();

        availableSlots = findViewById(R.id.available_slots);
        spinner = findViewById(R.id.bSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                BaseActivity.this.position = position;
                String values = parent.getItemAtPosition(position).toString();
                BaseActivity.this.name = values;
                Toast.makeText(BaseActivity.this, values, Toast.LENGTH_SHORT).show();
                Map<String, String> map = BaseActivity.this.categories.get(position);
                if (map.containsKey(REMAINING_SLOTS)) {
                    availableSlots.setText(map.get(REMAINING_SLOTS));
                } else {
                    availableSlots.setText(String.valueOf(DEFAULT_NUM_SLOTS));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button submitBtn = (Button) findViewById(R.id.submitbtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(BaseActivity.this.position));
                map.put("name", BaseActivity.this.name);
                Map<String, String> mapItem = categories.get(BaseActivity.this.position);

                int remainingSlots = Integer.parseInt(mapItem.get(REMAINING_SLOTS)) - 1;
                if (mapItem.containsKey(REMAINING_SLOTS)) {
                    map.put(REMAINING_SLOTS, String.valueOf(remainingSlots));
                } else {
                    map.put(REMAINING_SLOTS, String.valueOf(DEFAULT_NUM_SLOTS - 1));
                }
                if (remainingSlots > 0) {
                    database.child("locations").child("categories").child(category).child(String.valueOf(BaseActivity.this.position)).setValue(map);
                    Toast.makeText(BaseActivity.this, "Done", Toast.LENGTH_LONG).show();
//                    return;
                }
//                Toast.makeText(BaseActivity.this, "No available slots", Toast.LENGTH_SHORT).show();
            }
        });

        database.child("locations").child("categories").child(this.getCategory())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        categories = (ArrayList<Map<String, String>>) snapshot.getValue();
                        items = new String[categories.size()];

                        for (int i = 0; i < categories.size(); i++) {
                            Map<String, String> entry = categories.get(i);
                            items[i] = entry.get("name");
                            Map<String, String> map = categories.get(i);
                            if (map.containsKey(REMAINING_SLOTS)) {
                                availableSlots.setText(map.get(REMAINING_SLOTS));
                            } else {
                                availableSlots.setText(String.valueOf(DEFAULT_NUM_SLOTS));
                            }
                        }

                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter = new ArrayAdapter<String>(BaseActivity.this, R.layout.item_file, items);
                            adapter.setDropDownViewResource(R.layout.item_file);
                            spinner.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        database.child("locations").child("categories").child(this.getCategory())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            categories = (ArrayList<Map<String, String>>) task.getResult().getValue();
                            items = new String[categories.size()];

                            for (int i = 0; i < categories.size(); i++) {
                                Map<String, String> entry = categories.get(i);
                                items[i] = entry.get("name");
                            }
                            adapter = new ArrayAdapter<String>(BaseActivity.this, R.layout.item_file, items);
                            adapter.setDropDownViewResource(R.layout.item_file);
                            spinner.setAdapter(adapter);
                        }
                    }
                });
    }

    public abstract String getCategory();

    public abstract int getLayout();
}
