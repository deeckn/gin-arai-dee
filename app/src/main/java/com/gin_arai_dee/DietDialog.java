package com.gin_arai_dee;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DietDialog extends DialogFragment {
    private static final String TAG = "DietDialog";

    //widgets
    private EditText searchInput;
    private Button saveButton;
    private Button timeButton;

    //vars
    private DatabaseHelper db;
    private ArrayList<FoodItem> selectedItem;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_diet_page, container, false);
        searchInput = view.findViewById(R.id.dialog_search_bar);
        saveButton = view.findViewById(R.id.diet_save_button);
        timeButton = view.findViewById(R.id.time_selected);

        saveButton.setOnClickListener(e ->{
            Log.d(TAG,"onClick: saveButton dialog");
            getDialog().dismiss();
        });

        timeButton.setOnClickListener(e ->{
            Log.d(TAG,"onClick: timeButton dialog");
        });



        return view;
    }
}
