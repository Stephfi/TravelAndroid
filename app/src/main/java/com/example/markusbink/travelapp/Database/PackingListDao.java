package com.example.markusbink.travelapp.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.markusbink.travelapp.PackingList.PackingList_SingleItem;

@Dao
public interface PackingListDao {

    @Insert
    void insertItem(PackingList_SingleItem item);

    @Query("DELETE FROM packingItems WHERE itemName = :item")
    void deleteItem(String item);

    @Query("SELECT * FROM packingItems")
    PackingList_SingleItem[] selectAllPackingItems();

    @Query("DELETE FROM packingItems")
    void deleteAllItems();





}
