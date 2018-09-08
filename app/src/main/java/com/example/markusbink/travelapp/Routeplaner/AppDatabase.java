package com.example.markusbink.travelapp.Routeplaner;

import android.arch.persistence.room.Database;

@Database(entities = {RoutePlaner_EntityOne.class, RoutePlaner_EntityTwo.class, RoutePlaner_EntityThree.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends  android.arch.persistence.room.RoomDatabase {

    /*
    Connects the Dao-Interfaces to the Activities.
    Contains Data from all three Routes in three different Entities
     */

    public abstract EntityOneDao routeOneInterface();

    public abstract EntityTwoDao routeTwoInterface();

    public abstract EntityThreeDao routeThreeInterface();
}
