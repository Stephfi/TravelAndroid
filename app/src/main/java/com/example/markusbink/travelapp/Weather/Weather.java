package com.example.markusbink.travelapp.Weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.markusbink.travelapp.ActionBarActivity;
import com.example.markusbink.travelapp.R;
import com.facebook.stetho.Stetho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends ActionBarActivity {

    private TextView textViewTemp, textViewPressure, textViewHumidity, textViewWind, textViewCity;
    private EditText editTextCity;
    private Button buttonShowData;
    private RequestQueue mQueue;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initUi();
        initActionBar();


        mQueue = Volley.newRequestQueue(this);


        buttonShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = editTextCity.getText().toString();

                if(city.equals("")) {
                    Toast.makeText(getApplicationContext(), "Bitte Ort eingeben.", Toast.LENGTH_SHORT).show();
                }

                jsonParse(city);
                editTextCity.setText("");
            }
        });

    }


    //JSON Parsing based on the Tutorial from Coding in Flow on Youtube. Link: https://www.youtube.com/watch?v=y2xtLqP8dSQ&t=217s Changes were made to suit our functionality
    //Weather Data retrieved from openweathermap.org
    private void jsonParse(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=08b0554d25e979ac4f422baf20a69306&units=metric";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    //get main content
                    JSONObject main = response.getJSONObject("main");
                    double temp = main.getDouble("temp");
                    int pressure = main.getInt("pressure");
                    int humidity = main.getInt("humidity");


                    //get wind content
                    JSONObject wind = response.getJSONObject("wind");
                    double speed = wind.getDouble("speed");

                    //get location data

                    JSONObject sys = response.getJSONObject("sys");
                    String country = sys.getString("country");
                    String city = response.getString("name");

                    String cityCountryString = city + ", " + country;


                    //set textviews
                    textViewCity.setText(cityCountryString);
                    textViewTemp.setText(String.valueOf(temp + "°"));
                    textViewPressure.setText(String.valueOf(pressure + " hPa"));
                    textViewHumidity.setText(String.valueOf(humidity + " %"));
                    textViewWind.setText(String.valueOf(speed + " m/s"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }








    private void initUi() {
        textViewTemp = findViewById(R.id.textview_temperature);
        textViewPressure = findViewById(R.id.textview_pressure);
        textViewHumidity = findViewById(R.id.textview_humidity);
        textViewWind = findViewById(R.id.textview_wind);
        textViewCity = findViewById(R.id.textview_city);
        editTextCity = findViewById(R.id.edittext_city);
        buttonShowData = findViewById(R.id.button_show_data);
    }

    private void initActionBar() {

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Wetter");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    //Hide Weather menu-item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.weather_activity).setVisible(false);
        return true;
    }



}


