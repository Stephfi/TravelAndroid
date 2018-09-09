package com.example.markusbink.travelapp.Routeplaner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.markusbink.travelapp.R;
import java.util.ArrayList;

/*
For the work with the RecyclerView and the Adapter, three videos were the source for
information and inspiration. Some major changes were made. Retrieved between August 1st 2018
and September 8th 2018.  https://www.youtube.com/watch?v=CTBiwKlO5IU
https://www.youtube.com/watch?v=3CG-iXdje6E,  https://www.youtube.com/watch?v=ijx4sZVAdHw&t
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<RoutePlaner_EntityOne> routePlanerEntityOne;
    private ArrayList<RoutePlaner_EntityTwo> routePlanerEntityTwo;
    private ArrayList<RoutePlaner_EntityThree> routePlanerEntityThree;

    private int extra1,extra2,extra3;


    public RvAdapter(ArrayList<RoutePlaner_EntityOne> routePlanerEntityOne, ArrayList<RoutePlaner_EntityTwo> routePlanerEntityTwo, ArrayList<RoutePlaner_EntityThree> routePlanerEntityThree, int extra1, int extra2, int extra3){
        this.routePlanerEntityOne = routePlanerEntityOne;
        this.routePlanerEntityTwo = routePlanerEntityTwo;
        this.routePlanerEntityThree = routePlanerEntityThree;
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.extra3 = extra3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView elementArrowDown;
        TextView elementTransportation;
        TextView elementDestination;

        public ViewHolder(View itemView) {

            super(itemView); //Sends the Elements to the onBindViewHolder - Method
            elementArrowDown = itemView.findViewById(R.id.imageView);
            elementTransportation = itemView.findViewById(R.id.transportation_show);
            elementDestination = itemView.findViewById(R.id.destination_show);
        }
    }

    @NonNull
    @Override
    //Sets the layout for a single item of the RecyclerView
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View singleElement = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_routeplaner, null);

        return new ViewHolder(singleElement);
    }


    /*
    Displays the input from the user in the right views.
    Provides visual feedback when an item is clicked.
    Deletes one item of the RecyclerView after it is "long-clicked". Unfortunately it is not
    completely working and you see the item again, after returning to the route later.
    */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {


        if (extra1 == 1 && extra2 == 0 && extra3 == 0 ){

            viewHolder.elementDestination.setText(routePlanerEntityOne.get(position).getDestination());
            viewHolder.elementTransportation.setText(routePlanerEntityOne.get(position).getTransportation());

        }else if (extra2 == 2 && extra1 == 0 && extra3 == 0){

            viewHolder.elementDestination.setText(routePlanerEntityTwo.get(position).getDestination2());
            viewHolder.elementTransportation.setText(routePlanerEntityTwo.get(position).getTransportation2());

        }else if (extra3 == 3 && extra1 == 0 && extra2 == 0){

            viewHolder.elementDestination.setText(routePlanerEntityThree.get(position).getDestination3());
            viewHolder.elementTransportation.setText(routePlanerEntityThree.get(position).getTransportation3());

        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if (extra1 == 1 && extra2 == 0 && extra3 == 0 ){

                            SecondscreenRouteplaner.db.routeOneInterface().deleteSelectedItemFromRouteOne(position);

                        }else if (extra2 == 2 && extra1 == 0 && extra3 == 0){

                            SecondscreenRouteplaner.db.routeTwoInterface().deleteSelectedItemFromRouteTwo(position);

                        }else if (extra3 == 3 && extra1 == 0 && extra2 == 0){

                            SecondscreenRouteplaner.db.routeThreeInterface().deleteSelectedItemFromRouteThree(position);
                        }
                    }
                }).start();

                if (extra1 == 1 && extra2 == 0 && extra3 == 0 ){

                    SecondscreenRouteplaner.routePlanerEntityOne.remove(position);
                    notifyItemRemoved(position);

                }else if (extra2 == 2 && extra1 == 0 && extra3 == 0){

                    SecondscreenRouteplaner.routePlanerEntityTwo.remove(position);
                    notifyItemRemoved(position);

                }else if (extra3 == 3 && extra1 == 0 && extra2 == 0){

                    SecondscreenRouteplaner.routePlanerEntityThree.remove(position);
                    notifyItemRemoved(position);
                }

                return true;
            }
        });
    }


    //Sets the length of the RecyclerView
    @Override
    public int getItemCount() {

        if (extra1 == 1 && extra2 == 0 && extra3 == 0 ){

            return routePlanerEntityOne.size();

        }else if (extra2 == 2 && extra1 == 0 && extra3 == 0) {

            return routePlanerEntityTwo.size();

        }else if (extra3 == 3 && extra1 == 0 && extra2 == 0){

            return routePlanerEntityThree.size();
        }
        return getItemCount();
    }
}
