package com.example.contactapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contactapp.Dao.ContactDao;
import com.example.contactapp.Models.ContactModel;

@Database(entities ={ContactModel.class} ,version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
    private static ContactDatabase contactDatabase;
    private final static String DB_NAME = "contacts";

    public ContactDatabase() {
    }

    public static ContactDatabase getInstance(Context context){

        if (contactDatabase==null)
            contactDatabase= Room.databaseBuilder(context,ContactDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        return contactDatabase;
    }

}
