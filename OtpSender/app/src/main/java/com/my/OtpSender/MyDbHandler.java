package com.my.OtpSender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHandler extends SQLiteOpenHelper {


    Context con;

    public MyDbHandler(Context context) {
        super(context, FeedReaderContract.FeedEntry.DATABASE_NAME, null, FeedReaderContract.FeedEntry.DATABASE_VERSION);
        con=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FeedReaderContract.FeedEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertHistory(Msg m)
    {

        Temp.getMyDbHandler();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         ContentValues contentValues=new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN1_NAME,m.getName());
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN2_NAME,m.getDateTime());
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN3_NAME,m.getOtp());
        long rowid = sqLiteDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,contentValues);
        System.out.println("successfully Saved..............."+rowid);

    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+FeedReaderContract.FeedEntry.TABLE_NAME,null);
        return cursor;
    }
}
