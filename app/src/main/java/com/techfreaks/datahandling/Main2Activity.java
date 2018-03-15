package com.techfreaks.datahandling;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DBAdapter db=new DBAdapter(Main2Activity.this);
        db.open();
        Cursor c=db.getAllContacts();
        if(c.moveToFirst())
        {
            do{
                DisplayContact(c);
            }while(c.moveToNext());
        }
        db.close();

    }
    public void DisplayContact(Cursor c)
    {
        TableLayout t1= (TableLayout) findViewById(R.id.table);
        TableRow tr;
        tr=new TableRow(this);
        TextView tv1=new TextView(this);
        tv1.setText(c.getString(0));
        TextView tv2=new TextView(this);
        tv2.setText(c.getString(1));
        TextView tv3=new TextView(this);
        tv3.setText(c.getString(2));
        tr.addView(tv1);
        tr.addView(tv2);
        tr.addView(tv3);
        t1.addView(tr);
    }
}
