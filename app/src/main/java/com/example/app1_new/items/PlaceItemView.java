package com.example.app1_new.items;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app1_new.R;
import com.example.app1_new.activities.PlacesActivity;


public class PlaceItemView extends RecyclerView.ViewHolder {
    // lay data tu file xml
    public TextView txtPlaceName;
    public TextView txtPlaceDescription;
    private PlacesActivity placesActivity;

    public void setPlacesActivity(PlacesActivity placesActivity) {
        this.placesActivity = placesActivity;
    }
    public PlaceItemView(View view) {
        super(view);
        txtPlaceName = view.findViewById(R.id.txtPlaceName);
        txtPlaceDescription = view.findViewById(R.id.txtPlaceDescription);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hien thi lên cai placeItem da chon
                PlaceItemView.this.placesActivity.navigateToDetailPlace(getLayoutPosition());
            }
        });
    }
}
