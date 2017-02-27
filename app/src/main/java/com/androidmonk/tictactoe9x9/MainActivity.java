package com.androidmonk.tictactoe9x9;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    ImageButton[][] b00 = new ImageButton[3][3];
    ImageButton[][] b01 = new ImageButton[3][3];
    ImageButton[][] b02 = new ImageButton[3][3];

    ImageButton[][] b10 = new ImageButton[3][3];
    ImageButton[][] b11 = new ImageButton[3][3];
    ImageButton[][] b12 = new ImageButton[3][3];

    ImageButton[][] b20 = new ImageButton[3][3];
    ImageButton[][] b21 = new ImageButton[3][3];
    ImageButton[][] b22 = new ImageButton[3][3];

    TableLayout tl00, tl01, tl02, tl10, tl11, tl12, tl20, tl21, tl22;

    Button bReset, bUndo;

    TextView tvInfo, tvColor1, tvColor2, tvPlayer1, tvPlayer2;

    boolean PLAYER_X = true, bad = false;

    MediaPlayer click, reset, undo, block, game;
    int TURN_COUNT = 0;

    int[][] boardStatus = new int[9][9];

    String name1, name2;

    int WIN_X = 0, WIN_O = 0;

    int PLAYER_X_BLOCK, PLAYER_O_BLOCK, PREVIOUS_X_BLOCK, PREVIOUS_O_BLOCK, PREVIOUS_BUTTON;

    boolean[][] blockStatus = new boolean[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click= MediaPlayer.create(this, R.raw.adriantnt_bubble_clap);
        undo = MediaPlayer.create(this, R.raw.adriantnt_u_click);
        reset = MediaPlayer.create(this, R.raw.thozi_da_click);
        block = MediaPlayer.create(this, R.raw.block_win);
        game = MediaPlayer.create(this, R.raw.game_win);

        Intent j = getIntent();
        name1 = j.getStringExtra("Name1");
        name2 = j.getStringExtra("Name2");

        Toast.makeText(MainActivity.this,
                "Hello " + name1 + " and " + name2,
                Toast.LENGTH_SHORT).show();

        initializeBoard();

        b00[0][0] = (ImageButton) findViewById(R.id.b0000);
        b00[0][1] = (ImageButton) findViewById(R.id.b0001);
        b00[0][2] = (ImageButton) findViewById(R.id.b0002);
        b00[1][0] = (ImageButton) findViewById(R.id.b0010);
        b00[1][1] = (ImageButton) findViewById(R.id.b0011);
        b00[1][2] = (ImageButton) findViewById(R.id.b0012);
        b00[2][0] = (ImageButton) findViewById(R.id.b0020);
        b00[2][1] = (ImageButton) findViewById(R.id.b0021);
        b00[2][2] = (ImageButton) findViewById(R.id.b0022);

        b01[0][0] = (ImageButton) findViewById(R.id.b0100);
        b01[0][1] = (ImageButton) findViewById(R.id.b0101);
        b01[0][2] = (ImageButton) findViewById(R.id.b0102);
        b01[1][0] = (ImageButton) findViewById(R.id.b0110);
        b01[1][1] = (ImageButton) findViewById(R.id.b0111);
        b01[1][2] = (ImageButton) findViewById(R.id.b0112);
        b01[2][0] = (ImageButton) findViewById(R.id.b0120);
        b01[2][1] = (ImageButton) findViewById(R.id.b0121);
        b01[2][2] = (ImageButton) findViewById(R.id.b0122);

        b02[0][0] = (ImageButton) findViewById(R.id.b0200);
        b02[0][1] = (ImageButton) findViewById(R.id.b0201);
        b02[0][2] = (ImageButton) findViewById(R.id.b0202);
        b02[1][0] = (ImageButton) findViewById(R.id.b0210);
        b02[1][1] = (ImageButton) findViewById(R.id.b0211);
        b02[1][2] = (ImageButton) findViewById(R.id.b0212);
        b02[2][0] = (ImageButton) findViewById(R.id.b0220);
        b02[2][1] = (ImageButton) findViewById(R.id.b0221);
        b02[2][2] = (ImageButton) findViewById(R.id.b0222);

        b10[0][0] = (ImageButton) findViewById(R.id.b1000);
        b10[0][1] = (ImageButton) findViewById(R.id.b1001);
        b10[0][2] = (ImageButton) findViewById(R.id.b1002);
        b10[1][0] = (ImageButton) findViewById(R.id.b1010);
        b10[1][1] = (ImageButton) findViewById(R.id.b1011);
        b10[1][2] = (ImageButton) findViewById(R.id.b1012);
        b10[2][0] = (ImageButton) findViewById(R.id.b1020);
        b10[2][1] = (ImageButton) findViewById(R.id.b1021);
        b10[2][2] = (ImageButton) findViewById(R.id.b1022);

        b11[0][0] = (ImageButton) findViewById(R.id.b1100);
        b11[0][1] = (ImageButton) findViewById(R.id.b1101);
        b11[0][2] = (ImageButton) findViewById(R.id.b1102);
        b11[1][0] = (ImageButton) findViewById(R.id.b1110);
        b11[1][1] = (ImageButton) findViewById(R.id.b1111);
        b11[1][2] = (ImageButton) findViewById(R.id.b1112);
        b11[2][0] = (ImageButton) findViewById(R.id.b1120);
        b11[2][1] = (ImageButton) findViewById(R.id.b1121);
        b11[2][2] = (ImageButton) findViewById(R.id.b1122);

        b12[0][0] = (ImageButton) findViewById(R.id.b1200);
        b12[0][1] = (ImageButton) findViewById(R.id.b1201);
        b12[0][2] = (ImageButton) findViewById(R.id.b1202);
        b12[1][0] = (ImageButton) findViewById(R.id.b1210);
        b12[1][1] = (ImageButton) findViewById(R.id.b1211);
        b12[1][2] = (ImageButton) findViewById(R.id.b1212);
        b12[2][0] = (ImageButton) findViewById(R.id.b1220);
        b12[2][1] = (ImageButton) findViewById(R.id.b1221);
        b12[2][2] = (ImageButton) findViewById(R.id.b1222);

        b20[0][0] = (ImageButton) findViewById(R.id.b2000);
        b20[0][1] = (ImageButton) findViewById(R.id.b2001);
        b20[0][2] = (ImageButton) findViewById(R.id.b2002);
        b20[1][0] = (ImageButton) findViewById(R.id.b2010);
        b20[1][1] = (ImageButton) findViewById(R.id.b2011);
        b20[1][2] = (ImageButton) findViewById(R.id.b2012);
        b20[2][0] = (ImageButton) findViewById(R.id.b2020);
        b20[2][1] = (ImageButton) findViewById(R.id.b2021);
        b20[2][2] = (ImageButton) findViewById(R.id.b2022);

        b21[0][0] = (ImageButton) findViewById(R.id.b2100);
        b21[0][1] = (ImageButton) findViewById(R.id.b2101);
        b21[0][2] = (ImageButton) findViewById(R.id.b2102);
        b21[1][0] = (ImageButton) findViewById(R.id.b2110);
        b21[1][1] = (ImageButton) findViewById(R.id.b2111);
        b21[1][2] = (ImageButton) findViewById(R.id.b2112);
        b21[2][0] = (ImageButton) findViewById(R.id.b2120);
        b21[2][1] = (ImageButton) findViewById(R.id.b2121);
        b21[2][2] = (ImageButton) findViewById(R.id.b2122);

        b22[0][0] = (ImageButton) findViewById(R.id.b2200);
        b22[0][1] = (ImageButton) findViewById(R.id.b2201);
        b22[0][2] = (ImageButton) findViewById(R.id.b2202);
        b22[1][0] = (ImageButton) findViewById(R.id.b2210);
        b22[1][1] = (ImageButton) findViewById(R.id.b2211);
        b22[1][2] = (ImageButton) findViewById(R.id.b2212);
        b22[2][0] = (ImageButton) findViewById(R.id.b2220);
        b22[2][1] = (ImageButton) findViewById(R.id.b2221);
        b22[2][2] = (ImageButton) findViewById(R.id.b2222);

        bReset = (Button) findViewById(R.id.bReset);

        bUndo = (Button) findViewById(R.id.bUndo);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        tvPlayer1 = (TextView) findViewById(R.id.tvPlayer1);

        tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2);

        tvColor1 = (TextView) findViewById(R.id.tvColor1);

        tvColor2 = (TextView) findViewById(R.id.tvColor2);

        tvPlayer1.setText(name1);

        tvPlayer2.setText(name2);

        setInfo(name1 + "'s turn");

        bUndo.setEnabled(false);

        tl00 = (TableLayout) findViewById(R.id.tl00);
        tl01 = (TableLayout) findViewById(R.id.tl01);
        tl02 = (TableLayout) findViewById(R.id.tl02);

        tl10 = (TableLayout) findViewById(R.id.tl10);
        tl11 = (TableLayout) findViewById(R.id.tl11);
        tl12 = (TableLayout) findViewById(R.id.tl12);

        tl20 = (TableLayout) findViewById(R.id.tl20);
        tl21 = (TableLayout) findViewById(R.id.tl21);
        tl22 = (TableLayout) findViewById(R.id.tl22);

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                b00[i][k].setOnClickListener(this);
                b01[i][k].setOnClickListener(this);
                b02[i][k].setOnClickListener(this);

                b10[i][k].setOnClickListener(this);
                b11[i][k].setOnClickListener(this);
                b12[i][k].setOnClickListener(this);

                b20[i][k].setOnClickListener(this);
                b21[i][k].setOnClickListener(this);
                b22[i][k].setOnClickListener(this);
            }
        }

        bReset.setOnClickListener(this);

        bUndo.setOnClickListener(this);
    }

    private void setInfo(String text) {
        tvInfo.setText(text);
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardStatus[i][j] = -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockStatus[i][j] = true;
            }
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (TURN_COUNT >= 2) {
            switch (id) {
                case R.id.b0000:
                    if (PLAYER_X) {
                        b00[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b00[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;
                    }
                    b00[0][0].setEnabled(false);
                    break;
                case R.id.b0001:
                    if (PLAYER_X) {
                        b00[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;

                    } else {
                        b00[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;

                    }
                    b00[0][1].setEnabled(false);
                    break;
                case R.id.b0002:
                    if (PLAYER_X) {
                        b00[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;

                    } else {
                        b00[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;

                    }
                    b00[0][2].setEnabled(false);
                    break;
                case R.id.b0010:
                    if (PLAYER_X) {
                        b00[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;

                    } else {
                        b00[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;

                    }
                    b00[1][0].setEnabled(false);
                    break;
                case R.id.b0011:
                    if (PLAYER_X) {
                        b00[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;

                    } else {
                        b00[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;

                    }
                    b00[1][1].setEnabled(false);
                    break;
                case R.id.b0012:
                    if (PLAYER_X) {
                        b00[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b00[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;

                    }
                    b00[1][2].setEnabled(false);
                    break;
                case R.id.b0020:
                    if (PLAYER_X) {
                        b00[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;

                    } else {
                        b00[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;

                    }
                    b00[2][0].setEnabled(false);
                    break;
                case R.id.b0021:
                    if (PLAYER_X) {
                        b00[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;

                    } else {
                        b00[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;

                    }
                    b00[2][1].setEnabled(false);
                    break;
                case R.id.b0022:
                    if (PLAYER_X) {
                        b00[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;

                    } else {
                        b00[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;

                    }
                    b00[2][2].setEnabled(false);
                    break;
                case R.id.b0100:
                    if (PLAYER_X) {
                        b01[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;

                    } else {
                        b01[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;

                    }
                    b01[0][0].setEnabled(false);
                    break;
                case R.id.b0101:
                    if (PLAYER_X) {
                        b01[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;

                    } else {
                        b01[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;

                    }
                    b01[0][1].setEnabled(false);
                    break;
                case R.id.b0102:
                    if (PLAYER_X) {
                        b01[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;

                    } else {
                        b01[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;

                    }
                    b01[0][2].setEnabled(false);
                    break;
                case R.id.b0110:
                    if (PLAYER_X) {
                        b01[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;

                    } else {
                        b01[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;

                    }
                    b01[1][0].setEnabled(false);
                    break;
                case R.id.b0111:
                    if (PLAYER_X) {
                        b01[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;

                    } else {
                        b01[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;

                    }
                    b01[1][1].setEnabled(false);
                    break;
                case R.id.b0112:
                    if (PLAYER_X) {
                        b01[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b01[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;

                    }
                    b01[1][2].setEnabled(false);
                    break;
                case R.id.b0120:
                    if (PLAYER_X) {
                        b01[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;

                    } else {
                        b01[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;

                    }
                    b01[2][0].setEnabled(false);
                    break;
                case R.id.b0121:
                    if (PLAYER_X) {
                        b01[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;

                    } else {
                        b01[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;

                    }
                    b01[2][1].setEnabled(false);
                    break;
                case R.id.b0122:
                    if (PLAYER_X) {
                        b01[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;

                    } else {
                        b01[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;

                    }
                    b01[2][2].setEnabled(false);
                    break;
                case R.id.b0200:
                    if (PLAYER_X) {
                        b02[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;

                    } else {
                        b02[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;

                    }
                    b02[0][0].setEnabled(false);
                    break;
                case R.id.b0201:
                    if (PLAYER_X) {
                        b02[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;

                    } else {
                        b02[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;

                    }
                    b02[0][1].setEnabled(false);
                    break;
                case R.id.b0202:
                    if (PLAYER_X) {
                        b02[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;

                    } else {
                        b02[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;

                    }
                    b02[0][2].setEnabled(false);
                    break;
                case R.id.b0210:
                    if (PLAYER_X) {
                        b02[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;

                    } else {
                        b02[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;

                    }
                    b02[1][0].setEnabled(false);
                    break;
                case R.id.b0211:
                    if (PLAYER_X) {
                        b02[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;

                    } else {
                        b02[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;

                    }
                    b02[1][1].setEnabled(false);
                    break;
                case R.id.b0212:
                    if (PLAYER_X) {
                        b02[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b02[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;

                    }
                    b02[1][2].setEnabled(false);
                    break;
                case R.id.b0220:
                    if (PLAYER_X) {
                        b02[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][0] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;

                    } else {
                        b02[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][0] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;

                    }
                    b02[2][0].setEnabled(false);
                    break;
                case R.id.b0221:
                    if (PLAYER_X) {
                        b02[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][1] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;

                    } else {
                        b02[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][1] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;

                    }
                    b02[2][1].setEnabled(false);
                    break;
                case R.id.b0222:
                    if (PLAYER_X) {
                        b02[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][2] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;

                    } else {
                        b02[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][2] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;

                    }
                    b02[2][2].setEnabled(false);
                    break;
                case R.id.b1000:
                    if (PLAYER_X) {
                        b10[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;

                    } else {
                        b10[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;

                    }
                    b10[0][0].setEnabled(false);
                    break;
                case R.id.b1001:
                    if (PLAYER_X) {
                        b10[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;

                    } else {
                        b10[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;

                    }
                    b10[0][1].setEnabled(false);
                    break;
                case R.id.b1002:
                    if (PLAYER_X) {
                        b10[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;

                    } else {
                        b10[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;

                    }
                    b10[0][2].setEnabled(false);
                    break;
                case R.id.b1010:
                    if (PLAYER_X) {
                        b10[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;

                    } else {
                        b10[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;

                    }
                    b10[1][0].setEnabled(false);
                    break;
                case R.id.b1011:
                    if (PLAYER_X) {
                        b10[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;

                    } else {
                        b10[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;

                    }
                    b10[1][1].setEnabled(false);
                    break;
                case R.id.b1012:
                    if (PLAYER_X) {
                        b10[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b10[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;

                    }
                    b10[1][2].setEnabled(false);
                    break;
                case R.id.b1020:
                    if (PLAYER_X) {
                        b10[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;

                    } else {
                        b10[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;

                    }
                    b10[2][0].setEnabled(false);
                    break;
                case R.id.b1021:
                    if (PLAYER_X) {
                        b10[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;

                    } else {
                        b10[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;

                    }
                    b10[2][1].setEnabled(false);
                    break;
                case R.id.b1022:
                    if (PLAYER_X) {
                        b10[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;

                    } else {
                        b10[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;

                    }
                    b10[2][2].setEnabled(false);
                    break;
                case R.id.b1100:
                    if (PLAYER_X) {
                        b11[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;

                    } else {
                        b11[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;

                    }
                    b11[0][0].setEnabled(false);
                    break;
                case R.id.b1101:
                    if (PLAYER_X) {
                        b11[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;

                    } else {
                        b11[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;

                    }
                    b11[0][1].setEnabled(false);
                    break;
                case R.id.b1102:
                    if (PLAYER_X) {
                        b11[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;

                    } else {
                        b11[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;

                    }
                    b11[0][2].setEnabled(false);
                    break;
                case R.id.b1110:
                    if (PLAYER_X) {
                        b11[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;

                    } else {
                        b11[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;

                    }
                    b11[1][0].setEnabled(false);
                    break;
                case R.id.b1111:
                    if (PLAYER_X) {
                        b11[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;

                    } else {
                        b11[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;

                    }
                    b11[1][1].setEnabled(false);
                    break;
                case R.id.b1112:
                    if (PLAYER_X) {
                        b11[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b11[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;

                    }
                    b11[1][2].setEnabled(false);
                    break;
                case R.id.b1120:
                    if (PLAYER_X) {
                        b11[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;

                    } else {
                        b11[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;

                    }
                    b11[2][0].setEnabled(false);
                    break;
                case R.id.b1121:
                    if (PLAYER_X) {
                        b11[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;

                    } else {
                        b11[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;

                    }
                    b11[2][1].setEnabled(false);
                    break;
                case R.id.b1122:
                    if (PLAYER_X) {
                        b11[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;

                    } else {
                        b11[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;

                    }
                    b11[2][2].setEnabled(false);
                    break;
                case R.id.b1200:
                    if (PLAYER_X) {
                        b12[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b12[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;
                    }
                    b12[0][0].setEnabled(false);
                    break;
                case R.id.b1201:
                    if (PLAYER_X) {
                        b12[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b12[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;
                    }
                    b12[0][1].setEnabled(false);
                    break;
                case R.id.b1202:
                    if (PLAYER_X) {
                        b12[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b12[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;
                    }
                    b12[0][2].setEnabled(false);
                    break;
                case R.id.b1210:
                    if (PLAYER_X) {
                        b12[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b12[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;
                    }
                    b12[1][0].setEnabled(false);
                    break;
                case R.id.b1211:
                    if (PLAYER_X) {
                        b12[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b12[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;
                    }
                    b12[1][1].setEnabled(false);
                    break;
                case R.id.b1212:
                    if (PLAYER_X) {
                        b12[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b12[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;
                    }
                    b12[1][2].setEnabled(false);
                    break;
                case R.id.b1220:
                    if (PLAYER_X) {
                        b12[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][3] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b12[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][3] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;
                    }
                    b12[2][0].setEnabled(false);
                    break;
                case R.id.b1221:
                    if (PLAYER_X) {
                        b12[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][4] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b12[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][4] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;
                    }
                    b12[2][1].setEnabled(false);
                    break;
                case R.id.b1222:
                    if (PLAYER_X) {
                        b12[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][5] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b12[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][5] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;
                    }
                    b12[2][2].setEnabled(false);
                    break;
                case R.id.b2000:
                    if (PLAYER_X) {
                        b20[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b20[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;
                    }
                    b20[0][0].setEnabled(false);
                    break;
                case R.id.b2001:
                    if (PLAYER_X) {
                        b20[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b20[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;
                    }
                    b20[0][1].setEnabled(false);
                    break;
                case R.id.b2002:
                    if (PLAYER_X) {
                        b20[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b20[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;
                    }
                    b20[0][2].setEnabled(false);
                    break;
                case R.id.b2010:
                    if (PLAYER_X) {
                        b20[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b20[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;
                    }
                    b20[1][0].setEnabled(false);
                    break;
                case R.id.b2011:
                    if (PLAYER_X) {
                        b20[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b20[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;
                    }
                    b20[1][1].setEnabled(false);
                    break;
                case R.id.b2012:
                    if (PLAYER_X) {
                        b20[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b20[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;
                    }
                    b20[1][2].setEnabled(false);
                    break;
                case R.id.b2020:
                    if (PLAYER_X) {
                        b20[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b20[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;
                    }
                    b20[2][0].setEnabled(false);
                    break;
                case R.id.b2021:
                    if (PLAYER_X) {
                        b20[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b20[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;
                    }
                    b20[2][1].setEnabled(false);
                    break;
                case R.id.b2022:
                    if (PLAYER_X) {
                        b20[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b20[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;
                    }
                    b20[2][2].setEnabled(false);
                    break;
                case R.id.b2100:
                    if (PLAYER_X) {
                        b21[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b21[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;
                    }
                    b21[0][0].setEnabled(false);
                    break;
                case R.id.b2101:
                    if (PLAYER_X) {
                        b21[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b21[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;
                    }
                    b21[0][1].setEnabled(false);
                    break;
                case R.id.b2102:
                    if (PLAYER_X) {
                        b21[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b21[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;
                    }
                    b21[0][2].setEnabled(false);
                    break;
                case R.id.b2110:
                    if (PLAYER_X) {
                        b21[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b21[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;
                    }
                    b21[1][0].setEnabled(false);
                    break;
                case R.id.b2111:
                    if (PLAYER_X) {
                        b21[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b21[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;
                    }
                    b21[1][1].setEnabled(false);
                    break;
                case R.id.b2112:
                    if (PLAYER_X) {
                        b21[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b21[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;
                    }
                    b21[1][2].setEnabled(false);
                    break;
                case R.id.b2120:
                    if (PLAYER_X) {
                        b21[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b21[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;
                    }
                    b21[2][0].setEnabled(false);
                    break;
                case R.id.b2121:
                    if (PLAYER_X) {
                        b21[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b21[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;
                    }
                    b21[2][1].setEnabled(false);
                    break;
                case R.id.b2122:
                    if (PLAYER_X) {
                        b21[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b21[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;
                    }
                    b21[2][2].setEnabled(false);
                    break;
                case R.id.b2200:
                    if (PLAYER_X) {
                        b22[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b22[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 0;
                    }
                    b22[0][0].setEnabled(false);
                    break;
                case R.id.b2201:
                    if (PLAYER_X) {
                        b22[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b22[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 1;
                    }
                    b22[0][1].setEnabled(false);
                    break;
                case R.id.b2202:
                    if (PLAYER_X) {
                        b22[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b22[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 2;
                    }
                    b22[0][2].setEnabled(false);
                    break;
                case R.id.b2210:
                    if (PLAYER_X) {
                        b22[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b22[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 3;
                    }
                    b22[1][0].setEnabled(false);
                    break;
                case R.id.b2211:
                    if (PLAYER_X) {
                        b22[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b22[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 4;
                    }
                    b22[1][1].setEnabled(false);
                    break;
                case R.id.b2212:
                    if (PLAYER_X) {
                        b22[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b22[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 5;
                    }
                    b22[1][2].setEnabled(false);
                    break;
                case R.id.b2220:
                    if (PLAYER_X) {
                        b22[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][6] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b22[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][6] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 6;
                    }
                    b22[2][0].setEnabled(false);
                    break;
                case R.id.b2221:
                    if (PLAYER_X) {
                        b22[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][7] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b22[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][7] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 7;
                    }
                    b22[2][1].setEnabled(false);
                    break;
                case R.id.b2222:
                    if (PLAYER_X) {
                        b22[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][8] = 1;
                        PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
                        enableBlock(PLAYER_X_BLOCK, false);
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b22[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][8] = 0;
                        PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
                        enableBlock(PLAYER_O_BLOCK, false);
                        PLAYER_O_BLOCK = 8;
                    }
                    b22[2][2].setEnabled(false);
                    break;
            }
        } else {
            switch (id) {
                case R.id.b0000:
                    if (PLAYER_X) {
                        b00[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][0] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b00[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][0] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b00[0][0].setEnabled(false);
                    break;
                case R.id.b0001:
                    if (PLAYER_X) {
                        b00[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][1] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b00[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][1] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b00[0][1].setEnabled(false);
                    break;
                case R.id.b0002:
                    if (PLAYER_X) {
                        b00[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][2] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b00[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][2] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b00[0][2].setEnabled(false);
                    break;
                case R.id.b0010:
                    if (PLAYER_X) {
                        b00[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][0] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b00[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][0] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b00[1][0].setEnabled(false);
                    break;
                case R.id.b0011:
                    if (PLAYER_X) {
                        b00[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][1] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b00[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][1] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b00[1][1].setEnabled(false);
                    break;
                case R.id.b0012:
                    if (PLAYER_X) {
                        b00[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][2] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b00[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][2] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b00[1][2].setEnabled(false);
                    break;
                case R.id.b0020:
                    if (PLAYER_X) {
                        b00[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][0] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b00[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][0] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b00[2][0].setEnabled(false);
                    break;
                case R.id.b0021:
                    if (PLAYER_X) {
                        b00[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][1] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b00[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][1] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b00[2][1].setEnabled(false);
                    break;
                case R.id.b0022:
                    if (PLAYER_X) {
                        b00[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][2] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b00[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][2] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b00[2][2].setEnabled(false);
                    break;
                case R.id.b0100:
                    if (PLAYER_X) {
                        b01[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][0] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b01[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][0] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b01[0][0].setEnabled(false);
                    break;
                case R.id.b0101:
                    if (PLAYER_X) {
                        b01[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][1] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b01[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][1] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b01[0][1].setEnabled(false);
                    break;
                case R.id.b0102:
                    if (PLAYER_X) {
                        b01[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][2] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b01[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][2] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b01[0][2].setEnabled(false);
                    break;
                case R.id.b0110:
                    if (PLAYER_X) {
                        b01[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][0] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b01[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][0] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b01[1][0].setEnabled(false);
                    break;
                case R.id.b0111:
                    if (PLAYER_X) {
                        b01[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][1] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b01[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][1] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b01[1][1].setEnabled(false);
                    break;
                case R.id.b0112:
                    if (PLAYER_X) {
                        b01[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][2] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b01[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][2] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b01[1][2].setEnabled(false);
                    break;
                case R.id.b0120:
                    if (PLAYER_X) {
                        b01[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][0] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b01[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][0] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b01[2][0].setEnabled(false);
                    break;
                case R.id.b0121:
                    if (PLAYER_X) {
                        b01[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][1] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b01[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][1] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b01[2][1].setEnabled(false);
                    break;
                case R.id.b0122:
                    if (PLAYER_X) {
                        b01[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][2] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b01[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][2] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b01[2][2].setEnabled(false);
                    break;
                case R.id.b0200:
                    if (PLAYER_X) {
                        b02[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][0] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b02[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][0] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b02[0][0].setEnabled(false);
                    break;
                case R.id.b0201:
                    if (PLAYER_X) {
                        b02[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][1] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b02[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][1] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b02[0][1].setEnabled(false);
                    break;
                case R.id.b0202:
                    if (PLAYER_X) {
                        b02[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][2] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b02[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][2] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b02[0][2].setEnabled(false);
                    break;
                case R.id.b0210:
                    if (PLAYER_X) {
                        b02[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][0] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b02[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][0] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b02[1][0].setEnabled(false);
                    break;
                case R.id.b0211:
                    if (PLAYER_X) {
                        b02[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][1] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b02[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][1] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b02[1][1].setEnabled(false);
                    break;
                case R.id.b0212:
                    if (PLAYER_X) {
                        b02[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][2] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b02[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][2] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b02[1][2].setEnabled(false);
                    break;
                case R.id.b0220:
                    if (PLAYER_X) {
                        b02[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][0] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b02[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][0] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b02[2][0].setEnabled(false);
                    break;
                case R.id.b0221:
                    if (PLAYER_X) {
                        b02[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][1] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b02[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][1] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b02[2][1].setEnabled(false);
                    break;
                case R.id.b0222:
                    if (PLAYER_X) {
                        b02[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][2] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b02[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][2] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b02[2][2].setEnabled(false);
                    break;
                case R.id.b1000:
                    if (PLAYER_X) {
                        b10[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][3] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b10[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][3] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b10[0][0].setEnabled(false);
                    break;
                case R.id.b1001:
                    if (PLAYER_X) {
                        b10[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][4] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b10[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][4] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b10[0][1].setEnabled(false);
                    break;
                case R.id.b1002:
                    if (PLAYER_X) {
                        b10[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][5] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b10[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][5] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b10[0][2].setEnabled(false);
                    break;
                case R.id.b1010:
                    if (PLAYER_X) {
                        b10[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][3] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b10[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][3] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b10[1][0].setEnabled(false);
                    break;
                case R.id.b1011:
                    if (PLAYER_X) {
                        b10[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][4] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b10[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][4] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b10[1][1].setEnabled(false);
                    break;
                case R.id.b1012:
                    if (PLAYER_X) {
                        b10[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][5] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b10[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][5] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b10[1][2].setEnabled(false);
                    break;
                case R.id.b1020:
                    if (PLAYER_X) {
                        b10[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][3] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b10[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][3] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b10[2][0].setEnabled(false);
                    break;
                case R.id.b1021:
                    if (PLAYER_X) {
                        b10[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][4] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b10[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][4] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b10[2][1].setEnabled(false);
                    break;
                case R.id.b1022:
                    if (PLAYER_X) {
                        b10[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][5] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b10[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][5] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b10[2][2].setEnabled(false);
                    break;
                case R.id.b1100:
                    if (PLAYER_X) {
                        b11[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][3] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b11[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][3] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b11[0][0].setEnabled(false);
                    break;
                case R.id.b1101:
                    if (PLAYER_X) {
                        b11[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][4] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b11[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][4] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b11[0][1].setEnabled(false);
                    break;
                case R.id.b1102:
                    if (PLAYER_X) {
                        b11[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][5] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b11[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][5] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b11[0][2].setEnabled(false);
                    break;
                case R.id.b1110:
                    if (PLAYER_X) {
                        b11[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][3] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b11[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][3] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b11[1][0].setEnabled(false);
                    break;
                case R.id.b1111:
                    if (PLAYER_X) {
                        b11[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][4] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b11[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][4] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b11[1][1].setEnabled(false);
                    break;
                case R.id.b1112:
                    if (PLAYER_X) {
                        b11[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][5] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b11[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][5] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b11[1][2].setEnabled(false);
                    break;
                case R.id.b1120:
                    if (PLAYER_X) {
                        b11[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][3] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b11[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][3] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b11[2][0].setEnabled(false);
                    break;
                case R.id.b1121:
                    if (PLAYER_X) {
                        b11[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][4] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b11[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][4] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b11[2][1].setEnabled(false);
                    break;
                case R.id.b1122:
                    if (PLAYER_X) {
                        b11[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][5] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b11[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][5] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b11[2][2].setEnabled(false);
                    break;
                case R.id.b1200:
                    if (PLAYER_X) {
                        b12[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][3] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b12[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][3] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b12[0][0].setEnabled(false);
                    break;
                case R.id.b1201:
                    if (PLAYER_X) {
                        b12[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][4] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b12[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][4] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b12[0][1].setEnabled(false);
                    break;
                case R.id.b1202:
                    if (PLAYER_X) {
                        b12[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][5] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b12[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][5] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b12[0][2].setEnabled(false);
                    break;
                case R.id.b1210:
                    if (PLAYER_X) {
                        b12[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][3] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b12[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][3] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b12[1][0].setEnabled(false);
                    break;
                case R.id.b1211:
                    if (PLAYER_X) {
                        b12[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][4] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b12[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][4] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b12[1][1].setEnabled(false);
                    break;
                case R.id.b1212:
                    if (PLAYER_X) {
                        b12[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][5] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b12[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][5] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b12[1][2].setEnabled(false);
                    break;
                case R.id.b1220:
                    if (PLAYER_X) {
                        b12[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][3] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b12[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][3] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b12[2][0].setEnabled(false);
                    break;
                case R.id.b1221:
                    if (PLAYER_X) {
                        b12[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][4] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b12[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][4] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b12[2][1].setEnabled(false);
                    break;
                case R.id.b1222:
                    if (PLAYER_X) {
                        b12[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][5] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b12[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][5] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b12[2][2].setEnabled(false);
                    break;
                case R.id.b2000:
                    if (PLAYER_X) {
                        b20[0][0].setImageResource(R.drawable.x);
                        boardStatus[0][6] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b20[0][0].setImageResource(R.drawable.o);
                        boardStatus[0][6] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b20[0][0].setEnabled(false);
                    break;
                case R.id.b2001:
                    if (PLAYER_X) {
                        b20[0][1].setImageResource(R.drawable.x);
                        boardStatus[0][7] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b20[0][1].setImageResource(R.drawable.o);
                        boardStatus[0][7] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b20[0][1].setEnabled(false);
                    break;
                case R.id.b2002:
                    if (PLAYER_X) {
                        b20[0][2].setImageResource(R.drawable.x);
                        boardStatus[0][8] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b20[0][2].setImageResource(R.drawable.o);
                        boardStatus[0][8] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b20[0][2].setEnabled(false);
                    break;
                case R.id.b2010:
                    if (PLAYER_X) {
                        b20[1][0].setImageResource(R.drawable.x);
                        boardStatus[1][6] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b20[1][0].setImageResource(R.drawable.o);
                        boardStatus[1][6] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b20[1][0].setEnabled(false);
                    break;
                case R.id.b2011:
                    if (PLAYER_X) {
                        b20[1][1].setImageResource(R.drawable.x);
                        boardStatus[1][7] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b20[1][1].setImageResource(R.drawable.o);
                        boardStatus[1][7] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b20[1][1].setEnabled(false);
                    break;
                case R.id.b2012:
                    if (PLAYER_X) {
                        b20[1][2].setImageResource(R.drawable.x);
                        boardStatus[1][8] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b20[1][2].setImageResource(R.drawable.o);
                        boardStatus[1][8] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b20[1][2].setEnabled(false);
                    break;
                case R.id.b2020:
                    if (PLAYER_X) {
                        b20[2][0].setImageResource(R.drawable.x);
                        boardStatus[2][6] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b20[2][0].setImageResource(R.drawable.o);
                        boardStatus[2][6] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b20[2][0].setEnabled(false);
                    break;
                case R.id.b2021:
                    if (PLAYER_X) {
                        b20[2][1].setImageResource(R.drawable.x);
                        boardStatus[2][7] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b20[2][1].setImageResource(R.drawable.o);
                        boardStatus[2][7] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b20[2][1].setEnabled(false);
                    break;
                case R.id.b2022:
                    if (PLAYER_X) {
                        b20[2][2].setImageResource(R.drawable.x);
                        boardStatus[2][8] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b20[2][2].setImageResource(R.drawable.o);
                        boardStatus[2][8] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b20[2][2].setEnabled(false);
                    break;
                case R.id.b2100:
                    if (PLAYER_X) {
                        b21[0][0].setImageResource(R.drawable.x);
                        boardStatus[3][6] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b21[0][0].setImageResource(R.drawable.o);
                        boardStatus[3][6] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b21[0][0].setEnabled(false);
                    break;
                case R.id.b2101:
                    if (PLAYER_X) {
                        b21[0][1].setImageResource(R.drawable.x);
                        boardStatus[3][7] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b21[0][1].setImageResource(R.drawable.o);
                        boardStatus[3][7] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b21[0][1].setEnabled(false);
                    break;
                case R.id.b2102:
                    if (PLAYER_X) {
                        b21[0][2].setImageResource(R.drawable.x);
                        boardStatus[3][8] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b21[0][2].setImageResource(R.drawable.o);
                        boardStatus[3][8] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b21[0][2].setEnabled(false);
                    break;
                case R.id.b2110:
                    if (PLAYER_X) {
                        b21[1][0].setImageResource(R.drawable.x);
                        boardStatus[4][6] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b21[1][0].setImageResource(R.drawable.o);
                        boardStatus[4][6] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b21[1][0].setEnabled(false);
                    break;
                case R.id.b2111:
                    if (PLAYER_X) {
                        b21[1][1].setImageResource(R.drawable.x);
                        boardStatus[4][7] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b21[1][1].setImageResource(R.drawable.o);
                        boardStatus[4][7] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b21[1][1].setEnabled(false);
                    break;
                case R.id.b2112:
                    if (PLAYER_X) {
                        b21[1][2].setImageResource(R.drawable.x);
                        boardStatus[4][8] = 1;
                        PLAYER_X_BLOCK = 5;

                    } else {
                        b21[1][2].setImageResource(R.drawable.o);
                        boardStatus[4][8] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b21[1][2].setEnabled(false);
                    break;
                case R.id.b2120:
                    if (PLAYER_X) {
                        b21[2][0].setImageResource(R.drawable.x);
                        boardStatus[5][6] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b21[2][0].setImageResource(R.drawable.o);
                        boardStatus[5][6] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b21[2][0].setEnabled(false);
                    break;
                case R.id.b2121:
                    if (PLAYER_X) {
                        b21[2][1].setImageResource(R.drawable.x);
                        boardStatus[5][7] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b21[2][1].setImageResource(R.drawable.o);
                        boardStatus[5][7] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b21[2][1].setEnabled(false);
                    break;
                case R.id.b2122:
                    if (PLAYER_X) {
                        b21[2][2].setImageResource(R.drawable.x);
                        boardStatus[5][8] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b21[2][2].setImageResource(R.drawable.o);
                        boardStatus[5][8] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b21[2][2].setEnabled(false);
                    break;
                case R.id.b2200:
                    if (PLAYER_X) {
                        b22[0][0].setImageResource(R.drawable.x);
                        boardStatus[6][6] = 1;
                        PLAYER_X_BLOCK = 0;
                    } else {
                        b22[0][0].setImageResource(R.drawable.o);
                        boardStatus[6][6] = 0;
                        PLAYER_O_BLOCK = 0;
                    }
                    b22[0][0].setEnabled(false);
                    break;
                case R.id.b2201:
                    if (PLAYER_X) {
                        b22[0][1].setImageResource(R.drawable.x);
                        boardStatus[6][7] = 1;
                        PLAYER_X_BLOCK = 1;
                    } else {
                        b22[0][1].setImageResource(R.drawable.o);
                        boardStatus[6][7] = 0;
                        PLAYER_O_BLOCK = 1;
                    }
                    b22[0][1].setEnabled(false);
                    break;
                case R.id.b2202:
                    if (PLAYER_X) {
                        b22[0][2].setImageResource(R.drawable.x);
                        boardStatus[6][8] = 1;
                        PLAYER_X_BLOCK = 2;
                    } else {
                        b22[0][2].setImageResource(R.drawable.o);
                        boardStatus[6][8] = 0;
                        PLAYER_O_BLOCK = 2;
                    }
                    b22[0][2].setEnabled(false);
                    break;
                case R.id.b2210:
                    if (PLAYER_X) {
                        b22[1][0].setImageResource(R.drawable.x);
                        boardStatus[7][6] = 1;
                        PLAYER_X_BLOCK = 3;
                    } else {
                        b22[1][0].setImageResource(R.drawable.o);
                        boardStatus[7][6] = 0;
                        PLAYER_O_BLOCK = 3;
                    }
                    b22[1][0].setEnabled(false);
                    break;
                case R.id.b2211:
                    if (PLAYER_X) {
                        b22[1][1].setImageResource(R.drawable.x);
                        boardStatus[7][7] = 1;
                        PLAYER_X_BLOCK = 4;
                    } else {
                        b22[1][1].setImageResource(R.drawable.o);
                        boardStatus[7][7] = 0;
                        PLAYER_O_BLOCK = 4;
                    }
                    b22[1][1].setEnabled(false);
                    break;
                case R.id.b2212:
                    if (PLAYER_X) {
                        b22[1][2].setImageResource(R.drawable.x);
                        boardStatus[7][8] = 1;
                        PLAYER_X_BLOCK = 5;
                    } else {
                        b22[1][2].setImageResource(R.drawable.o);
                        boardStatus[7][8] = 0;
                        PLAYER_O_BLOCK = 5;
                    }
                    b22[1][2].setEnabled(false);
                    break;
                case R.id.b2220:
                    if (PLAYER_X) {
                        b22[2][0].setImageResource(R.drawable.x);
                        boardStatus[8][6] = 1;
                        PLAYER_X_BLOCK = 6;
                    } else {
                        b22[2][0].setImageResource(R.drawable.o);
                        boardStatus[8][6] = 0;
                        PLAYER_O_BLOCK = 6;
                    }
                    b22[2][0].setEnabled(false);
                    break;
                case R.id.b2221:
                    if (PLAYER_X) {
                        b22[2][1].setImageResource(R.drawable.x);
                        boardStatus[8][7] = 1;
                        PLAYER_X_BLOCK = 7;
                    } else {
                        b22[2][1].setImageResource(R.drawable.o);
                        boardStatus[8][7] = 0;
                        PLAYER_O_BLOCK = 7;
                    }
                    b22[2][1].setEnabled(false);
                    break;
                case R.id.b2222:
                    if (PLAYER_X) {
                        b22[2][2].setImageResource(R.drawable.x);
                        boardStatus[8][8] = 1;
                        PLAYER_X_BLOCK = 8;
                    } else {
                        b22[2][2].setImageResource(R.drawable.o);
                        boardStatus[8][8] = 0;
                        PLAYER_O_BLOCK = 8;
                    }
                    b22[2][2].setEnabled(false);
                    break;
            }
            if (TURN_COUNT == 1) {
                enableAll(false);
            }
        }


        if (id == R.id.bReset) {

            reset.start();
            resetBoard();

        } else if (id == R.id.bUndo) {

            undo.start();
            undoBoard();

        } else {

            click.start();

            if (PLAYER_X) {
                PREVIOUS_X_BLOCK = PLAYER_X_BLOCK;
            } else {
                PREVIOUS_O_BLOCK = PLAYER_O_BLOCK;
            }

            PREVIOUS_BUTTON = id;

            TURN_COUNT++;

            if (PLAYER_X) {
                if (PREVIOUS_O_BLOCK != 0)
                    //if(blockStatus[0][0])
                    tl00.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 1)
                    //if(blockStatus[0][1])
                    tl01.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 2)
                    //if(blockStatus[0][2])
                    tl02.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 3)
                    //if(blockStatus[1][0])
                    tl10.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 4)
                    //if(blockStatus[1][1])
                    tl11.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 5)
                    //if(blockStatus[1][2])
                    tl12.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 6)
                    //if(blockStatus[2][0])
                    tl20.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 7)
                    //if(blockStatus[2][1])
                    tl21.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_O_BLOCK != 8)
                    //if(blockStatus[2][2])
                    tl22.setBackgroundColor(Color.BLACK);

                switch (PLAYER_X_BLOCK) {
                    case 0:
                        tl00.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        tl01.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        tl02.setBackgroundColor(Color.RED);
                        break;
                    case 3:
                        tl10.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        tl11.setBackgroundColor(Color.RED);
                        break;
                    case 5:
                        tl12.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        tl20.setBackgroundColor(Color.RED);
                        break;
                    case 7:
                        tl21.setBackgroundColor(Color.RED);
                        break;
                    case 8:
                        tl22.setBackgroundColor(Color.RED);
                        break;
                }
            } else {
                if (PREVIOUS_X_BLOCK != 0)
                    tl00.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 1)
                    tl01.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 2)
                    tl02.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 3)
                    tl10.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 4)
                    tl11.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 5)
                    tl12.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 6)
                    tl20.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 7)
                    tl21.setBackgroundColor(Color.BLACK);
                if (PREVIOUS_X_BLOCK != 8)
                    tl22.setBackgroundColor(Color.BLACK);

                switch (PLAYER_O_BLOCK) {
                    case 0:
                        tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 1:
                        tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 2:
                        tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 3:
                        tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 4:
                        tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 5:
                        tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 6:
                        tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 7:
                        tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                    case 8:
                        tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                        break;
                }
            }

            PLAYER_X = !PLAYER_X;

            if (PLAYER_X) {
                if (bad) {
                    enableAll(false);
                    bad = false;
                }
                badCase(PLAYER_X_BLOCK);
                enableBlock(PLAYER_X_BLOCK, true);
                setInfo(name1 + "'s turn");
            } else {
                if (bad) {
                    enableAll(false);
                    bad = false;
                }
                badCase(PLAYER_O_BLOCK);
                enableBlock(PLAYER_O_BLOCK, true);
                setInfo(name2 + "'s turn");
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (blockStatus[i][j]) {
                        checkWinnerBlock(i * 3 + j);
                    }
                }
            }

            if (TURN_COUNT > 1)
                bUndo.setEnabled(true);
            else
                bUndo.setEnabled(false);
        }
    }

    private void undoBoard() {

        //undo turn count

        TURN_COUNT--;

        //undo set Text and disable block
        /*if(PLAYER_X) {
            enableBlock(PLAYER_X_BLOCK, false);
            enableBlock(PREVIOUS_X_BLOCK, true);
            //setInfo(name1 + "'s turn");
            setInfo("Enabled "+PREVIOUS_X_BLOCK+", Disabled "+PLAYER_X_BLOCK);
        } else {
            enableBlock(PLAYER_O_BLOCK, false);
            enableBlock(PREVIOUS_O_BLOCK, true);
            //setInfo(name2 + "'s turn");
            setInfo("Enabled "+PREVIOUS_O_BLOCK+", Disabled "+PLAYER_O_BLOCK);
        }*/

        enableAll(false);

        //undo chance
        PLAYER_X = !PLAYER_X;

        //undo color block
        if (PLAYER_X) {
            if (PREVIOUS_O_BLOCK != 0)
                //if(blockStatus[0][0])
                tl00.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 1)
                //if(blockStatus[0][1])
                tl01.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 2)
                //if(blockStatus[0][2])
                tl02.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 3)
                //if(blockStatus[1][0])
                tl10.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 4)
                //if(blockStatus[1][1])
                tl11.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 5)
                //if(blockStatus[1][2])
                tl12.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 6)
                //if(blockStatus[2][0])
                tl20.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 7)
                //if(blockStatus[2][1])
                tl21.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_O_BLOCK != 8)
                //if(blockStatus[2][2])
                tl22.setBackgroundColor(Color.BLACK);
        } else {
            if (PREVIOUS_X_BLOCK != 0)
                tl00.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 1)
                tl01.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 2)
                tl02.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 3)
                tl10.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 4)
                tl11.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 5)
                tl12.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 6)
                tl20.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 7)
                tl21.setBackgroundColor(Color.BLACK);
            if (PREVIOUS_X_BLOCK != 8)
                tl22.setBackgroundColor(Color.BLACK);
        }

        //undo button click and undo BoardStatus
        switch (PREVIOUS_BUTTON) {
            case R.id.b0000:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[0][0].setImageResource(R.drawable.white);
                boardStatus[0][0] = -1;
                b00[0][0].setEnabled(true);
                enableBlock(0, true);

                break;
            case R.id.b0001:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[0][1].setImageResource(R.drawable.white);
                boardStatus[0][1] = -1;
                b00[0][1].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0002:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[0][2].setImageResource(R.drawable.white);
                boardStatus[0][2] = -1;
                b00[0][2].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0010:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[1][0].setImageResource(R.drawable.white);
                boardStatus[1][0] = -1;
                b00[1][0].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0011:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[1][1].setImageResource(R.drawable.white);
                boardStatus[1][1] = -1;
                b00[1][1].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0012:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[1][2].setImageResource(R.drawable.white);
                boardStatus[1][2] = -1;
                b00[1][2].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0020:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl00.setBackgroundColor(Color.RED);

                } else {
                    PLAYER_O_BLOCK = 6;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[2][0].setImageResource(R.drawable.white);
                boardStatus[2][0] = -1;
                b00[2][0].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0021:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[2][1].setImageResource(R.drawable.white);
                boardStatus[2][1] = -1;
                b00[2][1].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0022:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl00.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl00.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b00[2][2].setImageResource(R.drawable.white);
                boardStatus[2][2] = -1;
                b00[2][2].setEnabled(true);
                enableBlock(0, true);
                break;
            case R.id.b0100:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[0][0].setImageResource(R.drawable.white);
                boardStatus[3][0] = -1;
                b01[0][0].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0101:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[0][1].setImageResource(R.drawable.white);
                boardStatus[3][1] = -1;
                b01[0][1].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0102:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl01.setBackgroundColor(Color.RED);

                } else {
                    PLAYER_O_BLOCK = 2;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[0][2].setImageResource(R.drawable.white);
                boardStatus[3][2] = -1;
                b01[0][2].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0110:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[1][0].setImageResource(R.drawable.white);
                boardStatus[4][0] = -1;
                b01[1][0].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0111:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[1][1].setImageResource(R.drawable.white);
                boardStatus[4][1] = -1;
                b01[1][1].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0112:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[1][2].setImageResource(R.drawable.white);
                boardStatus[4][2] = -1;
                b01[1][2].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0120:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[2][0].setImageResource(R.drawable.white);
                boardStatus[5][0] = -1;
                b01[2][0].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0121:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[2][1].setImageResource(R.drawable.white);
                boardStatus[5][1] = -1;
                b01[2][1].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0122:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl01.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl01.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b01[2][2].setImageResource(R.drawable.white);
                boardStatus[5][2] = -1;
                b01[2][2].setEnabled(true);
                enableBlock(1, true);
                break;
            case R.id.b0200:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[0][0].setImageResource(R.drawable.white);
                boardStatus[6][0] = -1;
                b02[0][0].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0201:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[0][1].setImageResource(R.drawable.white);
                boardStatus[6][1] = -1;
                b02[0][1].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0202:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[0][2].setImageResource(R.drawable.white);
                boardStatus[6][2] = -1;
                b02[0][2].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0210:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[1][0].setImageResource(R.drawable.white);
                boardStatus[7][0] = -1;
                b02[1][0].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0211:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[1][1].setImageResource(R.drawable.white);
                boardStatus[7][1] = -1;
                b02[1][1].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0212:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[1][2].setImageResource(R.drawable.white);
                boardStatus[7][2] = -1;
                b02[1][2].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0220:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[2][0].setImageResource(R.drawable.white);
                boardStatus[8][0] = -1;
                b02[2][0].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0221:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[2][1].setImageResource(R.drawable.white);
                boardStatus[8][1] = -1;
                b02[2][1].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b0222:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl02.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl02.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b02[2][2].setImageResource(R.drawable.white);
                boardStatus[8][2] = -1;
                b02[2][2].setEnabled(true);
                enableBlock(2, true);
                break;
            case R.id.b1000:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[0][0].setImageResource(R.drawable.white);
                boardStatus[0][3] = -1;
                b10[0][0].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1001:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[0][1].setImageResource(R.drawable.white);
                boardStatus[0][4] = -1;
                b10[0][1].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1002:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[0][2].setImageResource(R.drawable.white);
                boardStatus[0][5] = -1;
                b10[0][2].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1010:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[1][0].setImageResource(R.drawable.white);
                boardStatus[1][3] = -1;
                b10[1][0].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1011:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[1][1].setImageResource(R.drawable.white);
                boardStatus[1][4] = -1;
                b10[1][1].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1012:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[1][2].setImageResource(R.drawable.white);
                boardStatus[1][5] = -1;
                b10[1][2].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1020:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[2][0].setImageResource(R.drawable.white);
                boardStatus[2][3] = -1;
                b10[2][0].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1021:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[2][1].setImageResource(R.drawable.white);
                boardStatus[2][4] = -1;
                b10[2][1].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1022:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl10.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl10.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b10[2][2].setImageResource(R.drawable.white);
                boardStatus[2][5] = -1;
                b10[2][2].setEnabled(true);
                enableBlock(3, true);
                break;
            case R.id.b1100:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[0][0].setImageResource(R.drawable.white);
                boardStatus[3][3] = -1;
                b11[0][0].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1101:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[0][1].setImageResource(R.drawable.white);
                boardStatus[3][4] = -1;
                b11[0][1].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1102:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[0][2].setImageResource(R.drawable.white);
                boardStatus[3][5] = -1;
                b11[0][2].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1110:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[1][0].setImageResource(R.drawable.white);
                boardStatus[4][3] = -1;
                b11[1][0].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1111:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[1][1].setImageResource(R.drawable.white);
                boardStatus[4][4] = -1;
                b11[1][1].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1112:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[1][2].setImageResource(R.drawable.white);
                boardStatus[4][5] = -1;
                b11[1][2].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1120:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[2][0].setImageResource(R.drawable.white);
                boardStatus[5][3] = -1;
                b11[2][0].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1121:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[2][1].setImageResource(R.drawable.white);
                boardStatus[5][4] = -1;
                b11[2][1].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1122:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl11.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl11.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b11[2][2].setImageResource(R.drawable.white);
                boardStatus[5][5] = -1;
                b11[2][2].setEnabled(true);
                enableBlock(4, true);
                break;
            case R.id.b1200:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[0][0].setImageResource(R.drawable.white);
                boardStatus[6][3] = -1;
                b12[0][0].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1201:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[0][1].setImageResource(R.drawable.white);
                boardStatus[6][4] = -1;
                b12[0][1].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1202:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[0][2].setImageResource(R.drawable.white);
                boardStatus[6][5] = -1;
                b12[0][2].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1210:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[1][0].setImageResource(R.drawable.white);
                boardStatus[7][3] = -1;
                b12[1][0].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1211:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[1][1].setImageResource(R.drawable.white);
                boardStatus[7][4] = -1;
                b12[1][1].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1212:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[1][2].setImageResource(R.drawable.white);
                boardStatus[7][5] = -1;
                b12[1][2].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1220:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[2][0].setImageResource(R.drawable.white);
                boardStatus[8][3] = -1;
                b12[2][0].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1221:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[2][1].setImageResource(R.drawable.white);
                boardStatus[8][4] = -1;
                b12[2][1].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b1222:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl12.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl12.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b12[2][2].setImageResource(R.drawable.white);
                boardStatus[8][5] = -1;
                b12[2][2].setEnabled(true);
                enableBlock(5, true);
                break;
            case R.id.b2000:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[0][0].setImageResource(R.drawable.white);
                boardStatus[0][6] = -1;
                b20[0][0].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2001:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[0][1].setImageResource(R.drawable.white);
                boardStatus[0][7] = -1;
                b20[0][1].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2002:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[0][2].setImageResource(R.drawable.white);
                boardStatus[0][8] = -1;
                b20[0][2].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2010:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[1][0].setImageResource(R.drawable.white);
                boardStatus[1][6] = -1;
                b20[1][0].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2011:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[1][1].setImageResource(R.drawable.white);
                boardStatus[1][7] = -1;
                b20[1][1].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2012:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[1][2].setImageResource(R.drawable.white);
                boardStatus[1][8] = -1;
                b20[1][2].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2020:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[2][0].setImageResource(R.drawable.white);
                boardStatus[2][6] = -1;
                b20[2][0].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2021:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[2][1].setImageResource(R.drawable.white);
                boardStatus[2][7] = -1;
                b20[2][1].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2022:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl20.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl20.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b20[2][2].setImageResource(R.drawable.white);
                boardStatus[2][8] = -1;
                b20[2][2].setEnabled(true);
                enableBlock(6, true);
                break;
            case R.id.b2100:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[0][0].setImageResource(R.drawable.white);
                boardStatus[3][6] = -1;
                b21[0][0].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2101:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[0][1].setImageResource(R.drawable.white);
                boardStatus[3][7] = -1;
                b21[0][1].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2102:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[0][2].setImageResource(R.drawable.white);
                boardStatus[3][8] = -1;
                b21[0][2].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2110:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[1][0].setImageResource(R.drawable.white);
                boardStatus[4][6] = -1;
                b21[1][0].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2111:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[1][1].setImageResource(R.drawable.white);
                boardStatus[4][7] = -1;
                b21[1][1].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2112:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[1][2].setImageResource(R.drawable.white);
                boardStatus[4][8] = -1;
                b21[1][2].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2120:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[2][0].setImageResource(R.drawable.white);
                boardStatus[5][6] = -1;
                b21[2][0].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2121:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[2][1].setImageResource(R.drawable.white);
                boardStatus[5][7] = -1;
                b21[2][1].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2122:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl21.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl21.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b21[2][2].setImageResource(R.drawable.white);
                boardStatus[5][8] = -1;
                b21[2][2].setEnabled(true);
                enableBlock(7, true);
                break;
            case R.id.b2200:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 0;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 0;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[0][0].setImageResource(R.drawable.white);
                boardStatus[6][6] = -1;
                b22[0][0].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2201:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 1;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 1;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[0][1].setImageResource(R.drawable.white);
                boardStatus[6][7] = -1;
                b22[0][1].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2202:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 2;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 2;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[0][2].setImageResource(R.drawable.white);
                boardStatus[6][8] = -1;
                b22[0][2].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2210:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 3;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 3;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[1][0].setImageResource(R.drawable.white);
                boardStatus[7][6] = -1;
                b22[1][0].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2211:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 4;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 4;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[1][1].setImageResource(R.drawable.white);
                boardStatus[7][7] = -1;
                b22[1][1].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2212:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 5;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 5;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[1][2].setImageResource(R.drawable.white);
                boardStatus[7][8] = -1;
                b22[1][2].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2220:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 6;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 6;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[2][0].setImageResource(R.drawable.white);
                boardStatus[8][6] = -1;
                b22[2][0].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2221:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 7;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 7;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[2][1].setImageResource(R.drawable.white);
                boardStatus[8][7] = -1;
                b22[2][1].setEnabled(true);
                enableBlock(8, true);
                break;
            case R.id.b2222:
                if (PLAYER_X) {
                    PLAYER_X_BLOCK = 8;
                    tl22.setBackgroundColor(Color.RED);
                } else {
                    PLAYER_O_BLOCK = 8;
                    tl22.setBackgroundColor(Color.rgb(148, 230, 239));
                }
                b22[2][2].setImageResource(R.drawable.white);
                boardStatus[8][8] = -1;
                b22[2][2].setEnabled(true);
                enableBlock(8, true);
                break;
        }

        //disable undo button
        bUndo.setEnabled(false);
    }

    private void checkWinnerGame() {
        if (WIN_X == 5) {
            block.stop();
            game.start();
            result(name1 + " wins!");
            enableAll(false);
        } else if (WIN_O == 5) {
            block.stop();
            game.start();
            result(name2 + " wins!");
            enableAll(false);
        } else if (TURN_COUNT == 81) {
            if (WIN_X > WIN_O) {
                block.stop();
                game.start();
                result(name1 + " wins!");
                enableAll(false);
            } else if (WIN_X < WIN_O) {
                block.stop();
                game.start();
                result(name2 + " wins!");
                enableAll(false);
            } else {
                result("Game Draw!");
            }
        }
    }

    private void enableBlock(int n, boolean value) {

        switch (n) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (boardStatus[i][j]==-1) {
                            b00[i][j].setEnabled(value);
                        }
                    }
                }
                break;
            case 1:
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (boardStatus[i][j]==-1) {
                            b01[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 2:
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (boardStatus[i][j]==-1) {
                            b02[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (boardStatus[i][j]==-1) {
                            b10[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 4:
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (boardStatus[i][j]==-1) {
                            b11[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 5:
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (boardStatus[i][j]==-1) {
                            b12[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 6:
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (boardStatus[i][j]==-1) {
                            b20[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 7:
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (boardStatus[i][j]==-1) {
                            b21[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
            case 8:
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (boardStatus[i][j]==-1) {
                            b22[i%3][j%3].setEnabled(value);
                        }
                    }
                }
                break;
        }
    }

    private void result(String s) {

        setInfo(s);

    }

    private void checkWinnerBlock(int n) {

        switch (n) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]) {
                        if (boardStatus[i][0] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][0] = false;
                            tl00.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][0] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][0] = false;
                            tl00.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 0; i < 3; i++) {
                    if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]) {
                        if (boardStatus[0][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][0] = false;
                            tl00.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[0][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][0] = false;
                            tl00.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2]) {
                    if (boardStatus[0][0] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][0] = false;
                        tl00.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][0] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][0] = false;
                        tl00.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0]) {
                    if (boardStatus[0][2] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][0] = false;
                        tl00.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][2] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][0] = false;
                        tl00.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 1:
                for (int i = 3; i < 6; i++) {
                    if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]) {
                        if (boardStatus[i][1] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][1] = false;
                            tl01.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][1] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][1] = false;
                            tl01.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 0; i < 3; i++) {
                    if (boardStatus[3][i] == boardStatus[4][i] && boardStatus[4][i] == boardStatus[5][i]) {
                        if (boardStatus[3][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][1] = false;
                            tl01.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[3][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][1] = false;
                            block.start();
                            tl01.setBackgroundColor(Color.rgb(200,15,15));
                            break;
                        }
                    }
                }

                if (boardStatus[3][0] == boardStatus[4][1] && boardStatus[4][1] == boardStatus[5][2]) {
                    if (boardStatus[3][0] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][1] = false;
                        block.start();
                        tl01.setBackgroundColor(Color.rgb(0, 229, 255));
                    } else if (boardStatus[3][0] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][1] = false;
                        tl01.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[3][2] == boardStatus[4][1] && boardStatus[4][1] == boardStatus[5][0]) {
                    if (boardStatus[3][2] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][1] = false;
                        tl01.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[3][2] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][1] = false;
                        tl01.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 2:
                for (int i = 6; i < 9; i++) {
                    if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]) {
                        if (boardStatus[i][0] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][2] = false;
                            tl02.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][0] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][2] = false;
                            tl02.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 0; i < 3; i++) {
                    if (boardStatus[6][i] == boardStatus[7][i] && boardStatus[7][i] == boardStatus[8][i]) {
                        if (boardStatus[6][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[0][2] = false;
                            tl02.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[6][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[0][2] = false;
                            tl02.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[6][0] == boardStatus[7][1] && boardStatus[7][1] == boardStatus[8][2]) {
                    if (boardStatus[6][0] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][2] = false;
                        tl02.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[6][0] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][2] = false;
                        tl02.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[6][2] == boardStatus[7][1] && boardStatus[7][1] == boardStatus[8][0]) {
                    if (boardStatus[6][2] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[0][2] = false;
                        tl02.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[6][2] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[0][2] = false;
                        tl02.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 3:
                for (int i = 0; i < 3; i++) {
                    if (boardStatus[i][3] == boardStatus[i][4] && boardStatus[i][4] == boardStatus[i][5]) {
                        if (boardStatus[i][3] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][0] = false;
                            tl10.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][3] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][0] = false;
                            tl10.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 3; i < 6; i++) {
                    if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]) {
                        if (boardStatus[0][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][0] = false;
                            tl10.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[0][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][0] = false;
                            tl10.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[0][3] == boardStatus[1][4] && boardStatus[1][4] == boardStatus[2][5]) {
                    if (boardStatus[0][3] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][0] = false;
                        tl10.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][3] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][0] = false;
                        tl10.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[0][5] == boardStatus[1][4] && boardStatus[1][4] == boardStatus[2][3]) {
                    if (boardStatus[0][5] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][0] = false;
                        tl10.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][5] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][0] = false;
                        tl10.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 4:
                for (int i = 3; i < 6; i++) {
                    if (boardStatus[i][3] == boardStatus[i][4] && boardStatus[i][4] == boardStatus[i][5]) {
                        if (boardStatus[i][3] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][1] = false;
                            tl11.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][3] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][1] = false;
                            tl11.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 3; i < 6; i++) {
                    if (boardStatus[3][i] == boardStatus[4][i] && boardStatus[4][i] == boardStatus[5][i]) {
                        if (boardStatus[3][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][1] = false;
                            tl11.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[3][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][1] = false;
                            tl11.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[3][3] == boardStatus[4][4] && boardStatus[4][4] == boardStatus[5][5]) {
                    if (boardStatus[3][3] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][1] = false;
                        tl11.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[3][3] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][1] = false;
                        tl11.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[5][3] == boardStatus[4][4] && boardStatus[4][4] == boardStatus[3][5]) {
                    if (boardStatus[5][3] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][1] = false;
                        tl11.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[5][3] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][1] = false;
                        tl11.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 5:
                for (int i = 6; i < 9; i++) {
                    if (boardStatus[i][3] == boardStatus[i][4] && boardStatus[i][4] == boardStatus[i][5]) {
                        if (boardStatus[i][3] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][2] = false;
                            tl12.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][3] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][2] = false;
                            tl12.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 3; i < 6; i++) {
                    if (boardStatus[6][i] == boardStatus[7][i] && boardStatus[7][i] == boardStatus[8][i]) {
                        if (boardStatus[6][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[1][2] = false;
                            tl12.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[6][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[1][2] = false;
                            tl12.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[6][3] == boardStatus[7][4] && boardStatus[7][4] == boardStatus[8][5]) {
                    if (boardStatus[6][3] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][2] = false;
                        block.start();
                        tl12.setBackgroundColor(Color.rgb(0, 229, 255));
                    } else if (boardStatus[6][3] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][2] = false;
                        block.start();
                        tl12.setBackgroundColor(Color.rgb(200,15,15));
                    }
                }

                if (boardStatus[8][3] == boardStatus[7][4] && boardStatus[7][4] == boardStatus[6][5]) {
                    if (boardStatus[8][3] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[1][2] = false;
                        tl12.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[8][3] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[1][2] = false;
                        tl12.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 6:
                for (int i = 0; i < 3; i++) {
                    if (boardStatus[i][6] == boardStatus[i][7] && boardStatus[i][7] == boardStatus[i][8]) {
                        if (boardStatus[i][6] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][0] = false;
                            tl20.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][6] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][0] = false;
                            tl20.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 6; i < 9; i++) {
                    if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]) {
                        if (boardStatus[0][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][0] = false;
                            tl20.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[0][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][0] = false;
                            tl20.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[0][6] == boardStatus[1][7] && boardStatus[1][7] == boardStatus[2][8]) {
                    if (boardStatus[0][6] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][0] = false;
                        tl20.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][6] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][0] = false;
                        tl20.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[0][8] == boardStatus[1][7] && boardStatus[1][7] == boardStatus[2][6]) {
                    if (boardStatus[0][8] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][0] = false;
                        tl20.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[0][8] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][0] = false;
                        tl20.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;

            case 7:
                for (int i = 3; i < 6; i++) {
                    if (boardStatus[i][6] == boardStatus[i][7] && boardStatus[i][7] == boardStatus[i][8]) {
                        if (boardStatus[i][6] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][1] = false;
                            tl21.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][6] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][1] = false;
                            tl21.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 6; i < 9; i++) {
                    if (boardStatus[3][i] == boardStatus[4][i] && boardStatus[4][i] == boardStatus[5][i]) {
                        if (boardStatus[3][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][1] = false;
                            tl21.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[3][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][1] = false;
                            tl21.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[3][6] == boardStatus[4][7] && boardStatus[4][7] == boardStatus[5][8]) {
                    if (boardStatus[3][6] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][1] = false;
                        tl21.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[3][6] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][1] = false;
                        tl21.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[5][6] == boardStatus[4][7] && boardStatus[4][7] == boardStatus[3][8]) {
                    if (boardStatus[5][6] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][1] = false;
                        tl21.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[5][6] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][1] = false;
                        tl21.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    }
                }
                break;

            case 8:
                for (int i = 6; i < 9; i++) {
                    if (boardStatus[i][6] == boardStatus[i][7] && boardStatus[i][7] == boardStatus[i][8]) {
                        if (boardStatus[i][6] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][2] = false;
                            tl22.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[i][6] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][2] = false;
                            tl22.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                for (int i = 6; i < 9; i++) {
                    if (boardStatus[6][i] == boardStatus[7][i] && boardStatus[7][i] == boardStatus[8][i]) {
                        if (boardStatus[6][i] == 0) {
                            result(name2 + " Wins!");
                            WIN_O++;
                            tvColor2.setText("  " + WIN_O + "  ");
                            checkWinnerGame();
                            blockStatus[2][2] = false;
                            tl22.setBackgroundColor(Color.rgb(0, 229, 255));
                            block.start();
                            break;
                        } else if (boardStatus[6][i] == 1) {
                            result(name1 + " wins!");
                            WIN_X++;
                            tvColor1.setText("  " + WIN_X + "  ");
                            checkWinnerGame();
                            blockStatus[2][2] = false;
                            tl22.setBackgroundColor(Color.rgb(200,15,15));
                            block.start();
                            break;
                        }
                    }
                }

                if (boardStatus[6][6] == boardStatus[7][7] && boardStatus[7][7] == boardStatus[8][8]) {
                    if (boardStatus[6][6] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][2] = false;
                        tl22.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[6][6] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][2] = false;
                        tl22.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }

                if (boardStatus[8][6] == boardStatus[7][7] && boardStatus[7][7] == boardStatus[6][8]) {
                    if (boardStatus[8][6] == 0) {
                        result(name2 + " Wins!");
                        WIN_O++;
                        tvColor2.setText("  " + WIN_O + "  ");
                        checkWinnerGame();
                        blockStatus[2][2] = false;
                        tl22.setBackgroundColor(Color.rgb(0, 229, 255));
                        block.start();
                    } else if (boardStatus[8][6] == 1) {
                        result(name1 + " wins!");
                        WIN_X++;
                        tvColor1.setText("  " + WIN_X + "  ");
                        checkWinnerGame();
                        blockStatus[2][2] = false;
                        tl22.setBackgroundColor(Color.rgb(200,15,15));
                        block.start();
                    }
                }
                break;
        }
    }

    private void resetBoard() {

        initializeBoard();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                b00[i][k].setImageResource(R.drawable.white);
                b01[i][k].setImageResource(R.drawable.white);
                b02[i][k].setImageResource(R.drawable.white);

                b10[i][k].setImageResource(R.drawable.white);
                b11[i][k].setImageResource(R.drawable.white);
                b12[i][k].setImageResource(R.drawable.white);

                b20[i][k].setImageResource(R.drawable.white);
                b21[i][k].setImageResource(R.drawable.white);
                b22[i][k].setImageResource(R.drawable.white);
            }
        }

        enableAll(true);

        PLAYER_X = true;

        setInfo("Play Again!");

        TURN_COUNT = 0;

        tl00.setBackgroundColor(Color.BLACK);
        tl01.setBackgroundColor(Color.BLACK);
        tl02.setBackgroundColor(Color.BLACK);
        tl10.setBackgroundColor(Color.BLACK);
        tl11.setBackgroundColor(Color.BLACK);
        tl12.setBackgroundColor(Color.BLACK);
        tl20.setBackgroundColor(Color.BLACK);
        tl21.setBackgroundColor(Color.BLACK);
        tl22.setBackgroundColor(Color.BLACK);

        tvColor1.setText("  0  ");
        tvColor2.setText("  0  ");

    }


    private void badCase(int n) {
        /*boolean bad = true;
        switch(n) {
            case 0:
                for(int i=0;i<3;i++) {
                    for(int j=0;j<3;j++) {
                        if(b00[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 1:
                for(int i=3;i<6;i++) {
                    for(int j=0;j<3;j++) {
                        if(b01[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 2:
                for(int i=6;i<9;i++) {
                    for(int j=0;j<3;j++) {
                        if(b02[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<3;i++) {
                    for(int j=3;j<6;j++) {
                        if(b10[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 4:
                for(int i=3;i<6;i++) {
                    for(int j=3;j<6;j++) {
                        if(b11[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 5:
                for(int i=6;i<9;i++) {
                    for(int j=3;j<6;j++) {
                        if(b12[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 6:
                for(int i=0;i<3;i++) {
                    for(int j=6;j<9;j++) {
                        if(b20[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 7:
                for(int i=3;i<6;i++) {
                    for(int j=6;j<9;j++) {
                        if(b21[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
            case 8:
                for(int i=6;i<9;i++) {
                    for(int j=6;j<9;j++) {
                        if(b22[i%3][j%3].isEnabled()) {
                            bad = false;
                        }
                    }
                }
                break;
        }*/
        if (!blockStatus[n/3][n%3]) {
            bad = true;
            for(int i= 0; i< 9; i++) {
                if(i== n)
                    continue;
                enableBlock(i, true);

            }
        }
        //return n;
    }


    private void enableAll(boolean value) {

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                b00[i][k].setEnabled(value);
                b01[i][k].setEnabled(value);
                b02[i][k].setEnabled(value);

                b10[i][k].setEnabled(value);
                b11[i][k].setEnabled(value);
                b12[i][k].setEnabled(value);

                b20[i][k].setEnabled(value);
                b21[i][k].setEnabled(value);
                b22[i][k].setEnabled(value);
            }
        }
    }
}