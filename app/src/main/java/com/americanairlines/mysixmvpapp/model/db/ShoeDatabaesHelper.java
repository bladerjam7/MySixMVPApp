package com.americanairlines.mysixmvpapp.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.americanairlines.mysixmvpapp.R;
import com.americanairlines.mysixmvpapp.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeDatabaesHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shoe_db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "shoe_table";
    public static final String COLUMN_SHOE_ID = "shoe_id";
    public static final String COLUMN_SHOE_MODEL = "shoe_model";
    public static final String COLUMN_SHOE_PRICE = "shoe_price";
    public static final String COLUMN_SHOE_SIZE = "shoe_size";
    public static final String COLUMN_SHOE_IMAGE = "shoe_image";

    public Resources resources;

    public ShoeDatabaesHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        resources = context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_SHOE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SHOE_MODEL + " TEXT, "
                + COLUMN_SHOE_PRICE + " TEXT, "
                + COLUMN_SHOE_SIZE + " INTERGER, "
                + COLUMN_SHOE_IMAGE + " TEXT)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(update);
        onCreate(db);
        DATABASE_VERSION = newVersion;
    }

    public List<Shoe> getAllShoe(){

        // To get specific data
        // Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM + TABLE_NAME + " WHERE ... " + new String[]{COLUMN_MODEL, COLUMN_ID})

        Cursor cursor = getReadableDatabase().rawQuery(resources.getString(R.string.select_all_command, TABLE_NAME), null);

        List<Shoe> shoes = new ArrayList<>();
        cursor.move(-1);
        while(cursor.moveToNext()){
            String shoeModel = cursor.getString(cursor.getColumnIndex(COLUMN_SHOE_MODEL));
            String shoePrice = cursor.getString(cursor.getColumnIndex(COLUMN_SHOE_PRICE));
            int shoeSize = cursor.getInt(cursor.getColumnIndex(COLUMN_SHOE_SIZE));
            int shoeID = cursor.getInt(cursor.getColumnIndex(COLUMN_SHOE_ID));
            String shoeImage = cursor.getString(cursor.getColumnIndex(COLUMN_SHOE_IMAGE));

            shoes.add(new Shoe(shoeSize, shoeModel, shoeID, Double.parseDouble(shoePrice), shoeImage));
        }

        return shoes;
    }

    public void insertNewShoeIntoDatabase(Shoe shoe){
        ContentValues shoeValues = new ContentValues();
        shoeValues.put(COLUMN_SHOE_MODEL, shoe.getShoeModel());
        shoeValues.put(COLUMN_SHOE_PRICE, shoe.getShoePrice());
        shoeValues.put(COLUMN_SHOE_SIZE, shoe.getShoeSize());
        shoeValues.put(COLUMN_SHOE_IMAGE, shoe.getImageID());

        getWritableDatabase().insert(TABLE_NAME, null, shoeValues);
    }

    public void deleteShoeFromDatabase(Shoe shoe){
       // String deleteSql = "DELETE FROM " + TABLE_NAME + " " + COLUMN_SHOE_ID + " = " + shoe.getShoeID();

        //  <string name="delete_command">DELETE FROM %s WHERE %s = %d</string>
        String deleteSql = resources.getString(R.string.delete_command, TABLE_NAME, COLUMN_SHOE_ID, shoe.getShoeID());
        getWritableDatabase().execSQL(deleteSql);
    }
}
