package com.example.orderdetailui;

public class MenuItem {
    private int id;
    private String name;
    private String imageResourceName;
    private boolean isFavourite;

    public MenuItem(int id, String name, String imageResourceName, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.imageResourceName = imageResourceName;
        this.isFavourite = isFavourite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageResourceName() {
        return imageResourceName;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
