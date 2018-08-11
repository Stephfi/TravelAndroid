package com.example.markusbink.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextvacationName, editTextStartDate, editTextEndDate;
    Button buttonPlanVacation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextvacationName = (EditText) findViewById(R.id.edittext_vacation_name);
        editTextStartDate = (EditText) findViewById(R.id.edittext_start_date);
        editTextEndDate = (EditText) findViewById(R.id.edittext_end_date);
        buttonPlanVacation = (Button) findViewById(R.id.button_plan_vacation);

        buttonPlanVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vacationName = editTextvacationName.getText().toString();
                String startDate = editTextStartDate.getText().toString();
                String endDate = editTextEndDate.getText().toString();

                Intent intent = new Intent(MainActivity.this, SingleVacationActivity.class);
                intent.putExtra(Constants.VACATIONNAME, vacationName);
                intent.putExtra(Constants.STARTDATE, startDate);
                intent.putExtra(Constants.ENDDATE, endDate);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.start_activity:
                Intent startIntent = new Intent(this, MainActivity.class);
                startActivity(startIntent);
                return true;

            case R.id.packliste_activity:
                Intent packlisteIntent = new Intent(this, Packliste.class);
                startActivity(packlisteIntent);
                return true;

            case R.id.singlevacation_activity:
                Intent i = new Intent(this, SingleVacationActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
