package com.example.srinathreddy.contacts_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Edit_contact extends AppCompatActivity {
    EditText edt_name,edt_phone,edt_email,edt_address;
    int position;
    Db_action db;
    List<Model> viewContact;
    List<Model> update;
    Model upd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        edt_name = (EditText)findViewById(R.id.Edit_name);
        edt_phone = (EditText)findViewById(R.id.Edit_phone);
        edt_email = (EditText)findViewById(R.id.Edit_email);
        edt_address = (EditText)findViewById(R.id.Edit_address);

        Intent i = getIntent();
        position = i.getIntExtra("position", 0);

        db = new Db_action(this);
        viewContact = db.viewContact(position);
         upd = viewContact.get(0);

        edt_name.setText(upd.getName());
        edt_phone.setText(upd.getPhone());
        edt_email.setText(upd.getEmail());
        edt_address.setText(upd.getAddress());


    }

    public  void Update(View v){
        String name = edt_name.getText().toString();
        String phone = edt_phone.getText().toString();
        String email = edt_email.getText().toString();
        String address = edt_address.getText().toString();
        upd.setId(position);
        upd.setName(name);
        upd.setPhone(phone);
        upd.setEmail(email);
        upd.setAddress(address);

        db.update(upd);


        Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
         Intent goto_View_full_contact = new Intent(Edit_contact.this,View_full_contact.class);
        goto_View_full_contact.putExtra("position", position);
        startActivity(goto_View_full_contact);
        finish();


    }


}
