package com.gin_arai_dee.diet_page;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.gin_arai_dee.R;
import com.gin_arai_dee.general.DatabaseHelper;
import com.gin_arai_dee.general.FoodItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DietDialog extends DialogFragment {

    public interface OnInputListener {
        void sentInput(String time, ArrayList<FoodItem> lists);

    }


    private static final String TAG = "DietDialog";
    private int MODE = 0;

    private Button timeButton;
    private EditText searchInput;
    private ListView itemList;
    private int hour, minute;
    private String time_selected;
    private ArrayList<FoodItem> foodItems;
    private ArrayList<FoodItem> selectedItems;
    private ArrayList<FoodItem> displayItem;
    private DialogFoodAdapter adapter;

    public OnInputListener inputListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_diet_page, container, false);

        DatabaseHelper db = new DatabaseHelper(getContext());
        Button saveButton = view.findViewById(R.id.diet_save_button);
        timeButton = view.findViewById(R.id.time_selected_button);
        searchInput = view.findViewById(R.id.dialog_search_bar);
        itemList = view.findViewById(R.id.dialog_item_list);

        foodItems = (ArrayList<FoodItem>) db.getAllFoodItems();
        selectedItems = new ArrayList<>();
        displayItem = foodItems;

        adapter = new DialogFoodAdapter(getContext(), R.layout.dialog_foodview, foodItems);
        itemList.setAdapter(adapter);

        // item list add or remove item
        itemList.setOnItemClickListener((adapterView, view1, position, id) -> {
            displayItem.get(position).setSelected(!displayItem.get(position).isSelected());
            if (displayItem.get(position).isSelected() && !selectedItems.contains(displayItem.get(position))) {
                selectedItems.add(displayItem.get(position));
            }
            if (!displayItem.get(position).isSelected()) {
                selectedItems.remove(displayItem.get(position));
            }
            filter(searchInput.getEditableText().toString());
        });

        // search input and filter text
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

        // save data and send input to parent activity
        saveButton.setOnClickListener(e -> {
            if (time_selected != null) {
                if (MODE == 0) {
                    inputListener.sentInput(time_selected, selectedItems);
                }

            }
            Objects.requireNonNull(getDialog()).dismiss();
        });

        // time selected and format string
        timeButton.setOnClickListener(e -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (timePicker, hh, mm) -> {
                hour = hh;
                minute = mm;
                time_selected = hour + ":" + minute;
                @SuppressLint("SimpleDateFormat") SimpleDateFormat f24Hour = new SimpleDateFormat("HH:mm");
                try {
                    Date date = f24Hour.parse(time_selected);
                    assert date != null;
                    timeButton.setText(f24Hour.format(date));
                } catch (ParseException e1) {
                    e1.printStackTrace();
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

    private void updateList(ArrayList<FoodItem> lists) {
        adapter = new DialogFoodAdapter(getContext(), R.layout.dialog_foodview, lists);
        itemList.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            inputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
