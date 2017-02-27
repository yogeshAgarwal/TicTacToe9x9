package com.androidmonk.tictactoe9x9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mayank Padhi on 15-01-2017.
 */

public class Instruction extends AppCompatActivity implements View.OnClickListener{
    Button bPlay1;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.instr_layout);

        bPlay1 = (Button) findViewById(R.id.bPlay1);

        bPlay1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

            switch(id){
                case R.id.bPlay1:
                    Intent i = new Intent(Instruction.this, plrName.class);
                    startActivity(i);
                    break;
            }
    }
}
