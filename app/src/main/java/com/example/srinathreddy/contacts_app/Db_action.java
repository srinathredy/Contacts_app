package com.example.srinathreddy.contacts_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinathreddy on 01/11/17.
 */

public class Db_action extends SQLiteOpenHelper {
    private static final String DBNAME="MyContactsList";
    public static final String TB_CONTATCS="Contact";
    public static final String TB_DELETED_CONTACTS="DeletedContacts";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String PHONE="phone";
    public static final String Id="id";
    public static final String ADDRESS="Address";


    public Db_action(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Contact"+"(id integer primary key autoincrement,name text,phone text,email text, address text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CONTATCS);
        onCreate(db);
        onCreate(db);
    }

    //insert values
    public void method(Model model){
        SQLiteDatabase data = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,model.getName());
        cv.put(PHONE,model.getPhone());
        cv.put(EMAIL,model.getEmail());
        cv.put(ADDRESS,model.getAddress());

        data.insert(TB_CONTATCS,null,cv);

    }


    //View contacts

    public List<Model> getdata(){
        SQLiteDatabase database = getReadableDatabase();
        List<Model> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from Contact",null);
        if(cursor.moveToFirst()){
            do{
                Model obj = new Model();
                obj.setName(cursor.getString(1));
                list.add(obj);
            }while(cursor.moveToNext());
        }
        return list;
    }


    // full contact view
    //views in View_full_contact class
    public List<Model> viewContact(int position){
        String query="Select * from " + TB_CONTATCS + " WHERE " +Id+ " = ' " +position+ " ' ";
        SQLiteDatabase database = getReadableDatabase();
        List<Model> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Model obj = new Model();
                obj.setName(cursor.getString(1));
                obj.setPhone(cursor.getString(2));
                obj.setEmail(cursor.getString(3));
                obj.setAddress(cursor.getString(4));
                list.add(obj);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void deleteContact(int itempos){
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("insert into DeletedContacts select * from Contact where id = "+itempos);
    db.delete(TB_CONTATCS,Id + " = ? ",new String[]{String.valueOf(itempos)});
      //  database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + CONTACTS_COLUMN_TITLE + "= '" + title + "'");



    }




    public int update(Model model) {
        SQLiteDatabase data = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,model.getName());
        cv.put(PHONE,model.getPhone());
        cv.put(EMAIL,model.getEmail());
        cv.put(ADDRESS,model.getAddress());

        return   data.update(TB_CONTATCS,cv,Id+"=?",new String[]{String.valueOf(model.getId())});
    }
}
