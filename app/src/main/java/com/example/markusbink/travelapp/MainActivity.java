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

import com.example.markusbink.travelapp.PackingList.PackingList;
import com.example.markusbink.travelapp.SpendingCalculator.SpendingCalculator;
import com.facebook.stetho.Stetho;

public class MainActivity extends ActionBarActivity {

    EditText editTextvacationName, editTextStartDate, editTextEndDate;
    Button buttonPlanVacation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build()
        );

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

    //Hide MainActivity menu-item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.start_activity).setVisible(false);
        return true;
    }


}
