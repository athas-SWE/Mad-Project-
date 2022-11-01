package com.example.mad;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1,b2;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pass);
        e3 = (EditText) findViewById(R.id.cpass);
        b1 = (Button) findViewById(R.id.register);
        b2 = (Button) findViewById(R.id.b4);
        db = new DatabaseHelper(this);



        awesomeValidation.addValidation(MainActivity.this,R.id.email, android.util.Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(MainActivity.this,R.id.pass,".{0,5}",R.string.invalid_password);
        awesomeValidation.addValidation(MainActivity.this,R.id.cpass,R.id.pass,R.string.invalid_confirmpassword);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Login.class);
                Toast.makeText(getApplicationContext(),"THALSEVENA LOGIN",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    String s3 = e3.getText().toString();

                    if (s1.equals(" ") || s2.equals(" ") || s3.equals(" ")) {
                        Toast.makeText(getApplicationContext(), "Field are empty", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if (s2.equals(s3)) {
                            Boolean chkemail = db.chkmail(s1);
                            if (chkemail == true) {
                                Boolean insert = db.insert(s1, s2);
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Registered Sucess", Toast.LENGTH_SHORT).show();
                                    clearControls();
                                    Intent login=new Intent( MainActivity.this,Login.class );
                                    startActivity( login );
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Email is already exits", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                }
            }

        });


    }
    private void clearControls(){
        e1.setText("");
        e2.setText("");
        e3.setText("");


    }



}