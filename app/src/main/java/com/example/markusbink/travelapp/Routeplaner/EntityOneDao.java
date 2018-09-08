package com.example.markusbink.travelapp.Routeplaner;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface EntityOneDao {

    @Insert
    void insertPartsOfRouteOne(RoutePlaner_EntityOne routePlanerEntityOne);

    @Query("SELECT * FROM routeOne")
    RoutePlaner_EntityOne[] selectAllItemsFromRouteOne();

    @Query("DELETE FROM routeOne")
    void deleteAllItemsFromRouteOne();

    @Query("DELETE FROM routeOne WHERE id = :id")
    void deleteSelectedItemFromRouteOne(int id);

}
