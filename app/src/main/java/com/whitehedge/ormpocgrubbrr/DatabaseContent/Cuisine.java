package com.whitehedge.ormpocgrubbrr.DatabaseContent;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by SandeepM on 7/18/16.
 */
public class Cuisine {

    @DatabaseField(generatedId = true, columnName = "cuisine_id")
    public int cuisineID;

    @DatabaseField(columnName = "cuisine_name")
    public String cuisineName;

    public Cuisine() {
    }

    public Cuisine(String cuisineName, int cuisineID) {
        this.cuisineName = cuisineName;
        this.cuisineID = cuisineID;
    }

    public Cuisine(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public int getCuisineID() {
        return cuisineID;
    }

    public void setCuisineID(int cuisineID) {
        this.cuisineID = cuisineID;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "cuisineID=" + cuisineID +
                ", cuisineName='" + cuisineName + '\'' +
                '}';
    }
}
