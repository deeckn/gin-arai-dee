package com.gin_arai_dee;

import java.util.ArrayList;
import java.util.Arrays;

public class Food {
    private final int iD;
    private final int kCal;
    private final String name;
    private final String description;
    private ArrayList<String> category;
    private ArrayList<String> national;

    Food(){
        this.iD = 9999;
        this.name = "No Item";
        this.kCal = 0;
        this.description = "";
        this.category = new ArrayList<String>();
        this.national = new ArrayList<String>();
    }
    //Food(int iD){ DataBase    }

    Food(int iD,String name,int kCal){
        this.iD = iD;
        this.name = name;
        this.kCal = kCal;
        this.description = "";
        this.category = new ArrayList<>();
        this.national = new ArrayList<String>();
    }

    Food(int iD,String name,int kCal,String description){
        this.iD = iD;
        this.name = name;
        this.kCal = kCal;
        this.description = description;
        this.category = new ArrayList<>();
        this.national = new ArrayList<String>();
    }

    private void loadFoodFromID(int iD){
        //DataBase
    }

    private void setCategory(String category){
        try{
            this.category.addAll(Arrays.asList(category.split("/")));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNational(String national){
        try{
            this.national.addAll(Arrays.asList(national.split("/")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getID(){
        return iD;
    }

    public int getKCal() {
        return kCal;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return iD + "_" + name;
    }

}
