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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by srinathreddy on 01/11/17.
 */

public class Custom_view_contact_name extends BaseAdapter {
    List<Model> list=null;
    Context cx;
    Db_action data;
    ArrayList<Model> arrayList;
    Model mod;
    public Custom_view_contact_name(MainActivity mainActivity, List<Model> list) {
        this.cx=mainActivity;
        this.list=list;
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(list);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Model getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater=(LayoutInflater)cx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.value,null);
        TextView tv =(TextView)convertView.findViewById(R.id.view_contact_name);
         mod = list.get(position);
        tv.setText(mod.getName());
        data = new Db_action(cx);

        return convertView;
    }


        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            list.clear();
            if (charText.length() == 0) {
                list.addAll(arrayList);
            } else {
                for (Model wp : arrayList) {
                    if (wp.getName().toLowerCase(Locale.getDefault())
                            .contains(charText)) {
                        list.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }

}
