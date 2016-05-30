package com.example.myapp.helloworld.data;

import android.content.Context;

import com.example.myapp.helloworld.DBManager;
import com.example.myapp.helloworld.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Philips on 5/28/2016.
 */
public class ItemData {

    private List<Item> items = new ArrayList<Item>();
    public List<Item> getItems(){
        return items;
    }
    DBManager dbManager;
    HashMap<String, Integer> hashMap;


    public ItemData(Context context){
        dbManager = new DBManager(context);
        hashMap = dbManager.getAllItems();
        Set keys = hashMap.keySet();
        for(Iterator i = keys.iterator(); i.hasNext();){
            String key = (String)i.next();
            int value = (int) hashMap.get(key);
            items.add(new Item(key, value));
        }
        items.add(new Item("Coke", 10));
    }
}
