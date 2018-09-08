package com.example.markusbink.travelapp.Routeplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.markusbink.travelapp.ActionBarActivity;
import com.example.markusbink.travelapp.Constants;
import com.example.markusbink.travelapp.R;



/*
Displays three Routes, which can be edited by the user after clicking one of the buttons.
 */
public class FirstscreenRouteplaner extends ActionBarActivity {

    TextView routeOne, routeTwo, routeThree;
    Button buttonOne, buttonTwo, buttonThree;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routeplaner_firstscreen);

        routeOne = findViewById(R.id.Route_1);
        routeTwo = findViewById(R.id.Route_2);
        routeThree = findViewById(R.id.Route_3);

        buttonOne = findViewById(R.id.config_button_1);
        buttonTwo = findViewById(R.id.config_button_2);
        buttonThree = findViewById(R.id.config_button_3);


        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent switchToRouteOne = new Intent(FirstscreenRouteplaner.this, SecondscreenRouteplaner.class);
                switchToRouteOne.putExtra(Constants.KEY_ROUTE_ONE, 1);
                startActivity(switchToRouteOne);
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent switchToRouteTwo = new Intent(FirstscreenRouteplaner.this, SecondscreenRouteplaner.class);
                switchToRouteTwo.putExtra(Constants.KEY_ROUTE_TWO, 2);
                startActivity(switchToRouteTwo);
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent switchToRouteThree = new Intent(FirstscreenRouteplaner.this, SecondscreenRouteplaner.class);
                switchToRouteThree.putExtra(Constants.KEY_ROUTE_THREE, 3);
                startActivity(switchToRouteThree);
            }
        });
    }

    //Hide Route menu item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.route_activity).setVisible(false);
        return true;
    }

}
