package com.gin_arai_dee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Objects;

public class BillDialog extends DialogFragment {
    private LinearLayout personList;
    private ToggleButton selectAll;
    private CheckBox personBox;
    private TextView foodName;
    private EditText foodPrice;
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private Button del, clear, plus, minus, multiply, divide, equal, done;
    private boolean isPlus, isMinus, isDivide, isMultiply;
    private String input = "";
    private int result = 0, numPerson = 0, perPerson = 0;
    private ArrayList<CheckBox> checked = new ArrayList<CheckBox>();
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
        final View view = inflater.inflate(
                R.layout.activity_bill_food_dialog, container, false
        );
        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        BillSplitterPage bill = (BillSplitterPage) getActivity();

        String name = Objects.requireNonNull(bill).getFoodName();
        foodName = view.findViewById(R.id.food_name);
        foodName.setText(name);

        foodPrice = view.findViewById(R.id.food_price);
        foodPrice.setShowSoftInputOnFocus(false);

        ArrayList<Person> people = bill.getPeople();
        personList = view.findViewById(R.id.person_list);
        for(Person p : people) {
            personBox = new CheckBox(this.getContext());
            checked.add(personBox);
            personBox.setText(p.getName());
            personBox.setTypeface(getResources().getFont(R.font.rubik));
            personBox.setTextColor(getResources().getColor(R.color.ghost_white));
            personBox.setTextSize(20);
            personList.addView(personBox);
        }

        selectAll = view.findViewById(R.id.select_all);
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < checked.size(); i++) {
                    if(selectAll.isChecked()) {
                        selectAll.setTextColor(getResources().getColor(R.color.ghost_white));
                        checked.get(i).setChecked(true);
                    }
                    else {
                        selectAll.setTextColor(getResources().getColor(R.color.charcoal));
                        checked.get(i).setChecked(false);
                    }
                }
            }
        });

//        EventBus.getDefault().register(this);
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
                foodPrice.setText("");
                input = "";
                result = 0;
            }
        });

        plus = view.findViewById(R.id.plus_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mathOp("+"); }
        });

        minus = view.findViewById(R.id.minus_button);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mathOp("-"); }
        });

        multiply = view.findViewById(R.id.multiply_button);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mathOp("×"); }
        });

        divide = view.findViewById(R.id.divide_button);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mathOp("÷"); }
        });

        equal = view.findViewById(R.id.equal_button);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus || isMinus || isMultiply || isDivide) {
                    if (isPlus) {
                        result += Integer.parseInt(input);
                        isPlus = false;
                    }
                    if (isMinus) {
                        result -= Integer.parseInt(input);
                        isMinus = false;
                    }
                    if (isMultiply) {
                        result *= Integer.parseInt(input);
                        isMultiply = false;
                    }
                    if (isDivide) {
                        result /= Integer.parseInt(input);
                        isDivide = false;
                    }
                }
                else { result = Integer.parseInt(input); }
                foodPrice.setText(String.valueOf(result));
                input = String.valueOf(result);
            }
        });

        done = view.findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!equal.isPressed()) result = Integer.parseInt(input);
                for (int i = 0; i < checked.size(); i++) {
                    if (checked.get(i).isChecked()) numPerson++;
//                    System.out.println("num" + numPerson);
//                    System.out.println("result" + result);
                }

                if (numPerson > 0 && result > 0) {
                    perPerson = result/numPerson;
//                    System.out.println("person" + perPerson);
                }
            }
        });
//        initial();
    }

    private void updateNumber(String addedStr) {
        input += addedStr;
        foodPrice.setText(input);
    }

    private void mathOp(String op) {
        switch (op) {
            case "+":
                isPlus = true;
                break;
            case "-":
                isMinus = true;
                break;
            case "×":
                isMultiply = true;
                break;
            case "÷":
                isDivide = true;
                break;
        }
        foodPrice.setText("");
        result = Integer.parseInt(input);
        input = "";
    }
}
