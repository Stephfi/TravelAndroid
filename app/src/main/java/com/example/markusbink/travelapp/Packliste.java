package com.example.markusbink.travelapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Packliste extends AppCompatActivity {

    EditText editTextLuggage;
    Button buttonLuggage;
    ListView listViewLuggage;
    ArrayList<String> luggageList = new ArrayList<>();
    ArrayAdapter<String> luggageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packliste);

        getSupportActionBar().setTitle("Packliste");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextLuggage = (EditText) findViewById(R.id.editText_luggage);
        buttonLuggage = (Button) findViewById(R.id.button_add_luggage);
        listViewLuggage = (ListView) findViewById(R.id.listView_luggage);

        luggageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,luggageList);
        listViewLuggage.setAdapter(luggageAdapter);


        buttonLuggage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String luggageItem = editTextLuggage.getText().toString();


                if(!luggageItem.equals("")) {
                    luggageList.add(luggageItem);
                    editTextLuggage.setText("");
                    luggageAdapter.notifyDataSetChanged();

                }


            }
        });

        listViewLuggage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                luggageList.remove(position);
                luggageAdapter.notifyDataSetChanged();

                return false;
            }
        });






    }
}
