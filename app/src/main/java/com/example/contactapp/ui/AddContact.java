package com.example.contactapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactapp.Database.ContactDatabase;
import com.example.contactapp.Models.ContactModel;
import com.example.contactapp.R;

public class AddContact extends AppCompatActivity {
    EditText name , phone ;
    Button add_btn;
    String n ,p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initview();
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = name.getText().toString().trim();
                p = phone.getText().toString().trim();
                if (n.isEmpty() || p.isEmpty())
                    Toast.makeText(AddContact.this,"Check you data",Toast.LENGTH_SHORT).show();
                else
                    addContact(n,p);
            }
        });
    }


    protected void initview(){
        name = findViewById(R.id.contact_addName);
        phone = findViewById(R.id.contact_addNum);
        add_btn =findViewById(R.id.add_btn);
    }

    protected void addContact(String name , String num){

        ContactDatabase.getInstance(this).contactDao().addContact(new ContactModel(name ,num));
        startActivity(new Intent(AddContact.this,MainActivity.class));
        finish();
    }
}