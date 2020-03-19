package com.example.runtimeimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
/*
This app works even without these permissions since EXTERNAL_CONTENT_URI is called!
<uses-permission android:name="android.permission.MANAGE_DOCUMENTS"
        tools:ignore="ProtectedPermissions" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 */

public class MainActivity extends AppCompatActivity {

    Button button1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout flayout = findViewById(R.id.firstlayout);
        ImageView iview1 = new ImageView(this);

        //calling image from drawable folder at runtime
        iview1.setImageResource(R.drawable.hello);
        flayout.addView(iview1);

        textView1 = (TextView)findViewById(R.id.textview1);
        button1 = (Button) findViewById(R.id.click);
        //Click on this button to call an image from phone gallery

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LinearLayout slayout = findViewById(R.id.secondlayout);
        ImageView iview2 = new ImageView(this);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            //Can also print the uri/path of image on phone
            //String path = targetUri.toString();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                iview2.setImageBitmap(bitmap);
                slayout.addView(iview2);
                textView1.setVisibility(View.GONE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
