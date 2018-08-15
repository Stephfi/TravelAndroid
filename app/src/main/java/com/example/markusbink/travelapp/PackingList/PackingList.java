package com.example.markusbink.travelapp.PackingList;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.markusbink.travelapp.ActionBarActivity;
import com.example.markusbink.travelapp.Database.RoomDatabase;
import com.example.markusbink.travelapp.R;

import java.util.ArrayList;

public class PackingList extends ActionBarActivity {

    private static final String TAG = "PackingList";

    private final String DATABASE_NAME = "packingListDB";

    private EditText editTextLuggage;
    private Button buttonLuggage;
    private ListView listViewLuggage;
    private ArrayList<String> luggageList = new ArrayList<>();
    private PackingList_SingleItem[] packingList;
    private ArrayAdapter<String> luggageAdapter;
    private RoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packinglist);
            initDB();
            initUi();
            initActionBar();
            initListeners();

        getSupportActionBar().setTitle("Packliste");



    }

    private RoomDatabase initDB() {

        db = Room.databaseBuilder(PackingList.this, RoomDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
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
                PackingList_SingleItem packingItem = new PackingList_SingleItem();
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


    // Remove all Items from database and list

    public void deleteAllIListItems() {

        Log.d(TAG, "deleteAllIListItems: deleted");

        new Thread(new Runnable() {
            @Override
            public void run() {

                
              db.packingListInterface().deleteAllItems();

              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {

                      if(luggageList.size() != 0) {

                          luggageList.clear();
                          luggageAdapter.notifyDataSetChanged();

                          Toast.makeText(getApplicationContext(), "All Items have been removed", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(getApplicationContext(), "No Items to remove", Toast.LENGTH_SHORT).show();

                      }
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
                for(PackingList_SingleItem item : packingList) {
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
        getSupportActionBar().setTitle("PackingList");
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


    //SET DELETE-ICON AND ONCLICKLISTENER FOR IT

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.deleteItems).setVisible(true).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                deleteAllIListItems();

                return false;
            }
        });



        return true;



    }
}
