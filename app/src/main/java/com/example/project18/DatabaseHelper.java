package com.example.project18;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.project18.DatabaseContract;
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static  final String dataBase_name="database.db";
    private static  final int version=1;
    private static final String CREATE_TBL_USERS = "CREATE TABLE "
            + DatabaseContract.Users.Table_Name + " ("
            + DatabaseContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Users.Col_Uemail + " TEXT NOT NULL  UNIQUE,"
            + DatabaseContract.Users.Col_UFullName + " TEXT NOT NULL,"
            + DatabaseContract.Users.Col_Hint + " TEXT NOT NULL ,"
            + DatabaseContract.Users.Col_Password+ " TEXT NOT NULL )";
    private static final String CREATE_TBL_UserNotes = "CREATE TABLE "
            + DatabaseContract.UserNotes.TABLE_NOTES_NAME + " ("
            + DatabaseContract.UserNotes.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.UserNotes.COL_UserID + " INTEGER,"
            + DatabaseContract.UserNotes.COL_DATE + " TEXT NOT NULL, "
            + DatabaseContract.UserNotes.Col_TITLE + " TEXT,"
            + DatabaseContract.UserNotes.Col_NOTES+ " TEXT)";

    private static final String CREATE_TBL_REVIEW = "CREATE TABLE "
            + DatabaseContract.Review.Table_Name_REVIEW + " ("
            + DatabaseContract.Review._ID + " INTEGER PRIMARY KEY UNIQUE,"
            + DatabaseContract.Review.col_rate + " TEXT NOT NULL,"
            + DatabaseContract.Review.col_feedback+ " TEXT NOT NULL)";
    public DatabaseHelper(Context context)
    {
        super(context,dataBase_name,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TBL_USERS);
        db.execSQL(CREATE_TBL_UserNotes);
        db.execSQL(CREATE_TBL_REVIEW);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {}
}
