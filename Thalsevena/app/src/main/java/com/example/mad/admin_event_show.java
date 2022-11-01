package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class admin_event_show extends AppCompatActivity {

    EdbHelper mydB1;
    Button view,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_show);
        mydB1 = new EdbHelper( this );

        view = findViewById( R.id.button100 );
        view();

        logout = findViewById(R.id.log);
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent( admin_event_show.this, Login.class );
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
                        mydB1.getAllData();
                        Cursor res = mydB1.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("View is Empty !!!", "No Data Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Event Type :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Phone No :" + res.getString(2) + "\n");
                            buffer.append("Email :" + res.getString(3) + "\n");
                            buffer.append("Check In :" + res.getString(4) + "\n");
                            buffer.append("Check Out :" + res.getString(5) + "\n");
                            buffer.append("No of Events :" + res.getString(6) + "\n");
                            buffer.append("Total Cost :" + res.getString(7) + "\n\n");
                        }
                        showMessage("Events Details", buffer.toString());

                    }
                }
        );
    }

    private void showMessage(String events_details, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(events_details);
        builder.setMessage(toString);
        builder.show();
    }

}
