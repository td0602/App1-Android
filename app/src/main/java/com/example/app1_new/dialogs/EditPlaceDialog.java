package com.example.app1_new.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app1_new.R;
import com.example.app1_new.activities.DetailPlaceActivity;
import com.example.app1_new.activities.PlacesActivity;
import com.example.app1_new.models.Place;


public class EditPlaceDialog extends Dialog {
    private PlacesActivity placesActivity;
    private Place selectedPlace;
    // tham chieu toi DetailPlaceActivity de cap nhat gia tri moi nhat cua place
    private DetailPlaceActivity detailPlaceActivity;
    //UI
    private EditText txtPlaceName;
    private EditText txtPlaceDescription;
    private Button btnSave;
    public EditPlaceDialog(Context context, PlacesActivity placesActivity,
                           Place selectedPlace, int layoutResId,
                           DetailPlaceActivity detailPlaceActivity) {
        super(context);
        this.placesActivity = placesActivity;
        this.selectedPlace = selectedPlace;
        this.setContentView(layoutResId); // hien thi file xml theo id cua file xml
        this.detailPlaceActivity = detailPlaceActivity;
        setupUI();
    }

    private void setupUI() {
        txtPlaceName = (EditText) findViewById(R.id.txtPlaceName);
        txtPlaceDescription = (EditText) findViewById(R.id.txtDescription);
        btnSave = (Button) findViewById(R.id.btnSave);

        txtPlaceName.setText(selectedPlace.getName());
        txtPlaceDescription.setText(selectedPlace.getDescription());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlace.setName(txtPlaceName.getText().toString().trim());
                selectedPlace.setDescription(txtPlaceDescription.getText().toString().trim());
                EditPlaceDialog.this.placesActivity.updatePlace(selectedPlace);
                //khi luu gia tri moi thi phai hien thi ngay trong detail
                EditPlaceDialog.this.detailPlaceActivity.setSelectedPlace(selectedPlace);
                //khi nhan save thi dialog nay bien mat
                EditPlaceDialog.this.dismiss();
            }
        });
    }
}
