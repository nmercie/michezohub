package com.manavkumar.michezohub;

import static com.manavkumar.michezohub.Constants.DEFAULT_NUM_SLOTS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.manavkumar.michezohub.model.Categories;
import com.manavkumar.michezohub.model.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Locale;
import java.util.Map;


public class Athletics extends BaseActivity {
    private final String category = "athletics";
    private final int layout = R.layout.activity_athletics;


    Spinner spinner;
    String[] items = {"Nairobi Gymkhana", "Moi International Sports Center - Kasarani", "Parklands Sports Club", "Oshwal Sports Centre", "Aga Khan Sports Centre", "Nyayo Stadium", "Nairobi Jaffery Sports Club", "Ruaraka Sports Club" };
    TextView pickDate;

    Button timeButton;
    Button submitBtn;
    int hour, minute;
    private DatabaseReference database;
    private String position;
    private String name;
    private ArrayList<Map<String, String>> categories;
    private TextView availableSlots;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pickDate = findViewById(R.id.pick_date);
        timeButton = findViewById(R.id.time_button);


        //Initialize Calendar
        Calendar calendar = Calendar.getInstance();
        //Get year
        int year = calendar.get(Calendar.YEAR);
        //Get month
        int month = calendar.get(Calendar.MONTH);
        //Get day
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Athletics.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        //Store date in string
                        String sDate = dayOfMonth + "/" + month + "/" + year;
                        //Set date on text view
                        pickDate.setText(sDate);
                    }
                }, year,month,day
                );
                //Disable Past Date
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //Show date picker dialog
                datePickerDialog.show();
            }
        });
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(),"%02d %02d", hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public int getLayout() { return layout;
    }
}