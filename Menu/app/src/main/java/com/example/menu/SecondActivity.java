package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ConstraintLayout constraintLayout = findViewById(R.id.clayout2);
        textView = (TextView)findViewById(R.id.textView2);
        back = (Button)findViewById(R.id.backbtn);

        Intent in = getIntent();
        String str = in.getStringExtra("message");
        textView.setText("Welcome to "+str+" Activity!");
        textView.setTextColor(Color.WHITE);
        switch(str)
        {
            case "Brown":
                constraintLayout.setBackgroundColor(Color.rgb(61,0,31));
                break;

            case "Black":
                constraintLayout.setBackgroundColor(Color.BLACK);
                break;

            case "Purple":
                constraintLayout.setBackgroundColor(Color.rgb(122,0,81));
                break;

            case "Blue":
                constraintLayout.setBackgroundColor(Color.BLUE);
                break;

            case "Red":
                constraintLayout.setBackgroundColor(Color.RED);
                break;

            default:
                //default background is white
                constraintLayout.setBackgroundColor(Color.WHITE);
                break;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
