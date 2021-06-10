package com.example.EXC2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBControl extends SQLiteOpenHelper {

    public DBControl(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists teman");
        onCreate(sqLiteDatabase);
    }

    public void insertData(HashMap<String, String> queryValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", queryValues.get("nama"));
        values.put("telpon", queryValues.get("telpon"));
        sqLiteDatabase.insert("teman", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table teman(id integer primary key, nama text, telpon text)");
    }

    public ArrayList<HashMap<String, String>> getAllTeman() {
        ArrayList<HashMap<String, String>> listTeman = new ArrayList<HashMap<String, String>>();
        String selectQuery = "select * from teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("telpon", cursor.getString(2));
                listTeman.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTeman;
    }
    public void deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("teman", "id = ?",new String[] {id});
        sqLiteDatabase.close();
    }
    public void updateData(HashMap<String, String> queryValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama", queryValues.get("nama"));
        values.put("telpon", queryValues.get("telpon"));

        sqLiteDatabase.update("teman", values, "id = ?", new String[]{queryValues.get("id")});
        sqLiteDatabase.close();
    }

}