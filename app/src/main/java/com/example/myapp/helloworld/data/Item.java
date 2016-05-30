package com.example.myapp.helloworld.data;

import android.os.Bundle;

/**
 * Created by Philips on 5/28/2016.
 */
public class Item {

    public static final String ITEM_NAME = "Item Name";
    public static final String QUANTITY = "Quantity";

    private String itemName;
    private int quantity;
    private int imageResource;

    public Item(String itemName, int quantity){
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Item(Bundle bundle){
        if(bundle != null){
            this.itemName = bundle.getString(ITEM_NAME);
        }
    }

    public String getItemName(){
        return itemName;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ITEM_NAME, this.itemName);

        return bundle;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getImageResource(){
        return imageResource;
    }

}
