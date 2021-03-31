package com.example.contactapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.contactapp.Adapters.ContactAdapter;
import com.example.contactapp.Database.ContactDatabase;
import com.example.contactapp.Models.ContactModel;
import com.example.contactapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager ;
    ContactAdapter contactAdapter ;
    List<ContactModel> contactModel ;
    ImageButton btn_add ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add= findViewById(R.id.add_contact);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this ,AddContact.class));
                finish();
            }
        });

        getAllContacts();
    }

    protected void getAllContacts () {
        contactModel = ContactDatabase.getInstance(this).contactDao().getContacts();
        if (contactModel==null || contactModel.size()==0){
            Toast.makeText(MainActivity.this,"mafish",Toast.LENGTH_SHORT).show();
        }
        else
            install();

    }

    private void install() {
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        contactAdapter =  new ContactAdapter(contactModel,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

}