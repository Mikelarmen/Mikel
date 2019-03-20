package com.example.rememberingreminding;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper helper = new DatabaseHelper(this);

    Button bRegister;
    EditText etName, etUsername, etPassword, etRepPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepPassword = (EditText) findViewById(R.id.etRepPassword);
        bRegister = (Button) findViewById(R.id.bResgister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bResgister:

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String repPassword = etRepPassword.getText().toString();

                if(!password.equals(repPassword)){
                    Toast pass = Toast.makeText(Register.this, "Password don´t match!", Toast.LENGTH_SHORT);
                    pass.show();
                }else{
                    User user = new User(name, username, password);
                    helper.insertUser(user);
                    startActivity(new Intent(this, Login.class));
                }

                break;
        }
    }
}
