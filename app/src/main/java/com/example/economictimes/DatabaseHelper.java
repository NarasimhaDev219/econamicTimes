package com.example.economictimes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Economic.db";
    public static final String TABLE_NAME = "details";
    public static final String ID = "id";
    public static final String NAME ="name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table details " +
                "(id integer primary key autoincrement,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists details");
        onCreate(db);

    }
    public void addData(ModelClass model){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,model.getId());
        values.put( NAME,model.getText_url());
        System.out.println("=====++==========3"+model.getId());
        System.out.println("=====++==========4"+model.getText_url());
        database.insert(TABLE_NAME,null,values);
        System.out.println("=====++==========5"+database);
        database.close();
    }

    public List<ModelClass> getAllInfo() {
        List<ModelClass> data = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                ModelClass model = new ModelClass();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setText_url(cursor.getString(1));
                System.out.println("=====++==========7"+cursor.getString(1));
                System.out.println("=====++==========8"+cursor.getInt(0));
                data.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return data;
    }
}
