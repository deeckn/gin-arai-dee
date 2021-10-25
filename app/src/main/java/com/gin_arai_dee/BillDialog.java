package com.gin_arai_dee;

import android.os.Bundle;
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

import java.util.Objects;

public class BillDialog extends DialogFragment {
//    private TextView food_price;
//    private Button one, two, three, four, five, six, seven, eight, nine, zero, del, clear, plus, minus, multiply, divide, equal, done;
//    private String input, result;
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
//        ((TextView)view.findViewById(R.id.food_name)).setText("text");
//        System.out.println("set text");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        food_price = getActivity().findViewById(R.id.food_price);
//        one = getActivity().findViewById(R.id.one_button);
//        two = getActivity().findViewById(R.id.two_button);
//        three = getActivity().findViewById(R.id.three_button);
//        four = getActivity().findViewById(R.id.four_button);
//        five = getActivity().findViewById(R.id.five_button);
//        six = getActivity().findViewById(R.id.six_button);
//        seven = getActivity().findViewById(R.id.seven_button);
//        eight = getActivity().findViewById(R.id.eight_button);
//        nine = getActivity().findViewById(R.id.nine_button);
//        zero = getActivity().findViewById(R.id.zero_button);
//        del = getActivity().findViewById(R.id.delete_button);
//        clear = getActivity().findViewById(R.id.clear_button);
//        plus = getActivity().findViewById(R.id.plus_button);
//        minus = getActivity().findViewById(R.id.minus_button);
//        multiply = getActivity().findViewById(R.id.multiply_button);
//        divide = getActivity().findViewById(R.id.divide_button);
//        equal = getActivity().findViewById(R.id.equal_button);
//        done = getActivity().findViewById(R.id.done_button);
//        initial();
    }

//    public void buttonClick(View view) {
//        Button button = (Button) view;
//        String val = button.getText().toString();
//        switch (val) {
//            case "@string/clear":
//                input = "";
//                System.out.println("AC");
//                break;
//            case "@string/equal":
//                input += result;
//                System.out.println("=");
//                break;
//        }
//    }
}
