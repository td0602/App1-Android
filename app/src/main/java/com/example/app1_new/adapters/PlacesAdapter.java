package com.example.app1_new.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1_new.R;
import com.example.app1_new.activities.PlacesActivity;
import com.example.app1_new.items.PlaceItemView;
import com.example.app1_new.models.Place;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlaceItemView> {
    private ArrayList<Place> placesData;
    private PlacesActivity placesActivity;

    //muc dich truyen tham chieu den view PlaceActivity qua Adapter sau do truyen xuong Placeitemview
    public void setPlacesActivity(PlacesActivity placesActivity) {
        this.placesActivity = placesActivity;
    }
    public PlacesAdapter(ArrayList<Place> placesData) {
        this.placesData = placesData;//this rất quan trọng
    }
    @NonNull
    @Override
    public PlaceItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Tao placeItemView voi giao dien place_item_view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item_view, viewGroup, false);
        PlaceItemView placeItemView = new PlaceItemView(view);
        // Adapter truyen placesActivity cho PlaceItemView
        placeItemView.setPlacesActivity(this.placesActivity);
        return placeItemView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceItemView placeItemView, int position) {
        //Anh xa data tung phan tu cua placesData voi tung placeItemView
        Place selectedPlace = placesData.get(position);
        String placeName = selectedPlace.getName();
        String placeDescription = selectedPlace.getDescription();
        placeItemView.txtPlaceName.setText(placeName);
        placeItemView.txtPlaceDescription.setText(placeDescription);
    }
    // lay tong so phan tu trong mang
    @Override
    public int getItemCount() {
        if(placesData != null) {
            return placesData.size();
        }
        return 0;
    }
}
