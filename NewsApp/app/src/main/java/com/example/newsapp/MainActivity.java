package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnEnglish;
    FragmentEnglish frag1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnglish = (Button)findViewById(R.id.btnEnglish);


        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag1 = new FragmentEnglish();
                FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.add(R.id.llayout, frag1);
                fragmentTransaction.commit(); // save the changes
            }
        });


    }
}
