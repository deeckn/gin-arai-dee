package com.gin_arai_dee.diet_page;

public class DietBuffer {
    private final String time;
    private final int iD;

    public DietBuffer(String time, int iD) {
        this.time = time;
        this.iD = iD;
    }

    public String getTime() {
        return time;
    }

    public int getID() {
        return iD;
    }
}
