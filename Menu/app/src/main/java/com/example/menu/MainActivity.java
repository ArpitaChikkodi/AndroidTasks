package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
/*
    This application demonstrates the menus and submenus.
    Whenever a menuitem/submenuitem is clicked it opens secondActivity through intent by passing the value,
    the value passed is the name of color and the secondActivity will be set to that specified background color
    With the welcome message!
 */

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText("Hello!!! \nClick on the menu item!");
    }

    //res-rightclick-Android Resource file- Resource file as Menu

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {

            case R.id.red:
                        Intent i1 = new Intent(MainActivity.this,SecondActivity.class);
                        i1.putExtra("message","Red");
                        startActivity(i1);
                        return true;

            case R.id.brown:
                        Intent i2 = new Intent(MainActivity.this,SecondActivity.class);
                        i2.putExtra("message","Brown");
                        startActivity(i2);
                        return true;

            case R.id.purple:
                        Intent i3 = new Intent(MainActivity.this,SecondActivity.class);
                        i3.putExtra("message","Purple");
                        startActivity(i3);
                        return true;



            case R.id.black:
                        Intent i4 = new Intent(MainActivity.this,SecondActivity.class);
                        i4.putExtra("message","Black");
                        startActivity(i4);
                        return true;

            case R.id.blue:
                        Intent i5 = new Intent(MainActivity.this,SecondActivity.class);
                        i5.putExtra("message","Blue");
                        startActivity(i5);
                        return true;

                default:
                    super.onOptionsItemSelected(menuItem);
        }
        return false;
    }
}