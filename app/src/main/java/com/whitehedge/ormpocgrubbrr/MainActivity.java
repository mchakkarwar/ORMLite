package com.whitehedge.ormpocgrubbrr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.Category;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.Cuisine;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    DatabaseHelper dbHelper;
    private DatabaseHelper getDBHelper()
    {
        if(dbHelper == null)
        {
            dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return dbHelper;
    }

    public Cuisine getSelectedCuisine()
    {
        return new Cuisine("Test Cuisine");
    }




    public void handleAddCategory(View view) {
        Toast.makeText(MainActivity.this, "Adding Category", Toast.LENGTH_SHORT).show();
        //get Current Available Categories
        try {

            final Dao<Category,Integer> categoryDao = getDBHelper().getCategoryDao();

            List<Category> availableCategories = categoryDao.queryForAll();

            Log.d(".java"," No of Category Available are ::"+availableCategories.size());

            int noOfCategories = availableCategories.size() + 1;


            Cuisine cuisineObj = (Cuisine) searchRecordWithID(getDBHelper().getCuisineDao(),5);
            Log.d(".java","Cuisine record for ID= 5 is ::"+cuisineObj.toString());


            //Add new category with new values
            Category tempCategory = new Category();

            tempCategory.setCategoryName("Category"+noOfCategories);
            tempCategory.setMainCategory(true);
            tempCategory.setParentCategoryID(-99);
            tempCategory.setLeafCategory(false);
            tempCategory.setCuisine(cuisineObj);
            tempCategory.setImageURL("Image Url"+noOfCategories);

            categoryDao.create(tempCategory);

            Toast.makeText(MainActivity.this, "Record created", Toast.LENGTH_SHORT).show();


            getAllCategories();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(dbHelper != null)
        {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    public void getAllCategories()
    {
        //get Current Available Categories
        try {
            final Dao<Category,Integer> categoryDao = getDBHelper().getCategoryDao();

            List<Category> availableCategories = categoryDao.queryForAll();

            Log.d(".java"," No. of Category Available are ::"+availableCategories.size());

            for(int i = 0 ; i < availableCategories.size() ; i++)
            {
                Log.d(".java",i+":"+availableCategories.get(i).toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printList(List <?> collectionList)
    {
        Log.d(".java","printList ------ Start");
        Log.d(".java"," No. of rows Available are ::"+collectionList.size());

        for(int i = 0 ; i < collectionList.size() ; i++)
        {
            Log.d(".java",i+":"+collectionList.get(i).toString());
        }
        Log.d(".java","printList ------ End");
    }





    public void listAllCuisine()
    {
        try {
            List<Cuisine> cuisine = (List<Cuisine>) (List<?>) selectAllQuery(getDBHelper().getCuisineDao());
            printList(cuisine);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void handleGetAllCategory(View view) {

        try {
            List<Object> listOfAllCategories = selectAllQuery(getDBHelper().getCategoryDao());
            List<Category> categories = new ArrayList<Category>();

            for (int i = 0; i < listOfAllCategories.size(); i++) {
                categories.add((Category) listOfAllCategories.get(i));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handleAddCuisine(View view) {

        //get Current Available Categories
        try {
            final Dao<Cuisine,Integer> cuisineDao = getDBHelper().getCuisineDao();

            List<Cuisine> availableCuisines = cuisineDao.queryForAll();

            Log.d(".java"," No of Cuisine Available are ::"+availableCuisines.size());

            int noOfCuisine = availableCuisines.size() + 1;

            //Add new category with new values
            Cuisine tempCuisine = new Cuisine();
            tempCuisine.setCuisineName("Cuisine"+noOfCuisine);
            cuisineDao.create(tempCuisine);

            Toast.makeText(MainActivity.this, "Cuisine Record created", Toast.LENGTH_SHORT).show();


            listAllCuisine();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void handleGetAllCuisine(View view) {
        listAllCuisine();
    }


    public void handleSearchCuisine(View view) {

        //search record with ID
        try {

//            final Dao<Cuisine,Integer> cuisineDao = getDBHelper().getCuisineDao();
//            List<Cuisine> cuisine = cuisineDao.queryForEq("cuisine_name","Cuisine33");

            List<Cuisine> cuisine = (List<Cuisine>)(List<?>) searchForColumnValue(getDBHelper().getCuisineDao(),"cuisine_name","Cuisine33");

            Log.d(".java","Here the name search ::"+cuisine.size()+" Record ::"+cuisine.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    // ****************** GENERIC QUERIES ******************

    /**
     * Method to execute select All or select * from query
     * @param targetedDao
     * @return
     * return value must be type cast at the return value
     * e.g. List<CUSTOM_CLASS> custom_class_obj = (List<CUSTOM_CLASS>) (List<?>) selectAllQuery(getDBHelper().getCustomClassDao());
     */
    private List <Object> selectAllQuery(final Dao targetedDao)
    {
        List <Object> collectionList = new ArrayList<Object>();
        try {
            collectionList = targetedDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collectionList;
    }


    /**
     * Search record with a Id as a primary key input
     * @param targetDao
     * @param ID
     * @return Object, this return value should be typecast at the time of collection of the return value
     * e.g.CUSTOM_CLASS customClassObj = (CUSTOM_CLASS) searchRecordWithID(getDBHelper().getCustomClassDao(),ID);
     */
    private Object searchRecordWithID(final Dao targetDao , int ID)
    {
        Object object = null;

        //search record with ID
        try {
            object = targetDao.queryForId(ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Method to update any record from a table
     * @param targetedDao
     * @param objToUpdate
     * @return
     * true - Returns true when record gets successfully updated
     * false - Return false when update fail.
     */

    private boolean updateRecord(final Dao targetedDao, Object objToUpdate)
    {
        try
        {
            targetedDao.update(objToUpdate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Search for a column with specific equal value
     * @param targetDao : Dao of a table from which to extract details
     * @param columnName : Name of the column for which to search a value
     * @param targetValue : A value to be search as a equal contraint
     * @return : List of Objects which matches the column and it's value for search
     *  typecast to get values as per the table class
     *  e.g. List<CUSTOM_CLASS> CustomClass = (List<CUSTOM_CLASS>)(List<?>) searchForColumnValue(getDBHelper().getCustomDao(),"column_name","valueForEquality");

     */

    private List<Object> searchForColumnValue(final Dao targetDao, String columnName, String targetValue)
    {
        List<Object> objectsList = null;

        try {
            objectsList = targetDao.queryForEq(columnName,targetValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectsList;
    }


}
