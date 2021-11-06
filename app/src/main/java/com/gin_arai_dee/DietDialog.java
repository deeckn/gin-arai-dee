package com.gin_arai_dee;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DietDialog extends DialogFragment {

    public interface OnInputListener{
        void sentInput(String time,ArrayList<FoodItem> lists);
    }


    private static final String TAG = "DietDialog";
    private DatabaseHelper db;

    //widgets

    private Button saveButton;
    private Button timeButton;
    private EditText searchInput;
    private ListView itemList;

    DialogFoodAdapter adapter;
    private int hour, minute;
    private String time_selected;
    private ArrayList<FoodItem> foodItems;
    private ArrayList<FoodItem> selectedItems;
    private ArrayList<FoodItem> displayItem;

    public OnInputListener inputListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_diet_page, container, false);

        db = new DatabaseHelper(getContext());
        saveButton = view.findViewById(R.id.diet_save_button);
        timeButton = view.findViewById(R.id.time_selected_button);
        searchInput = view.findViewById(R.id.dialog_search_bar);
        itemList = view.findViewById(R.id.dialog_item_list);

        foodItems = (ArrayList<FoodItem>) db.getAllFoodItems();
        displayItem = foodItems;

        adapter = new DialogFoodAdapter(getContext(), R.layout.dialog_foodview, foodItems);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                displayItem.get(position).setSelected(!displayItem.get(position).isSelected());
                filter(searchInput.getEditableText().toString());
            }
        });

        // search input
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


        saveButton.setOnClickListener(e -> {
            Log.d(TAG, "onClick: saveButton dialog");


            getDialog().dismiss();
        });

        timeButton.setOnClickListener(e -> {
            Log.d(TAG, "onClick: timeButton dialog");
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour = i;
                    minute = i1;
                    time_selected = hour + ":" + minute;

                    SimpleDateFormat f24Hour = new SimpleDateFormat("HH:mm");
                    try {
                        Date date = f24Hour.parse(time_selected);
                        timeButton.setText(f24Hour.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, 24, 0, true);
            timePickerDialog.updateTime(hour, minute);
            timePickerDialog.show();
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<FoodItem> filterList = new ArrayList<>();

        for (FoodItem item : foodItems) {
            if (item.getFood_item().toLowerCase().contains(text.toLowerCase()) || item.isSelected()) {
                filterList.add(item);
            }
        }
        displayItem = filterList;
        updateList(filterList);

    }

    private void updateList(ArrayList<FoodItem> lists){
        adapter = new DialogFoodAdapter(getContext(), R.layout.dialog_foodview, lists);
        itemList.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            inputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG,"onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
