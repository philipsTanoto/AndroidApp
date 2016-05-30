package com.example.myapp.helloworld;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapp.helloworld.data.Item;
import com.example.myapp.helloworld.data.ItemData;

import java.util.List;

/**
 * Created by Philips on 5/28/2016.
 */
public class ShoppingFragment extends ListFragment{
    Button addButton;
    DBManager dbManager;
    EditText itemname, quantity;
    public ShoppingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        dbManager = new DBManager(getContext());

        super.onCreate(savedInstanceState);
        ShoppingList adapter = new ShoppingList(getActivity(), R.layout.item_listview, new ItemData(getContext()).getItems());
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.add_item, container, false);
        itemname = (EditText) rootView.findViewById(R.id.editText);
        quantity = (EditText) rootView.findViewById(R.id.editText2);
        addButton = (Button) rootView.findViewById(R.id.addItemButton);
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Add Item: ",itemname.getText().toString() + " " + Integer.parseInt(quantity.getText().toString()));
                dbManager.insertItem(itemname.getText().toString(), Integer.parseInt(quantity.getText().toString()));
            }
        });
        //ImageButton imageButton = (ImageButton) view.findViewById(R.id.shopping_list);
        return view;
    }

}
