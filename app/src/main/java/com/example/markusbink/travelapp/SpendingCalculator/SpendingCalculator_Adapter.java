package com.example.markusbink.travelapp.SpendingCalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.markusbink.travelapp.R;

import java.util.ArrayList;

public class SpendingCalculator_Adapter extends ArrayAdapter<SpendingCalculator_SingleItem> {


    private Context context;
    private int resource;

    public SpendingCalculator_Adapter(Context context, int resource, ArrayList<SpendingCalculator_SingleItem> objects) {
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
        SpendingCalculator_SingleItem singleItem = new SpendingCalculator_SingleItem(name, price);
        

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textview_kostenrechner_label);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.textview_kostenrechner_price);

        textViewName.setText(name);
        textViewPrice.setText(price);

        return convertView;

    }


}
