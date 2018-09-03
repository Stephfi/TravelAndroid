package com.example.markusbink.travelapp.SpendingCalculator;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.markusbink.travelapp.ActionBarActivity;
import com.example.markusbink.travelapp.Database.RoomDatabase;
import com.example.markusbink.travelapp.PackingList.PackingList;
import com.example.markusbink.travelapp.PackingList.PackingList_SingleItem;
import com.example.markusbink.travelapp.R;

import java.util.ArrayList;

public class SpendingCalculator extends ActionBarActivity {

    private static final String TAG = "SpendingCalculator";
    public static final String DATABASE_NAME = "spendingListDB";

    private EditText editTextLabel, editTextPrice;
    private Button buttonAdd;
    private ListView listView;
    private ArrayList<SpendingCalculator_SingleItem> arrayList = new ArrayList<>();
    private SpendingCalculator_SingleItem[] spendingList;
    private SpendingCalculator_Adapter arrayAdapter;
    private RoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendingcalculator);
            initDB();
            initUi();
            initActionBar();
            initListeners();
    }

    private RoomDatabase initDB() {
        db = Room.databaseBuilder(SpendingCalculator.this, RoomDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        return db;
    }

    private void initListeners() {

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addSingleSpendingToListView();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                removeSingleSpendingFromListView(position);
                return true;
            }
        });

    }

    //Add a single Element to UI and DB

    private void addSingleSpendingToListView() {

        final String labelItem = editTextLabel.getText().toString();
        final String priceItem = editTextPrice.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                    final SpendingCalculator_SingleItem spendingItem = new SpendingCalculator_SingleItem(labelItem, priceItem);

                    db.spendingCalculatorInterface().insertItem(spendingItem);
                Log.d(TAG, "run: addSingleSpendingToView Method called");

                    Log.d(TAG, "run: Item added to Database");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            addItemToList(spendingItem);
                            editTextLabel.setText("");
                            editTextPrice.setText("");


                            Log.d(TAG, "run: Item added to ListView");
                        }
                    });
                }
        }).start();
    }

    // Remove a single item from database and list
    private void removeSingleSpendingFromListView(final int position) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final int listViewPosition = position + 1;

                final String itemPosition = String.valueOf(listViewPosition);

                db.spendingCalculatorInterface().deleteSpendingItem(listViewPosition);

                Log.d(TAG, "Item removed from Database");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged();

                        Log.d(TAG, "Item removed from ListView");

                        Toast.makeText(getApplicationContext(), "Item has been removed", Toast.LENGTH_SHORT).show();



                    }
                });
            }
        }).start();
    }


    private void addItemToList(SpendingCalculator_SingleItem itemName) {
        arrayList.add(itemName);
        arrayAdapter.notifyDataSetChanged();
    }

    private void removeSingleSpending(int position) {
        arrayList.remove(position);
        arrayAdapter.notifyDataSetChanged();
    }


    private void deleteAllListItems() {

        Log.d(TAG, "deleteAllIListItems: deleted");

        new Thread(new Runnable() {
            @Override
            public void run() {


                db.spendingCalculatorInterface().deleteAllSpendingItems();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(arrayList.size() != 0) {

                            arrayList.clear();
                            arrayAdapter.notifyDataSetChanged();

                            Toast.makeText(getApplicationContext(), "All Items have been removed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Items to remove", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        }).start();



    }

    private void initUi(){
        editTextLabel = (EditText)findViewById(R.id.editText_label);
        editTextPrice = (EditText)findViewById(R.id.editText_price);
        buttonAdd = (Button)findViewById(R.id.button_add_price);
        listView = (ListView)findViewById(R.id.listView_prices);
        arrayAdapter = new SpendingCalculator_Adapter(SpendingCalculator.this, R.layout.singleitem_spendingcalculator, arrayList);
        listView.setAdapter(arrayAdapter);
        initSavedItems();

    }

    private void initSavedItems() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                spendingList = db.spendingCalculatorInterface().selectAllSpendingItems();
                for(SpendingCalculator_SingleItem singleItem : spendingList) {
                    addItemToList(singleItem);
                }


            }
        }).start();



    }

    //Change Title
    private void initActionBar() {
        getSupportActionBar().setTitle("Kostenrechner");
    }

    //Set Delete-Icon and OnClickListener for it
    //Hide PackingList menu item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.packinglist_activity).setVisible(false);
        menu.findItem(R.id.deleteItems).setVisible(true).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                deleteAllListItems();

                return false;
            }
        });
        return true;
    }

}


