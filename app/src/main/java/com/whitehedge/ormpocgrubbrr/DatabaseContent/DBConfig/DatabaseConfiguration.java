package com.whitehedge.ormpocgrubbrr.DatabaseContent.DBConfig;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.Category;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.Cuisine;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.StudentDetails;
import com.whitehedge.ormpocgrubbrr.DatabaseContent.TeacherDetails;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by SandeepM on 7/18/16.
 */
public class DatabaseConfiguration extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {Cuisine.class,Category.class, TeacherDetails.class, StudentDetails.class};

    public static void main(String args[]) throws IOException, SQLException {
        System.out.println("Here in Main");
        writeConfigFile("ormlite_config.txt",classes);
        System.out.println("After writing Config");
    }
}
