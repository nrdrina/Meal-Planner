package com.example.orderdetailui;
import java.util.ArrayList;
import java.util.List;

public class FoodItem {
    private String name;
    private int imageResId;
    private boolean isFavourite;

    public FoodItem(String name, int imageResId, boolean isFavourite) {
        this.name = name;
        this.imageResId = imageResId;
        this.isFavourite = isFavourite;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
