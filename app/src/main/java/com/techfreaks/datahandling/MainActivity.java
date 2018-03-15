package com.techfreaks.datahandling;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b,b1,b2,b3,b4;
    DBAdapter db=new DBAdapter(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        b = (Button) findViewById(R.id.button);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        b4= (Button) findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(i);
            }
        });
      /*  b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),);
                startActivity(i);
            }
        });*/
        b2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("DELETE CONTACT");
                alert.setMessage("Enter Id to Delete");
                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String rid = input.getText().toString();
                        long id = Long.parseLong(rid);
                        db.open();
                        if (db.deleteContact(id)) {
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data deleted Successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog ad = alert.create();
                input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                });
                ad.show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=e1.getText().toString();
                String email=e2.getText().toString();
                DBAdapter db=new DBAdapter(MainActivity.this);
                db.open();
                long id=db.insertContact(name,email);
                db.close();
                e1.setText("");
                e2.setText("");
                if(id>0)
                {
                    Toast.makeText(getApplicationContext(),"DATA INSERTED SUCCESFULLY",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Show Contact");
                alert.setMessage("Enter Id to shoe details:");
                final EditText input=new EditText(MainActivity.this);
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String rid=input.getText().toString();
                        long id=Long.parseLong(rid);
                        db.open();
                        Cursor c=db.getContact(id);
                        if(c.moveToFirst())
                            DisplayContact(c);
                        else
                            Toast.makeText(getApplicationContext(),"No Contact Found",Toast.LENGTH_LONG).show();
                    }
                    public void DisplayContact(Cursor c)
                    {
                        Toast.makeText(getApplicationContext(),"ID: "+c.getString(0)+"\n"+"Name: "+c.getString(1)+"\n"+"Email:"+c.getString(2),Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog ad=alert.create();
                input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus)
                        {
                            ad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                    }
                });
                ad.show();

            }
        });
    }
}
