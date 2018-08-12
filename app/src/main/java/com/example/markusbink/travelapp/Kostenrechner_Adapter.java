package com.example.markusbink.travelapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Kostenrechner_Adapter extends ArrayAdapter<Kostenrechner_SingleItem> {


    private Context context;
    private int resource;

    public Kostenrechner_Adapter(Context context, int resource, ArrayList<Kostenrechner_SingleItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons informaion
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();



        //create a person
        Kostenrechner_SingleItem singleItem = new Kostenrechner_SingleItem(name, price);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textview_kostenrechner_label);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.textview_kostenrechner_price);

        textViewName.setText(name);
        textViewPrice.setText(price);

        return convertView;

    }


}
