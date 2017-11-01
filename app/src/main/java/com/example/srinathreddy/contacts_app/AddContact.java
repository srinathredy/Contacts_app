package com.example.srinathreddy.contacts_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText name,phone,email,address;
    Db_action db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

         name = (EditText)findViewById(R.id.ins_name);
         phone = (EditText)findViewById(R.id.ins_phone);
         email = (EditText)findViewById(R.id.ins_email);
         address = (EditText)findViewById(R.id.ins_address);

         db = new Db_action(this);
    }

    public void insert_save(View view) {
        String Name = name.getText().toString();
        String Phone = phone.getText().toString();
        String Email = email.getText().toString();
        String Address = address.getText().toString();

        db.method(new Model(Name,Phone,Email,Address));

        Toast.makeText(this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();

        Intent return_home = new Intent(AddContact.this,MainActivity.class);
        startActivity(return_home);

    }
}
