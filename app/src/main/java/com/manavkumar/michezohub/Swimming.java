package com.manavkumar.michezohub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class Swimming extends AppCompatActivity {
    Spinner spinner;
    String[] items = {"Nairobi Gymkhana","The Goan Gymkhana", "Parklands Sports Club", "YMCA Swimming pool", "Aga Khan Sports Centre", "Premier Club", "Oshwal Complex Swimming Pool", "MOW Swimming Pool", "Kasarani Aquatic Stadium" };
    TextView pickDate;

    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimming);

        spinner = findViewById(R.id.bSpinner);
        pickDate = findViewById(R.id.pick_date);
        timeButton = findViewById(R.id.time_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Swimming.this, R.layout.item_file, items);
        adapter.setDropDownViewResource(R.layout.item_file);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String values = parent.getItemAtPosition(position).toString();
                Toast.makeText(Swimming.this, values, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                        Swimming.this, new DatePickerDialog.OnDateSetListener() {
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
}