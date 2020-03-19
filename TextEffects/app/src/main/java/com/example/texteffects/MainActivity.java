package com.example.texteffects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/*
    This application demonstrates how to dynamically change text effects
    It has a seekbar which helps to increase size of user input text
    3 buttons itaic,bold and normal
    normal button clears all the formatting of text
    Spinner helps to change the color of text
 */

public class MainActivity extends AppCompatActivity {

    EditText editInput;
    SeekBar seekBar;
    Button bold,italic,normal;
    Spinner spinner;

    int seekValue;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editInput = (EditText)findViewById(R.id.inputedit);
        //Default color is black
        editInput.setTextColor(Color.BLACK);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        //setting max and min value for seekbar
        seekBar.setMin(18);
        seekBar.setMax(30);

        bold = (Button)findViewById(R.id.btnbold);
        italic = (Button)findViewById(R.id.btnitalic);
        normal = (Button)findViewById(R.id.btnormal);

        spinner = (Spinner)findViewById(R.id.spinner);
        //Adding items in spinner through ArrayList
        ArrayList<String> alist = new ArrayList<>();
        alist.add("Black");
        alist.add("Red");
        alist.add("Blue");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,alist);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                if(str.equals("Red"))
                    editInput.setTextColor(Color.RED);
                else if (str.equals("Blue"))
                    editInput.setTextColor(Color.BLUE);
                else if (str.equals("Black"))
                    editInput.setTextColor(Color.BLACK);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Selected nothing from list", Toast.LENGTH_SHORT).show();
            }
        });

        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInput.setTypeface(null, Typeface.BOLD);
            }
        });

        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInput.setTypeface(null, Typeface.ITALIC);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekValue = 18;
                editInput.setTypeface(null, Typeface.NORMAL);
                editInput.setTextSize(seekValue);
                editInput.setTextColor(Color.BLACK);
                //To set seekbar progress to normal
                seekBar.setProgress(seekValue);
            }
        });

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seekValue = progress;
            //progress of seekbar is stored as integer value
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            editInput.setTextSize(seekValue);
        }
    });

    }
}