package com.example.srinathreddy.contacts_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Db_action db;
    ListView view_contact_name;
    //int itemPos;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this views contact name in home page
        view_contact_name = (ListView)findViewById(R.id.lv_view_contacts);
        db = new Db_action(this);
        dblistdata();


        //this is to use context menu
       //registerForContextMenu(view_contact_name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddContact = new Intent(MainActivity.this,AddContact.class);
                startActivity(AddContact);


            }
        });

        view_contact_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent view_full_contact = new Intent(MainActivity.this,View_full_contact.class);
                view_full_contact.putExtra("position",position+1);
                startActivity(view_full_contact);
            }
        });



       view_contact_name.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder action = new AlertDialog.Builder(MainActivity.this);
                action.setMessage("Do you want to delete contact?");
                action.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                db.deleteContact(position+1);
                                dblistdata();
                                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
                            }
                        });

                action.setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                action.show();




                return true;
            }
        });
    }
    // refresh  activity_main when activity is performed
    // dblistdata() method is used and here it retrives database each time when called
    private void dblistdata() {

        list= db.getdata();
        Custom_view_contact_name ref = new Custom_view_contact_name(this,list);
        view_contact_name.setAdapter(ref);
        ref.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
