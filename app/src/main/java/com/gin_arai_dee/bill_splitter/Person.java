package com.gin_arai_dee.bill_splitter;

// Person in BillSplitter page
public class Person {
    private String name;
    private int payment;

    public Person(String name) {
        this.name = name;
        this.payment = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPayment() { return payment; }
    public void setPayment(int payment) { this.payment = payment; }
    public void updatePayment(int perPerson) { this.payment += perPerson; }
}
