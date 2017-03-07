package com.whitehedge.ormpocgrubbrr.DatabaseContent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.whitehedge.ormpocgrubbrr.R;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by SandeepM on 7/18/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "Grubbrr.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Category,Integer> categoryDao;
    private Dao<Cuisine,Integer> cuisineDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Category.class);
            TableUtils.createTable(connectionSource,Cuisine.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Category.class,true);
            TableUtils.dropTable(connectionSource,Cuisine.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<Category,Integer> getCategoryDao() throws SQLException {
        if(categoryDao == null)
        {
            categoryDao = getDao(Category.class);
        }
        return categoryDao;
    }

    public Dao<Cuisine,Integer> getCuisineDao() throws SQLException {
        if(cuisineDao == null)
        {
            cuisineDao = getDao(Cuisine.class);
        }
        return cuisineDao;
    }

}
