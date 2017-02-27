package com.androidmonk.tictactoe9x9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mayank Padhi on 15-01-2017.
 */

public class Main2Activity  extends AppCompatActivity implements View.OnClickListener{
    Button bAbtus;
    Button bPlay;
    Button bInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bPlay = (Button) findViewById(R.id.bPlay);
        bInstructions = (Button) findViewById(R.id.bInstructions);
        bAbtus =  (Button) findViewById(R.id.bAbtus);

        bPlay.setOnClickListener(this);
        bInstructions.setOnClickListener(this);
        bAbtus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){
            case R.id.bPlay:
                Intent i = new Intent(Main2Activity.this, plrName.class);
                startActivity(i);
                break;

            case R.id.bInstructions:
                Intent w = new Intent(Main2Activity.this, Instruction.class);
                startActivity(w);
                break;

            case R.id.bAbtus:
                Intent z = new Intent(Main2Activity.this, abtus.class);
                startActivity(z);
                break;

        }
    }
}