package com.example.mad;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;

import java.lang.reflect.Type;


public class AD_Employee extends AppCompatActivity {

    DB_Employee mydb;
    Button logout,search;
    Spinner spinner1;
    EditText m1,m2,m3,m4,m5,m6,m7;
    Button b1,b2,b3,b4;
    String[] Type = {"Manager", "Staff","Workers"};
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d__employee);
        logout=findViewById(R.id.log);
        mydb = new DB_Employee(this);
        m1=(EditText)findViewById(R.id.emp1);
        m2=(EditText)findViewById(R.id.emp2);
        m3=(EditText)findViewById(R.id.emp3);
        m4=(EditText)findViewById(R.id.emp4);
        m5=(EditText)findViewById(R.id.emp5);
        m6=(EditText)findViewById(R.id.emp6);
        m7=(EditText)findViewById(R.id.emp8);
        b1=(Button)findViewById(R.id.btn2);
        b2=(Button)findViewById(R.id.btn1);
        b3=(Button)findViewById(R.id.btn3);
        b4=(Button)findViewById(R.id.btn4);
        search=(Button)findViewById(R.id.btn5);
        AddData();
        UpdateDetail();
        DeleteDetail();
        ViewDetail();
        SearchData();
        awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.emp1, RegexTemplate.NOT_EMPTY, R.string.ivalid_Id);
        awesomeValidation.addValidation(this, R.id.emp2, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.emp3, RegexTemplate.NOT_EMPTY, R.string.ivalid_address);
        awesomeValidation.addValidation(this,R.id.emp4,".{10}",R.string.invalid_phoneno);
        awesomeValidation.addValidation(this, R.id.emp5, Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        awesomeValidation.addValidation(this, R.id.emp6, RegexTemplate.NOT_EMPTY, R.string.ivalid_position);
        awesomeValidation.addValidation(this,R.id.emp8, Range.closed(5000,200000),R.string.invalid_salary);









        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner1.setAdapter(aa);



    }

    final AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {

                //Performing action onItemSelected and onNothing selected
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                    String s1 = String.valueOf(Type[position]);
                    m6.setText(s1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }


            };



    public void DeleteDetail() {
        b4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = mydb.deleteDetail(m1.getText().toString());
                        if (deletedRows > 0) {
                            Toast.makeText( AD_Employee.this, "Data deleted", Toast.LENGTH_LONG ).show();
                            clearControls();

                        } else
                            Toast.makeText(AD_Employee.this, "Data Not deleted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


    public void UpdateDetail() {
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = mydb.updateDetail(m1.getText().toString(), m2.getText().toString(), m3.getText().toString(),
                                m4.getText().toString(), m5.getText().toString(), m6.getText().toString(), m7.getText().toString());
                        if (isUpdate == true && awesomeValidation.validate()) {
                            Toast.makeText( AD_Employee.this, "Data updated", Toast.LENGTH_LONG ).show();
                            clearControls();
                        }else
                            Toast.makeText(AD_Employee.this, "Data Not updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void AddData() {

        b1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted = mydb.insertData(m1.getText().toString(), m2.getText().toString(), m3.getText().toString(),
                                m4.getText().toString(), m5.getText().toString(), m6.getText().toString(), m7.getText().toString());
                        if (isInserted == true && awesomeValidation.validate()) {
                            Toast.makeText( AD_Employee.this, "Data Inserted", Toast.LENGTH_LONG ).show();
                            clearControls();
                        }else
                            Toast.makeText(AD_Employee.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(AD_Employee.this, Login.class);
                startActivity( logout );
                finish();
                Toast.makeText(getApplicationContext(),"LOGOUT",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ViewDetail() {
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mydb.getAllData();
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("View is Empty !!!", "No Data Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Employee ID :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Address :" + res.getString(2) + "\n");
                            buffer.append("Telephone :" + res.getString(3) + "\n");
                            buffer.append(" Mail :" + res.getString(4) + "\n");
                            buffer.append(" Working Position :" + res.getString(5) + "\n");
                            buffer.append("Salary :" + res.getString(6) + "\n\n");

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
    public void SearchData(){

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = mydb.searchData(m1.getText().toString());
                if (data.getCount() == 0) {
                    //Show Message
                    showMessage("Error ", "Nothing Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (data.moveToNext()) {
                    m1.setText( data.getString(0));
                    m2.setText( data.getString(1));
                    m3.setText( data.getString(2));
                    m4.setText( data.getString(3));
                    m5.setText( data.getString(4));
                    m6.setText( data.getString(5));
                    m7.setText( data.getString(6));


                }
            }
        });

    }
    private void clearControls(){
        m1.setText("");
        m2.setText("");
        m3.setText("");
        m4.setText("");
        m5.setText("");
        m6.setText("");
        m7.setText("");

    }







}
