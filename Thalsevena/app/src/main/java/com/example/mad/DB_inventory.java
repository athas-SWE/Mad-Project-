package com.example.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_inventory extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inventory_maneger.db";
    public static final String TABLE_NAME = "inventory";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "InventoryType";
    public static final String COL_3 = "NoOfInventory";
    public static final String COL_4 = "InventoryCost";
    public static final String COL_5 = "SupplierName";

    public DB_inventory( Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER primary key ,InventoryType text,NoOfInventory interger,InventoryCost interger,SupplierName text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String ID, String InventoryType, String NoOfInventory, String InventoryCost, String SupplierName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, InventoryType);
        contentValues.put(COL_3, NoOfInventory);
        contentValues.put(COL_4, InventoryCost);
        contentValues.put(COL_5, SupplierName);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public  boolean updateData(String ID,String InventoryType, String NoOfInventory, String InventoryCost, String SupplierName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COL_2, InventoryType);
        contentValues.put(COL_3, NoOfInventory);
        contentValues.put(COL_4, InventoryCost);
        contentValues.put(COL_5, SupplierName);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[] {ID});
        return true;
    }
    public Integer deleteData (String ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[]{ID});
    }
    public Cursor searchData(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + "= '" + ID + "'",null);
        return data;
    };

}
