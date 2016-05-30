package com.example.myapp.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ShoppingActivity extends AppCompatActivity {

//    Button addButton;
//    DBManager dbManager;
//    EditText itemname, quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        dbManager = new DBManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        itemname = (EditText) findViewById(R.id.editText);
//        quantity = (EditText) findViewById(R.id.editText2);
//        addButton = (Button) findViewById(R.id.addItemButton);

        final ShoppingFragment shoppingFragment = new ShoppingFragment();
        final AddItemFragment addItemFragment = new AddItemFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.shop, shoppingFragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .detach(shoppingFragment)
                        .add(R.id.shop, addItemFragment)
                        .commit();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }



}
