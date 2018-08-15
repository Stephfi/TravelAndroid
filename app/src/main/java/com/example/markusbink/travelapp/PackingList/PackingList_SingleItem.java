package com.example.markusbink.travelapp.PackingList;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "packingItems")
public class PackingList_SingleItem {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private String itemName;

    @NonNull
    public int getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull int itemId) {
        this.itemId = itemId;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public PackingList_SingleItem() {

    }
}
