package com.example.srinathreddy.contacts_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddContact extends AppCompatActivity {
    EditText name,phone,email,address;
    Db_action db;
    byte addImage[];
    ByteArrayOutputStream stream;
    Bitmap yourImage;
    private static final int CAMERA_ID = 1;
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

        yourImage.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte imageinByte[] = stream.toByteArray();

        db.method(new Model(Name,Phone,Email,Address, imageinByte));

        Toast.makeText(this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();

        Intent return_home = new Intent(AddContact.this,MainActivity.class);
        startActivity(return_home);

    }

    public void add_image(View v){
        Intent img = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(img, CAMERA_ID);
        img.setType("image/*");
        img.putExtra("crop","true");
        img.putExtra("aspectX",0);
        img.putExtra("aspectY",0);
        img.putExtra("outputX",250);
        img.putExtra("outputY",250);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= RESULT_OK)
            return;
            switch (requestCode){
                case CAMERA_ID:
                    Bundle extras = data.getExtras();
                    if(extras != null){
                        yourImage = extras.getParcelable("data");
                        stream = new ByteArrayOutputStream();
                    }
                    break;
            }
    }
}
