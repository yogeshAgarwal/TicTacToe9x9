package com.androidmonk.tictactoe9x9;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;

public class plrName extends AppCompatActivity{

    EditText etName1, etName2;
    Button bDone;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plrname_layout);

        etName1 = (EditText) findViewById(R.id.etName1);

        etName2 = (EditText) findViewById(R.id.etName2);
        bDone = (Button) findViewById(R.id.bDone);

        bDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name1 = etName1.getText().toString();
                Intent a = new Intent(plrName.this, MainActivity.class);
                a.putExtra("Name1", name1);
                String name2 = etName2.getText().toString();
                //Intent d = new Intent(Main2Activity.this, MainActivity.class);
                a.putExtra("Name2", name2);
                startActivity(a);
                //startActivity(d);
            }
        });
    }
}
