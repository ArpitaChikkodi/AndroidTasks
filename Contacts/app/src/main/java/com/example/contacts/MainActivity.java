package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeSet;

//After installing please go to settings and give permission to access contacts then only it will work

/*
    The contacts here are sorted in ascending order and the repetitions are avoided
    by adding it to Treeset with the required validations, since this is not working for email,dob and fax fields so
    another app with its own database is created!
 */
public class MainActivity extends AppCompatActivity {

    ArrayList<String> alist = new ArrayList<>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null,null,null,null);
        if(cur.getCount()>0)
        {
            while (cur.moveToNext())
            {
                try {
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    /*String email=null;
                    String dob = null;

                    if(cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)) != null && cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE))!=null )
                    {
                        email = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        dob = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                    }*/
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        Cursor pcur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                                new String[]{id},null);
                        int count = 0;
                        while (pcur.moveToNext()) {
                            String str = "";
                            String phoneNo = pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            count++;
                             if (name != null) {
                                str += "\nName : " + name + "\n";
                            }
                            if (phoneNo != null) {
                                str += "Phone No : " + phoneNo + "\n";
                            }
  /*                          if (dob != null) {
                            //    stringBuffer.append("Birthday : " + dob + "\n");
                                str += "Birthday : " + dob + "\n";
                            }
                            if(email!=null) {
                                //stringBuffer.append("Email : "+email+"\n");
                                str += "Email : "+email+"\n";
                            }
*/
                           // alist.add("Name: "+name+"\nPhone No : "+phoneNo);
                            alist.add(str);
                            // alist.add("Name: "+name+"\nPhone No : "+phoneNo+"\nEmail : "+email+"\nBirthday : "+dob);
                        }
                        pcur.close();
                        
                    }
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Some error fetching contacts!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(alist);

        alist.clear();
        alist.addAll(treeSet);

        lv = (ListView)findViewById(R.id.listview1);
        ArrayAdapter<String> adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alist);
        lv.setAdapter(adt);
    }
}