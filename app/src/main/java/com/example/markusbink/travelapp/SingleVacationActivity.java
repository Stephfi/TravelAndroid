package com.example.markusbink.travelapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SingleVacationActivity extends AppCompatActivity {

    TextView textViewVacationName, textViewStartDate, textViewEndDate;
    Button buttonBackButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlevacation);

        getSupportActionBar().setTitle("SingleVacationActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewVacationName = (TextView) findViewById(R.id.textview_vacation_name);
        textViewStartDate = (TextView) findViewById(R.id.textview_start_date);
        textViewEndDate = (TextView) findViewById(R.id.textview_end_date);
        buttonBackButton = (Button) findViewById(R.id.button_back_startactivity);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String vacationName = extras.getString(Constants.VACATIONNAME);
            String startDate = extras.getString(Constants.STARTDATE);
            String endDate = extras.getString(Constants.ENDDATE);

            textViewVacationName.setText(vacationName);
            textViewStartDate.setText(startDate);
            textViewEndDate.setText(endDate);


        }

        buttonBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingleVacationActivity.this, MainActivity.class);
                startActivity(i);
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
