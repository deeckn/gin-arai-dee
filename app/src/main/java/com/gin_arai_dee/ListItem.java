package com.gin_arai_dee;

public class ListItem {
    private String name;
    private int price;
    private int perPerson;

    public ListItem(String name, int price, int perPerson) {
        this.name = name;
        this.price = price;
        this.perPerson = perPerson;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getPerPerson() { return perPerson; }
    public void setPerPerson(int perPerson) { this.perPerson = perPerson; }
}
