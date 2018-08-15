package com.example.markusbink.travelapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class Packliste extends AppCompatActivity {

    private static final String TAG = "PackingList";

    private final String DATABASE_NAME = "packingListDB";

    private EditText editTextLuggage;
    private Button buttonLuggage;
    private ListView listViewLuggage;
    private ArrayList<String> luggageList = new ArrayList<>();
    private PackingListActivityItem[] packingList;
    private ArrayAdapter<String> luggageAdapter;
    private PackingListDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packliste);
            initDB();
            initUi();
            initActionBar();
            initListeners();



    }

    private PackingListDatabase initDB() {

        db = Room.databaseBuilder(Packliste.this, PackingListDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        return db;
    }

    private void initListeners() {

        buttonLuggage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewItemToListView();

            }
        });

        listViewLuggage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                removeItemFromListView(position);
                return true;
            }
        });


    }

    private void addNewItemToListView() {

        final String luggageItem = editTextLuggage.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {

                if(!luggageItem.equals("")) {
                PackingListActivityItem packingItem = new PackingListActivityItem();
                packingItem.setItemName(luggageItem);
                db.packingListInterface().insertItem(packingItem);

                Log.d(TAG, "run: Item added to Database");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        addItemToList(luggageItem);
                        editTextLuggage.setText("");


                        Log.d(TAG, "run: Item added to ListView");
                    }
                });
            }}
        }).start();
    }

    private void removeItemFromListView(final int position) {
        final String itemName =  listViewLuggage.getItemAtPosition(position).toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                db.packingListInterface().deleteItem(itemName);

                Log.d(TAG, "Item removed from Database");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        luggageList.remove(position);
                        luggageAdapter.notifyDataSetChanged();

                        Log.d(TAG, "Item removed from ListView");

                        Toast.makeText(getApplicationContext(), "Item has been removed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();
    }


    private void initSavedItems() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                packingList = db.packingListInterface().selectAllItems();
                for(PackingListActivityItem item : packingList) {
                    String itemName = item.getItemName();
                   addItemToList(itemName);
                }


            }
        }).start();


    }


    private void addItemToList(String itemName) {
        luggageList.add(itemName);
        luggageAdapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        getSupportActionBar().setTitle("Packliste");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void initUi() {
        editTextLuggage = (EditText) findViewById(R.id.editText_luggage);
        buttonLuggage = (Button) findViewById(R.id.button_add_luggage);
        listViewLuggage = (ListView) findViewById(R.id.listView_luggage);
        luggageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,luggageList);
        listViewLuggage.setAdapter(luggageAdapter);
        initSavedItems();
    }

}
