package com.example.markusbink.travelapp;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markusbink.travelapp.Database.RoomDatabase;
import com.example.markusbink.travelapp.PackingList.PackingList;
import com.facebook.stetho.Stetho;

import java.util.Calendar;



public class MainActivity extends ActionBarActivity {

    EditText editTextvacationName;
    TextView editTextStartDate, editTextEndDate;
    Button buttonPlanVacation;
    DatePickerDialog datePickerDialog;
    private RoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build()
        );

        setContentView(R.layout.activity_main);

        initDB();
        initUi();
        initDatepicker();
        initListeners();

    }

    //Hide MainActivity menu-item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.start_activity).setVisible(false);
        return true;
    }


    private RoomDatabase initDB() {

        db = Room.databaseBuilder(MainActivity.this, RoomDatabase.class, Constants.DATABASENAME).fallbackToDestructiveMigration().build();
        return db;
    }


    private void initUi() {
        editTextvacationName = (EditText) findViewById(R.id.edittext_vacation_name);
        editTextStartDate = (TextView) findViewById(R.id.edittext_start_date);
        editTextEndDate = (TextView) findViewById(R.id.edittext_end_date);
        buttonPlanVacation = (Button) findViewById(R.id.button_plan_vacation);
    }

    private void initListeners() {

        buttonPlanVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String vacationName = editTextvacationName.getText().toString();
                final String startDate = editTextStartDate.getText().toString();
                final String endDate = editTextEndDate.getText().toString();


                if(!vacationName.equals("")  && !startDate.equals("") && !endDate.equals("")) {

                    Intent intent = new Intent(MainActivity.this, SingleVacationActivity.class);
                    intent.putExtra(Constants.VACATIONNAME, vacationName);
                    intent.putExtra(Constants.STARTDATE, startDate);
                    intent.putExtra(Constants.ENDDATE, endDate);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            MainActivity_SingleItem vacation = new MainActivity_SingleItem(vacationName, startDate, endDate);
                            db.mainActivityInterface().insertItem(vacation);

                        }
                    }).start();

                } else {

                    Toast.makeText(getApplicationContext(), "Bitte jeden Eintrag ausfüllen", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initDatepicker() {

        // Datepicker from Abhi Android: https://abhiandroid.com/ui/datepicker. Retrieved on September 6th, 2018. Small adjustments were made.
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR); // current year
                int month = c.get(Calendar.MONTH); // current month
                int day = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // set day of month , month and year value in the edit text
                                editTextStartDate.setText(day + "/"
                                        + (month + 1) + "/" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });

        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR); // current year
                int month = c.get(Calendar.MONTH); // current month
                int day = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // set day of month , month and year value in the edit text
                                editTextEndDate.setText(day + "/"
                                        + (month + 1) + "/" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });


    }



}
