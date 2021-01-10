package com.srj_enterprise.todo.data;

public class ListData {

    private int id;
    private String title;
    private int iconId;
    private int color;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getIconId() { return iconId; }
    public int getColor() { return color; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setIconId(int iconId) { this.iconId = iconId; }
    public void setColor(int color) { this.color = color; }

    public ListData(int id, String title, int iconId, int color) {
        this.id = id;
        this.title = title;
        this.iconId = iconId;
        this.color = color;
    }
}