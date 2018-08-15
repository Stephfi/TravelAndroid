package com.example.markusbink.travelapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {PackingListActivityItem.class}, version = 1, exportSchema = false)

public abstract class PackingListDatabase extends RoomDatabase {

public abstract PackingListInterface packingListInterface();



}
