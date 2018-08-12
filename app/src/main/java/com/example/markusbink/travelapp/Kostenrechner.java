package com.example.markusbink.travelapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Kostenrechner extends AppCompatActivity {

    EditText editTextLabel, editTextPrice;
    Button buttonAdd;
    ArrayList<Kostenrechner_SingleItem> arrayList = new ArrayList<>();
    Kostenrechner_Adapter arrayAdapter;
    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kostenrechner);

        editTextLabel = (EditText)findViewById(R.id.editText_label);
        editTextPrice = (EditText)findViewById(R.id.editText_price);
        buttonAdd = (Button)findViewById(R.id.button_add_price);
        listView = (ListView)findViewById(R.id.listView_prices);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextLabel.getText().toString();
                String price = editTextPrice.getText().toString();

                Kostenrechner_SingleItem newItem = new Kostenrechner_SingleItem(name, price);
                arrayList.add(newItem);
                arrayAdapter = new Kostenrechner_Adapter(Kostenrechner.this, R.layout.singleitem_kostenrechner, arrayList);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });









    }
}
