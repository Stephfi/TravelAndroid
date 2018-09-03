package com.example.markusbink.travelapp.Database;

import android.arch.persistence.room.Database;

import com.example.markusbink.travelapp.PackingList.PackingList_SingleItem;
import com.example.markusbink.travelapp.SpendingCalculator.SpendingCalculator_SingleItem;


@Database(entities = {PackingList_SingleItem.class, SpendingCalculator_SingleItem.class}, version = 2, exportSchema = false)

public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

public abstract PackingListDao packingListInterface();

public abstract SpendingCalculatorDao spendingCalculatorInterface();


}
