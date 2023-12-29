package com.example.app1_new.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app1_new.R;
import com.example.app1_new.dialogs.EditPlaceDialog;
import com.example.app1_new.models.Place;

public class DetailPlaceActivity extends Activity {

    //tham chieu toi man hinh trc do
    public static PlacesActivity placesActivity;
    private Place selectedPlace;
    private TextView txtPlaceNameDetail;
    private TextView txtPlaceDescriptionDetail;
    private Button btnDelete;

    public void setSelectedPlace(Place selectedPlace) {
        this.selectedPlace = selectedPlace;
        txtPlaceNameDetail.setText(selectedPlace.getName());
        txtPlaceDescriptionDetail.setText(selectedPlace.getDescription());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        //lay ra doi tuong ma cho khac truyen vao
        selectedPlace = (Place) getIntent().getExtras().getSerializable("selectedPlace");

        txtPlaceNameDetail = (TextView) findViewById(R.id.txtPlaceNameDetail);
        txtPlaceDescriptionDetail = (TextView) findViewById(R.id.txtDescriptionDetail);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtPlaceNameDetail.setText(selectedPlace.getName());
        txtPlaceDescriptionDetail.setText(selectedPlace.getDescription());
        setupActions();
    }

    //khi bam vao ten hoac description cua place
    private void actionUpdatePlace() {
        //show Edit Dialog
        EditPlaceDialog editPlaceDialog = new EditPlaceDialog(this, DetailPlaceActivity.placesActivity,
                selectedPlace, R.layout.edit_place, this);
        editPlaceDialog.show();
    }

    private void setupActions() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailPlaceActivity.this)
                        .setTitle("Delete a place")
                        .setMessage("Are you sure want to delete this?")
                        .setIcon(android.R.drawable.ic_dialog_alert) // bieu tuong icon
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DetailPlaceActivity.this.placesActivity.deletePlace(selectedPlace.getPlaceId());
                                // quay tro ve man hinh truoc do
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish(); //quay tro lai man hinh trc do
                            }
                        })
                        .show();
            }
        });

        txtPlaceNameDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionUpdatePlace();
            }
        });

        txtPlaceDescriptionDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionUpdatePlace();
            }
        });
    }
}