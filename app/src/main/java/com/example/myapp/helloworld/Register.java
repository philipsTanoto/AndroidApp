package com.example.myapp.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private DBManager mydb;
    private ArrayList arrayListU, arrayListP;
    Button register;
    EditText username,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        mydb = new DBManager(this);
        arrayListU = mydb.getAllUsers();
        arrayListP = mydb.getAllUsers();

        register = (Button) findViewById(R.id.registerPage);
        username = (EditText) findViewById(R.id.nameI);
        password = (EditText) findViewById(R.id.passwordI);
        email = (EditText) findViewById(R.id.emailI);
    }
    public void buttonClickHandler(View view) {
        if(username.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please enter your username or password or email" ,Toast.LENGTH_SHORT).show();
            return;
        } else{
            for(int i =0; i < arrayListU.size(); i++){
                if(arrayListU.get(i).toString().equals(username.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Username have already been registered" ,Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }
        mydb.insertUser(username.getText().toString(), password.getText().toString(), email.getText().toString());
        Toast.makeText(getApplicationContext(), "You have registered..taking back to login" ,Toast.LENGTH_SHORT).show();
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
//        setContentView(R.layout.login_layout);
    }
}
