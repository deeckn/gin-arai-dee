package com.gin_arai_dee;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

public class BillDialog extends DialogFragment {
//    private TextView food_name;
    private EditText food_price;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, del, clear, plus, minus, multiply, divide, equal, done;
    private String input = "", result;
    public static BillDialog newInstance(String title) {
        BillDialog frag = new BillDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

//    private void initial() {
//        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setLayout(
//                WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT
//        );
//        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_bill_food_dialog, container);
        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        food_name = (TextView)view.findViewById(R.id.food_name);
        food_price = view.findViewById(R.id.food_price);
        food_price.setShowSoftInputOnFocus(false);

//        if(!EventBus.getDefault().hasSubscriberForEvent(ListNameEvent.class)) {
//            EventBus.getDefault().register(this);
//            System.out.println("eventtttttt");
//        }
//        EventBus.getDefault().register(this);
//        ((TextView)view.findViewById(R.id.food_name)).setText("text");

//        System.out.println("set text");
        return view;
    }

//    @Subscribe
//    public void onEvent(ListNameEvent event) {
//        Log.d("Get List Name", "List name:" + event.getList_name());
//        food_name.setText(event.getList_name());
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        one = view.findViewById(R.id.one_button);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("1"); }
        });

        two = view.findViewById(R.id.two_button);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("2"); }
        });

        three = view.findViewById(R.id.three_button);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("3"); }
        });

        four = view.findViewById(R.id.four_button);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("4"); }
        });

        five = view.findViewById(R.id.five_button);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("5"); }
        });

        six = view.findViewById(R.id.six_button);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("6"); }
        });

        seven = view.findViewById(R.id.seven_button);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("7"); }
        });

        eight = view.findViewById(R.id.eight_button);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("8"); }
        });

        nine = view.findViewById(R.id.nine_button);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("9"); }
        });

        zero = view.findViewById(R.id.zero_button);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateNumber("0"); }
        });

        del = view.findViewById(R.id.delete_button);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                tmp = input.substring(0, input.length()-1);
                input = "";
                updateNumber(tmp);
            }
        });

        clear = view.findViewById(R.id.clear_button);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food_price.setText("");
                input = "";
            }
        });

        plus = view.findViewById(R.id.plus_button);
        minus = view.findViewById(R.id.minus_button);
        multiply = view.findViewById(R.id.multiply_button);
        divide = view.findViewById(R.id.divide_button);
        equal = view.findViewById(R.id.equal_button);
        done = view.findViewById(R.id.done_button);
//        initial();
    }

    private void updateNumber(String addedStr) {
        input += addedStr;
        System.out.println(input);
        food_price.setText(input);
    }
}
