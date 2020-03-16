package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signin,cancel,clear;
    EditText username,password;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin = (Button)findViewById(R.id.signin);
        cancel = (Button)findViewById(R.id.cancel);
        clear = (Button)findViewById(R.id.clear);


        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("abc@gmail.com") && password.getText().toString().equals("abc@123")){
                    Toast.makeText(getApplicationContext(),"Login Valid",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            startActivity(intent);
                }
                else if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter all the required fields!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Count the attempts for wrong credentials and disable login button for 10 seconds after 3 wrong attempts using CountDownTimer
                    count++;
                    Toast.makeText(getApplicationContext(),"Invalid credentials",'4').show(); //4seconds
                    if(count==3)
                    {
                        signin.setEnabled(false);
                        Toast.makeText(getApplicationContext(),"SignIn button has been blocked for 10 seconds due to 3 wrong attempts!",Toast.LENGTH_LONG).show();

                        new CountDownTimer(10000, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                    signin.setEnabled(true);
                                    count = 0;
                            }
                        }.start();


                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
            }
        });

    }
}
