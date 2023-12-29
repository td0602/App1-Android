package com.example.app1_new.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.app1_new.R;
import com.example.app1_new.adapters.PlacesAdapter;
import com.example.app1_new.models.Place;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {

    private RecyclerView placesRecyclerview;
    // adapter la cau noi giua du lieu va giao dien cua list
    private RecyclerView.Adapter placesAdapter;
    private ArrayList<Place> placesData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        //lay du lieu tu Main Activity sang
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String email = extras.getString("email");
        }
        placesRecyclerview = findViewById(R.id.placesRecyclervew);

        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 11));
        placesData.add(new Place("ABC park", "An exciting park", 22));
        placesData.add(new Place("XY place", "An good park", 33));
        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 44));
        placesData.add(new Place("ABC park", "An exciting park", 55));
        placesData.add(new Place("XY place", "An good park", 66));
        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 77));
        placesData.add(new Place("ABC park", "An exciting park", 88));
        placesData.add(new Place("XY place", "An good park", 99));
        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 111));
        placesData.add(new Place("ABC park", "An exciting park", 222));
        placesData.add(new Place("XY place", "An good park", 333));
        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 333));
        placesData.add(new Place("ABC park", "An exciting park", 444));
        placesData.add(new Place("XY place", "An good park", 555));
        placesData.add(new Place("Hoan Kiem Lake", "This is a beautiful place", 666));
        placesData.add(new Place("ABC park", "An exciting park", 777));
        placesData.add(new Place("XY place", "An good park", 888));

        placesAdapter = new PlacesAdapter(placesData);
        //truyen doi tuong placeActivity o day xuong placeActivity cua placeAdapter
        ((PlacesAdapter) placesAdapter).setPlacesActivity(this);
        // linearlayout cho phep cuon len xuong VERTICAL, vuot sang ben HORIZONTAL
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        placesRecyclerview.setAdapter(placesAdapter);
        placesRecyclerview.setLayoutManager(layoutManager);
    }

    //Chuyen den man hinh nao khi click vao placeitem
    public void navigateToDetailPlace(Integer position) {
        Intent intent = new Intent(PlacesActivity.this, DetailPlaceActivity.class);
        //Lay ra place khi click chon
        Place place = placesData.get(position);
        intent.putExtra("selectedPlace", place);
        startActivity(intent); // show view
        // tham hieu man hinh nay xuong detailPlaceActivity
        DetailPlaceActivity.placesActivity = this; // tro den man hinh PlacesActivity
    }

    public void deletePlace(Integer placeId) {
        placesData.removeIf(place -> {
            return place.getPlaceId().equals(placeId);});
        // reload
        placesAdapter.notifyDataSetChanged();
    }

    public void updatePlace(Place placeUpdate) {
        // duyet tung doi tuong trong placesData
        placesData.forEach(place -> {
            if(place.getPlaceId().equals(placeUpdate.getPlaceId())) {
                place.setName(placeUpdate.getName());
                place.setDescription(placeUpdate.getDescription());
            }
        });
    }
}