package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home4 extends AppCompatActivity {

    TextView linkTextView;
    public Button button1;
    public Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);



        button1 = (Button) findViewById(R.id.s_button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home4.this, Login.class);
                startActivity(intent);
            }
        });
        button2 = (Button) findViewById(R.id.S_button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home4.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
