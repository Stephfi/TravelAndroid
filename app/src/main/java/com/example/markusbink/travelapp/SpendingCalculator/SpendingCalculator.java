package com.example.markusbink.travelapp.SpendingCalculator;

import android.content.Context;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.markusbink.travelapp.ActionBarActivity;
import com.example.markusbink.travelapp.R;

import java.util.ArrayList;

public class SpendingCalculator extends ActionBarActivity {

    EditText editTextLabel, editTextPrice;
    Button buttonAdd;
    ArrayList<SpendingCalculator_SingleItem> arrayList = new ArrayList<>();
    SpendingCalculator_Adapter arrayAdapter;
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

                SpendingCalculator_SingleItem newItem = new SpendingCalculator_SingleItem(name, price);
                arrayList.add(newItem);
                arrayAdapter = new SpendingCalculator_Adapter(SpendingCalculator.this, R.layout.singleitem_kostenrechner, arrayList);
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
