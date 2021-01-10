package com.srj_enterprise.todo.data;

public class ColorData {

    private int color;
    private boolean isChecked;

    public int getColor() { return color; }
    public boolean isChecked() { return isChecked; }

    public void setColor(int color) { this.color = color; }
    public void setChecked(boolean isChecked) { this.isChecked = isChecked; }

    public ColorData(int color, boolean isChecked) {
        this.color = color;
        this.isChecked = isChecked;
    }
}