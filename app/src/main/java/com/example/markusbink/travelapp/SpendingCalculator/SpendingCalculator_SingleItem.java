package com.example.markusbink.travelapp.SpendingCalculator;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName ="spendingCalculator")
public class SpendingCalculator_SingleItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int itemId;

    String name;
    String price;


    public SpendingCalculator_SingleItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    @NonNull
    public int getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
