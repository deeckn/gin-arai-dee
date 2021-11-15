package com.gin_arai_dee.bill_splitter;

// Food for BillSplitter page
public class ListItem {
    private String name;
    private final int price;
    private final int perPerson;

    public ListItem(String name, int price, int perPerson) {
        this.name = name;
        this.price = price;
        this.perPerson = perPerson;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public int getPerPerson() { return perPerson; }
}
