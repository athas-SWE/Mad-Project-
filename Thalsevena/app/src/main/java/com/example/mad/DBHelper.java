package com.example.mad;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "room_booking.db";
    public static final String TABLE_NAME = "Room_Reservation";
    public static final String COL_1 = "Room_type";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Phone_no";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Check_in";
    public static final String COL_6 = "CHeck_out";
    public static final String COL_7 = "No_of_rooms";
    public static final String COL_8 = "Total_cost";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(Room_type text,Name text, Phone_no text primary key, Email text,Check_in text, Check_out text, No_of_rooms integer, Total_cost integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String Room_type, String Name, String Phone_no, String Email, String Check_in, String Check_out, int No_of_rooms, int Total_cost) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Room_type);
        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Phone_no);
        contentValues.put(COL_4, Email);
        contentValues.put(COL_5, Check_in);
        contentValues.put(COL_6, Check_out);
        contentValues.put(COL_7, No_of_rooms);
        contentValues.put(COL_8, Total_cost);

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
    public  boolean updateDetail(String Room_type, String Name, String Phone_no, String Email, String Check_in, String Check_out, String No_of_rooms, String Total_cost){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1, Room_type);
        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Phone_no);
        contentValues.put(COL_4, Email);
        contentValues.put(COL_5, Check_in);
        contentValues.put(COL_6, Check_out);
        contentValues.put(COL_7, No_of_rooms);
        contentValues.put(COL_8, Total_cost);

        sqLiteDatabase.update(TABLE_NAME,contentValues,"Phone_no = ?",new String[] {Phone_no});
        return true;
    }
    public Integer deleteDetail (String Phone_no){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"Phone_no = ?",new String[] {Phone_no});
    }


}

