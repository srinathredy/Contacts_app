package com.example.srinathreddy.contacts_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    int itemPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this views contact name in home page
        view_contact_name = (ListView)findViewById(R.id.lv_view_contacts);
        db = new Db_action(this);
        List<Model> list= db.getdata();
        Custom_view_contact_name ref = new Custom_view_contact_name(this,list);
        view_contact_name.setAdapter(ref);

        registerForContextMenu(view_contact_name);

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


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPos = info.position;
        menu.add(0, v.getId(), 0, "Delete");

        //groupId, itemId, order, title

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Delete"){
            db.deleteContact(itemPos);

            //deleteReport(itemPos);
            Toast.makeText(getApplicationContext(),""+itemPos+"",Toast.LENGTH_LONG).show();
            view_contact_name.invalidateViews();


        }
        else{
            return false;
        }
        return true;
    }

    private void deleteReport(int itemPos) {
        //delete code

    }


}
