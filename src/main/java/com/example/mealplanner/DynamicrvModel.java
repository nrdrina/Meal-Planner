package com.example.mealplanner;

public class DynamicrvModel {
    private String mealName;
    private String mealDescription;

    // ConstructorS
    public DynamicrvModel(String mealName, String mealDescription) {
        this.mealName = mealName;
        this.mealDescription = mealDescription;
    }

    // Getter for mealName
    public String getMealName() {
        return mealName;
    }

    // Getter for mealDescription (if needed)
    public String getMealDescription() {
        return mealDescription;
    }

    // Setter methods (if necessary)
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }
}
