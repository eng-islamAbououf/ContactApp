package com.example.contactapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.Database.ContactDatabase;
import com.example.contactapp.Database.ContactDatabase_Impl;
import com.example.contactapp.Models.ContactModel;
import com.example.contactapp.R;
import com.example.contactapp.ui.MainActivity;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    List<ContactModel> contacts ;
     Context context ;
    public ContactAdapter(List<ContactModel> contacts , Context context) {
        this.contacts = contacts;
        this.context = context ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ContactModel contactModel = contacts.get(position);
        if (contactModel!= null)
        {
            holder.phone.setText(contactModel.getNumber());
            holder.name.setText(contactModel.getName());
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteContact(contactModel);
                    Toast.makeText(context,"Contact deleted",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , phone ;
        ImageButton delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_number);
            delete = itemView.findViewById(R.id.delete_contact);
        }
    }
    protected void deleteContact(ContactModel contactModel){
        ContactDatabase.getInstance(context).contactDao().deleteContact(contactModel);
        refresh();

    }
    protected void refresh(){
        List<ContactModel> contactModels ;
        contactModels = ContactDatabase.getInstance(context).contactDao().getContacts();
        updateData(contactModels);
    }

    private void updateData(List<ContactModel> contactModels) {
        contacts = contactModels ;
        notifyDataSetChanged();
    }
}
