package com.gin_arai_dee;

import android.widget.TextView;

public class Person {
    private String name;
    private int payment;
    private int name_textView;
    private int payment_textView;

    public Person(String name) {
        this.name = name;
        this.payment = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPayment() { return payment; }
    public void setPayment(int payment) { this.payment = payment; }

    public void setTextView(int nameId, int paymentId) {
        name_textView = nameId;
        payment_textView = paymentId;
    }
}
