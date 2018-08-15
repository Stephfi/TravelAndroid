package com.example.markusbink.travelapp;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;

@Dao
public interface PackingListInterface {

    @Insert
    void insertItem(PackingListActivityItem item);

    @Query("DELETE FROM packingItems WHERE itemName = :item")
    void deleteItem(String item);

    @Query("SELECT * FROM packingItems")
    PackingListActivityItem[] selectAllItems();





    @Query("DELETE FROM packingItems")
    void deleteAllItems();





}
