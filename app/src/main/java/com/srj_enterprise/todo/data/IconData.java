package com.srj_enterprise.todo.data;

public class IconData {

    private int imageId;
    private boolean isSelected;

    public int getImageId() { return imageId; }
    public boolean isSelected() { return isSelected; }

    public void setImageId(int imageId) { this.imageId = imageId; }
    public void setSelected(boolean isSelected) { this.isSelected = isSelected; }

    public IconData(int imageId, boolean isSelected) {
        this.imageId = imageId;
        this.isSelected = isSelected;
    }
}