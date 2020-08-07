package com.example.project18;

public class NotesUser {
    String date;
    String title;
    String notes;
    long id;
    NotesUser(String d, String t, String n,long i)
    {
        date=d;
        title=t;
        id=i;
        notes=n;
    }
    String getDate()
    {
        return date;
    }
    String getTitle()
    {
        return title;
    }
    String getNotes()
    {
        return notes;
    }
   long getNotesId()
    {
        return id;
    }
}
