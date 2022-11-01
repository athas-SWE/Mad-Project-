package com.example.mad;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Admin_booking extends AppCompatActivity {
    DBHelper mydB;
    Button view,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_booking );
        mydB = new DBHelper( this );

        logout = findViewById(R.id.log);
        view = findViewById( R.id.button5 );
        view();
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent( Admin_booking.this, Login.class );
                startActivity( logout );
                finish();
                Toast.makeText( getApplicationContext(), "LOGOUT", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    public void view() {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mydB.getAllData();
                        Cursor res = mydB.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("View is Empty !!!", "No Data Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Room Type :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Phone No :" + res.getString(2) + "\n");
                            buffer.append("Email :" + res.getString(3) + "\n");
                            buffer.append("Check In :" + res.getString(4) + "\n");
                            buffer.append("Check Out :" + res.getString(5) + "\n");
                            buffer.append("No of Rooms :" + res.getString(6) + "\n");
                            buffer.append("Total Cost :" + res.getString(7) + "\n\n");
                        }
                        showMessage("Rooms Details", buffer.toString());

                    }
                }
        );
    }

    private void showMessage(String rooms_details, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(rooms_details);
        builder.setMessage(toString);
        builder.show();
    }




}