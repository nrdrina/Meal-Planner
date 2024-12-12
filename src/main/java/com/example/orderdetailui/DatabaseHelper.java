package com.example.orderdetailui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Information
    private static final String DATABASE_NAME = "menu.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Column Names
    private static final String TABLE_MENU = "menu";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_IS_FAVOURITE = "is_favourite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Table Query
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MENU + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_IMAGE + " TEXT, "
                + COLUMN_IS_FAVOURITE + " INTEGER DEFAULT 0)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(db);
    }

    // Insert a Menu Item
    public void insertMenuItem(String name, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_IS_FAVOURITE, 0); // Default to not favourite
        db.insert(TABLE_MENU, null, values);
        db.close();
    }

    // Update Favourite Status
    public void updateFavouriteStatus(int id, boolean isFavourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_FAVOURITE, isFavourite ? 1 : 0);
        db.update(TABLE_MENU, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Get All Menu Items
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);

        if (cursor.moveToFirst()) {
            do {
                // Use getColumnIndexOrThrow to ensure correct column retrieval
                MenuItem menuItem = new MenuItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVOURITE)) == 1
                );
                menuItems.add(menuItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return menuItems;
    }

    public MenuItem getMenuItemByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU + " WHERE " + COLUMN_NAME + " = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            MenuItem menuItem = new MenuItem(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVOURITE)) == 1
            );
            cursor.close();
            return menuItem;
        } else {
            cursor.close();
            return null; // Item not found
        }
    }


    // Get Favourite Items
    public List<MenuItem> getFavouriteItems() {
        List<MenuItem> favouriteItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU + " WHERE " + COLUMN_IS_FAVOURITE + " = 1", null);

        if (cursor.moveToFirst()) {
            do {
                // Use getColumnIndexOrThrow to ensure correct column retrieval
                MenuItem menuItem = new MenuItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVOURITE)) == 1
                );
                favouriteItems.add(menuItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favouriteItems;
    }
}
