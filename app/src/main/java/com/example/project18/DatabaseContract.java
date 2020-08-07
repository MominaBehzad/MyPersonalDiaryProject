package com.example.project18;

import android.provider.BaseColumns;

public final class DatabaseContract
{
    public DatabaseContract(){}
    public static abstract class Users implements BaseColumns
    {
        public static final String Table_Name="Users";
        public static final String Col_UFullName="UserFullName";
        public static final String Col_Uemail="Email";
        public static final String Col_Password="Password";

        public static final String Col_Hint="Hint";
    }
    public static abstract class UserNotes implements BaseColumns {
        public static final String TABLE_NOTES_NAME="UserNotes";
        public static final String COL_ID="notesid";
        public static final String COL_UserID="UserID";
        public static final String COL_DATE="date";
        public static final String Col_TITLE="title";
        public static final String Col_NOTES="notes";
    }
    public static abstract class Review implements  BaseColumns
    {
        public static final String Table_Name_REVIEW="review";
        public static final String col_rate="rate";
        public static final String col_feedback="feedback";
    }
}
