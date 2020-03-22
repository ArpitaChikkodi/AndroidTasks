package com.example.ticketbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelperClass databaseHelperClass;
    EditText id, name, source, destination, dated, timed;
    Button  addData, updateData, deleteData, viewData,clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelperClass = new DatabaseHelperClass(this);
        id = findViewById(R.id.editId);
        id.setInputType(InputType.TYPE_CLASS_NUMBER);
        id.requestFocus();


        name = findViewById(R.id.editName);
        name.setInputType(InputType.TYPE_CLASS_TEXT);

        source = findViewById(R.id.editSource);
        source.setInputType(InputType.TYPE_CLASS_TEXT);

        destination = findViewById(R.id.editDestination);
        destination.setInputType(InputType.TYPE_CLASS_TEXT);

        dated = findViewById(R.id.editDate);
        dated.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_DATETIME );

        timed = findViewById(R.id.editTime);
        timed.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_DATETIME);

        addData = findViewById(R.id.btnadd);
        viewData = findViewById(R.id.btnread);
        updateData = findViewById(R.id.btnupdate);
        deleteData = findViewById(R.id.btndelete);
        clear = findViewById(R.id.btnclear);


        AddData();
        UpdateData();
        ViewAll();
        DeleteData();
        clearData();

    }

    public void AddData() {
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sid = id.getText().toString();
                String sname = name.getText().toString();
                String ssource = source.getText().toString();
                String sdestination = destination.getText().toString();
                String sdate = dated.getText().toString();
                String stime = timed.getText().toString();
                if (sid != "" && sname != "" && ssource != "" && sdestination != "" && sdate != "" && stime != "") {


                    boolean isInserted = databaseHelperClass.insertData(sid, sname, ssource, sdestination, sdate, stime);
                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Data Inserted !!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Inserted !!!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fill all the fields !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void UpdateData() {
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sid = id.getText().toString();
                String sname = name.getText().toString();
                String ssource = source.getText().toString();
                String sdestination = destination.getText().toString();
                String sdate = dated.getText().toString();
                String stime = timed.getText().toString();
                if (sid != "" && sname != "" && ssource != "" && sdestination != "" && sdate != "" && stime != "") {

                    boolean isUpdated = databaseHelperClass.updateDataThroughId(sid, sname, ssource, sdestination, sdate, stime);

                    if (isUpdated == true) {
                        Toast.makeText(MainActivity.this, "Data Updated !!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not updated !!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void DeleteData() {
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = databaseHelperClass.deleteDataThroughId(id.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Data deleted !!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not deleted !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewAll() {
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = databaseHelperClass.getAllData();
                if (cursor.getCount() == 0) {
                    showMessage("Details", "No Bookings!");
                    Toast.makeText(MainActivity.this, "No bookings done!!!!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        stringBuffer.append("ID : " + cursor.getString(0) + "\n");
                        stringBuffer.append("NAME : " + cursor.getString(1) + "\n");
                        stringBuffer.append("SOURCE : " + cursor.getString(2) + "\n");
                        stringBuffer.append("DESTINATION : " + cursor.getString(3) + "\n");
                        stringBuffer.append("DATED : " + cursor.getString(4) + "\n");
                        stringBuffer.append("TIMED : " + cursor.getString(5) + "\n");

                    }
                    showMessage("Booking Details : ", stringBuffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearData()
    {
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText("");
                name.setText("");
                source.setText("");
                destination.setText("");
                dated.setText("");
                timed.setText("");
            }
        });

    }
}
