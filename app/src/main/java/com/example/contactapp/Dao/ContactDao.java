package com.example.contactapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactapp.Models.ContactModel;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("select * from ContactModel")
    List<ContactModel> getContacts();

    @Insert
    void addContact(ContactModel contactModel);

    @Delete
    void deleteContact(ContactModel contactModel);
}
