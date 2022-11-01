package com.example.mad;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    Button logout,room,emp,book,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );
        logout = findViewById( R.id.log );
        room = findViewById( R.id.empa );
        emp = findViewById( R.id.emp );
        book = findViewById( R.id.book );
        b3=findViewById(R.id.b3);



        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent( Admin.this, Login.class );
                startActivity( logout );
                finish();
                Toast.makeText( getApplicationContext(), "LOGOUT", Toast.LENGTH_SHORT ).show();
            }
        } );
        room.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent room = new Intent( Admin.this, Addinventory.class );
                startActivity( room );
            }
        } );
        emp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employee = new Intent( Admin.this, AD_Employee.class );
                startActivity( employee );
            }
        } );

        book.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employee = new Intent( Admin.this, Admin_booking.class );
                startActivity( employee );


            }
        } );
        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bn = new Intent( Admin.this, admin_event_show.class );
                startActivity( bn );


            }
        } );

    }
}
