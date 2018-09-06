package com.example.markusbink.travelapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "single_vacations")
public class MainActivity_SingleItem {

@PrimaryKey(autoGenerate = true)
private int vacationId;

private String vacationName;
private String startDate;
private String endDate;

    public MainActivity_SingleItem(String vacationName, String startDate, String endDate) {
        this.vacationName = vacationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getVacationId() {
        return vacationId;
    }

    public void setVacationId(int vacationId) {
        this.vacationId = vacationId;
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
