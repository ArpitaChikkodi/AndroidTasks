package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
    This application fetches news from newsapi-google API Key
    It fetches the title and description and shows it in fragment at runtime
    It uses internet permission!
    Fragment life cycle is demonstrated!
 */
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
                Toast.makeText(MainActivity.this,"Fragment called!",'1').show();
                FragmentManager fm = getSupportFragmentManager();
                Toast.makeText(MainActivity.this,"Fragment oncreate()!",'1').show();
// create a FragmentTransaction to begin the transaction and replace the Fragment
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.add(R.id.llayout, frag1);
                fragmentTransaction.commit(); // save the changes
                Toast.makeText(MainActivity.this,"Fragment onResume()!",'2').show();
            }
        });


    }
}
