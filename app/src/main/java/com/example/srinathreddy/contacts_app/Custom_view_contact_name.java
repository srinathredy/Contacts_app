package com.example.srinathreddy.contacts_app;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by srinathreddy on 01/11/17.
 */

public class Custom_view_contact_name extends BaseAdapter {
    List<Model> list;
    Context cx;
    Db_action data;
    public Custom_view_contact_name(MainActivity mainActivity, List<Model> list) {
        this.cx=mainActivity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)cx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.value,null);
        TextView tv =(TextView)convertView.findViewById(R.id.view_contact_name);
        final Model mod = list.get(position);
        tv.setText(mod.getName());
        data = new Db_action(cx);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteitem(mod);
                Toast.makeText(cx,"row deleted from database",Toast.LENGTH_LONG).show();

            }
        });
        return convertView;
    }
}
