package com.gin_arai_dee;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DietDialog extends DialogFragment {
    private static final String TAG = "DietDialog";
    private DatabaseHelper db;

    //widgets

    private Button saveButton;
    private Button timeButton;
    private EditText searchInput;
    private ListView itemList;


    private int hour,minute;
    private String time_selected;
    private ArrayList<FoodItem> selectedItem;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_diet_page, container, false);

        db = new DatabaseHelper(getContext());
        saveButton = view.findViewById(R.id.diet_save_button);
        timeButton = view.findViewById(R.id.time_selected_button);
        searchInput = view.findViewById(R.id.dialog_search_bar);
        itemList = view.findViewById(R.id.dialog_item_list);

        saveButton.setOnClickListener(e ->{
            Log.d(TAG,"onClick: saveButton dialog");
            getDialog().dismiss();
        });

        timeButton.setOnClickListener(e ->{
            Log.d(TAG,"onClick: timeButton dialog");
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour= i;
                    minute = i1;
                    time_selected = hour + ":" + minute;

                    SimpleDateFormat f24Hour = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = f24Hour.parse(time_selected);
                        timeButton.setText(f24Hour.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            },24,0,true);
            timePickerDialog.updateTime(hour,minute);
            timePickerDialog.show();
        });
        return view;
    }

}
