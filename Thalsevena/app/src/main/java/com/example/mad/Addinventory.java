package com.example.mad;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class Addinventory extends AppCompatActivity {
    DB_inventory myDb;
    EditText v1, v2, v3, v4, v5;
    Button btn,search;
    Button view;
    Button update;
    Button delete;
    AwesomeValidation awesomeValidation;
    Button log;
    //Spinner
    String[] InventoryType = {"Tables", "Beds","Chairs","Fan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        myDb=new DB_inventory(this);
        v1 = findViewById(R.id.id);
        v2 = findViewById(R.id.type);
        v3 = findViewById(R.id.room);
        v4 = findViewById(R.id.cost);
        v5 = findViewById(R.id.fac);
        btn = findViewById(R.id.button);
        view = findViewById(R.id.button7 );
        update = findViewById(R.id.button4);
        delete = findViewById(R.id.button3);
        search = findViewById(R.id.search);
        log=findViewById(R.id.log);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        SearchData();

        log.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout =new Intent( Addinventory.this,Login.class );
                startActivity( logout );
                finish();
                Toast.makeText(getApplicationContext(),"LOGOUT",Toast.LENGTH_SHORT).show();
            }
        } );
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.id, RegexTemplate.NOT_EMPTY,R.string.invalid_id);
        awesomeValidation.addValidation(this,R.id.type, RegexTemplate.NOT_EMPTY,R.string.invalid_type);
        awesomeValidation.addValidation(this,R.id.room,".{1}",R.string.invalid_noofrooms);
        awesomeValidation.addValidation(this,R.id.cost, Range.closed(1000,12000),R.string.invalid_roomcost);
        awesomeValidation.addValidation(this,R.id.fac, RegexTemplate.NOT_EMPTY,R.string.invalid_facilities);



        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, InventoryType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner1.setAdapter(aa);



    }

    final AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {

                //Performing action onItemSelected and onNothing selected
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                    String s1 = String.valueOf(InventoryType[position]);
                    v2.setText(s1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }


            };




    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows =myDb.deleteData(v1.getText().toString());
                        if(deletedRows >0) {
                            Toast.makeText( Addinventory.this, "Data deleted", Toast.LENGTH_LONG ).show();
                            clearControls();
                        }else
                            Toast.makeText(Addinventory.this,"Data Not deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void UpdateData() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isUpdate = myDb.updateData(v1.getText().toString(), v2.getText().toString(), v3.getText().toString(),
                                v4.getText().toString(), v5.getText().toString());
                        if (isUpdate == true &&awesomeValidation.validate()) {
                            Toast.makeText( Addinventory.this, "Data updated", Toast.LENGTH_LONG ).show();
                            clearControls();
                        }else
                            Toast.makeText(Addinventory.this, "Data Not updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void AddData(){
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(v1.getText().toString(),v2.getText().toString(),v3.getText().toString(),
                                v4.getText().toString(),v5.getText().toString());
                        if(isInserted == true &&awesomeValidation.validate() ) {
                            clearControls();
                            Toast.makeText( Addinventory.this, "Data Inserted", Toast.LENGTH_LONG ).show();

                        }else
                            Toast.makeText(Addinventory.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
    public void viewAll(){
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDb.getAllData();
                        Cursor res =myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("View is Empty !!!","No Data Found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Inventory ID :"+res.getString(0)+"\n");
                            buffer.append("Inventory Type :"+res.getString(1)+"\n");
                            buffer.append("No Of Inventory :"+res.getString(2)+"\n");
                            buffer.append("Inventory Cost:"+res.getString(3)+"\n");
                            buffer.append("Supplier Name:"+res.getString(4)+"\n\n");

                        }
                        showMessage("Inventory Details",buffer.toString());

                    }
                }
        );
    }
    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    public void SearchData(){

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDb.searchData(v1.getText().toString());
                if (data.getCount() == 0) {
                    //Show Message
                    showMessage("Error ", "Nothing Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (data.moveToNext()) {
                    v1.setText( data.getString(0));
                    v2.setText( data.getString(1));
                    v3.setText( data.getString(2));
                    v4.setText( data.getString(3));
                    v5.setText( data.getString(4));

                }
            }
        });

    }
    private void clearControls(){
        v1.setText("");
        v2.setText("");
        v3.setText("");
        v4.setText("");
        v5.setText("");

    }
}