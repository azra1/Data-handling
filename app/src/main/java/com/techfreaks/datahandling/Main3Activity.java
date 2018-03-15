package com.techfreaks.datahandling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText et1,et2,et3;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        et1= (EditText) findViewById(R.id.editText4);
        et2= (EditText) findViewById(R.id.editText5);
        et3= (EditText) findViewById(R.id.editText6);
        b= (Button) findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rid=et1.getText().toString();
                long id=Long.parseLong(rid);
                String name=et2.getText().toString();
                String email=et3.getText().toString();
                DBAdapter db=new DBAdapter(Main3Activity.this);
                db.open();
                if(db.upDateContact(id,name,email))
                    Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }
}
