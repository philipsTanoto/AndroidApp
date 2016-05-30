package com.example.myapp.helloworld;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.myapp.helloworld.data.Item;

import java.util.List;

public class ShoppingList extends ArrayAdapter<Item> {

    private List<Item> objects;
    private Context context;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShoppingList(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Item item = objects.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_listview, null);
        //ImageView image = (ImageView) view.findViewById(R.id.ivItemImage);
        //image.setImageResource(item.getImageResource());

        TextView tv = (TextView) view.findViewById(R.id.tvItemName);
        tv.setText(item.getItemName());

        return view;
    }
}
