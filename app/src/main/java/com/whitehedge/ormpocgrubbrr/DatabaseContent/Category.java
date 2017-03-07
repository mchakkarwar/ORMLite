package com.whitehedge.ormpocgrubbrr.DatabaseContent;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by SandeepM on 7/18/16.
 */

public class Category {

@DatabaseField(generatedId = true, columnName = "category_id")
public int categoryID;

@DatabaseField(columnName = "category_name")
public String categoryName;

@DatabaseField(columnName = "isMainCategory")
public  boolean isMainCategory;

@DatabaseField(columnName = "parentCategory_id")
public  int parentCategoryID;

@DatabaseField(columnName = "isLeafCategory")
public  boolean isLeafCategory;



@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
public  Cuisine cuisine;

@DatabaseField(columnName = "image_url")
public  String imageURL;


    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", isMainCategory=" + isMainCategory +
                ", parentCategoryID=" + parentCategoryID +
                ", isLeafCategory=" + isLeafCategory +
                ", cuisine=" + cuisine +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    public Category() {
    }

    public Category(String categoryName, boolean isMainCategory, int parentCategoryID, boolean isLeafCategory, Cuisine cuisine, String imageURL) {
        this.categoryName = categoryName;
        this.isMainCategory = isMainCategory;
        this.parentCategoryID = parentCategoryID;
        this.isLeafCategory = isLeafCategory;
        this.cuisine = cuisine;
        this.imageURL = imageURL;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isMainCategory() {
        return isMainCategory;
    }

    public void setMainCategory(boolean mainCategory) {
        isMainCategory = mainCategory;
    }

    public int getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public boolean isLeafCategory() {
        return isLeafCategory;
    }

    public void setLeafCategory(boolean leafCategory) {
        isLeafCategory = leafCategory;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
