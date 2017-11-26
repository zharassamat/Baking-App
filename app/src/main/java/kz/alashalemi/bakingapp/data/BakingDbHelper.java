package kz.alashalemi.bakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import kz.alashalemi.bakingapp.data.BakingContract.BakingEntry;

/**
 * Created by zharas on 11/20/17.
 */

public class BakingDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "baking.db";

    private static final int DATABASE_VERSION = 1;

    public BakingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FOOD_TABLE =

                "CREATE TABLE " + BakingEntry.TABLE_FOOD + " (" +

                        BakingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        BakingEntry.COLUMN_FOOD_ID + " INTEGER NOT NULL, " +
                        BakingEntry.COLUMN_FOOD_NAME + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_FOOD_IMAGE + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_FOOD_SERVINGS + " REAL NOT NULL, " +

                        " UNIQUE (" + BakingEntry.COLUMN_FOOD_ID + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_INGREDIENT_TABLE =

                "CREATE TABLE " + BakingEntry.TABLE_INGREDIENT + " (" +

                        BakingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        BakingEntry.COLUMN_INGREDIENT_QUANTITY + " NUMERIC NOT NULL, " +
                        BakingEntry.COLUMN_INGREDIENT_INGREDIENT + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_INGREDIENT_MEASURE + " REAL NOT NULL);";


        final String SQL_CREATE_STEP_TABLE =

                "CREATE TABLE " + BakingEntry.TABLE_STEP + " (" +

                        BakingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        BakingEntry.COLUMN_STEP_STEP + " INTEGER NOT NULL, " +
                        BakingEntry.COLUMN_STEP_DESCRIPTION + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_STEP_SHORT_DESCRIPTION + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_STEP_THUMBNAIL + " REAL NOT NULL, " +
                        BakingEntry.COLUMN_STEP_VIDEO + " REAL NOT NULL, " +

                        " UNIQUE (" + BakingEntry.COLUMN_STEP_STEP + ") ON CONFLICT REPLACE);";


        db.execSQL(SQL_CREATE_FOOD_TABLE);
        db.execSQL(SQL_CREATE_INGREDIENT_TABLE);
        db.execSQL(SQL_CREATE_STEP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingEntry.TABLE_FOOD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingEntry.TABLE_INGREDIENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingEntry.TABLE_STEP);
        onCreate(sqLiteDatabase);
    }
}
