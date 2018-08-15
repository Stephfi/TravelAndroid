package com.example.markusbink.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.markusbink.travelapp.PackingList.PackingList;
import com.example.markusbink.travelapp.SpendingCalculator.SpendingCalculator;

public class ActionBarActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
    }

    //INFLATE THE MENU WITH LAYOUT

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //CLICKEVENTS FOR THE MAIN MENU-ITEMS

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.start_activity:
                Intent startIntent = new Intent(this, MainActivity.class);
                startActivity(startIntent);
                return true;

            case R.id.packliste_activity:
                Intent packlisteIntent = new Intent(this, PackingList.class);
                startActivity(packlisteIntent);
                return true;

            case R.id.singlevacation_activity:
                Intent i = new Intent(this, SingleVacationActivity.class);
                startActivity(i);
                return true;

            case R.id.kostenrechner_activity:
                Intent kostenrechner = new Intent(this, SpendingCalculator.class);
                startActivity(kostenrechner);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
