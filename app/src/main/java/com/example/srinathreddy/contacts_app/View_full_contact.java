package com.example.srinathreddy.contacts_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class View_full_contact extends AppCompatActivity {
    int position;
    Db_action db;
    List<Model> viewContact;
    TextView tv_name, tv_phone, tv_email, tv_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_contact);

        tv_name = (TextView) findViewById(R.id.View_name);
        tv_phone = (TextView) findViewById(R.id.View_phone);
        tv_email = (TextView) findViewById(R.id.View_email);
        tv_address = (TextView) findViewById(R.id.View_address);


        Intent i = getIntent();
        position = i.getIntExtra("position", 0);

        db = new Db_action(this);
        viewContact = db.viewContact(position);
        Model ref = viewContact.get(0);


        tv_name.setText(ref.getName());
        tv_phone.setText(ref.getPhone());
        tv_email.setText(ref.getEmail());
        tv_address.setText(ref.getAddress());
        //Toast.makeText(this,ref.getName()+"\n"+ref.getAddress(),Toast.LENGTH_SHORT).show();


    }

    public void edit_contact(View editcontact){
        Intent edit_contact = new Intent(View_full_contact.this,Edit_contact.class);
        edit_contact.putExtra("position", position);
        startActivity(edit_contact);

    }


}

