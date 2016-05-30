package com.example.myapp.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philips on 5/25/2016.
 */
public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";

    public DBManager(Context context){
        super(context,DATABASE_NAME, null, 2); //context, string name, SQLiteDatabase.CursorFactory, int version)

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table users " +
                "(id integer primary key, username text,password text,email text)");
        db.execSQL("create table items " +
                "(id integer primary key, itemname text,quantity integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean insertUser  (String name, String password, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        db.insert("users", null, contentValues);
        return true;
    }

    public boolean insertItem  (String itemname, int quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemname", itemname);
        contentValues.put("quantity", quantity);
        db.insert("items", null, contentValues);
        return true;
    }

    public void createDefault(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("password", "admin");
        contentValues.put("email", "admin@admin.com");
        db.insert("users", null, contentValues);
        contentValues2.put("itemname", "coke");
        contentValues2.put("quantity", 10);
        db.insert("items", null, contentValues2);
    }

    public Boolean checkCredentials(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users where username = \""+username+"\"", null );
        if(res.getCount() == 0){
            return false;
        }else{
            res.moveToFirst();
            if(res.getString(res.getColumnIndex("password")).equals(password)){
                return true;
            }
        }
        return false;
    }


    public boolean updateUser (Integer id, String name, String password, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        db.update("users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteUser (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllUsers()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("username")));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public HashMap<String, Integer> getAllItems()
    {
        HashMap<String, Integer> hashMap = new HashMap<>();;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            hashMap.put(res.getString(res.getColumnIndex("itemname")), Integer.valueOf(res.getString(res.getColumnIndex("quantity"))));
            res.moveToNext();
        }
        res.close();
        return hashMap;
    }

    public ArrayList<String> getAllPassword()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("password")));
            res.moveToNext();
        }
        return array_list;
    }
}
