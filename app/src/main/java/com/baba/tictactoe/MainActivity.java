package com.baba.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

  public class MainActivity extends AppCompatActivity   {
    private Button[][] buttons = new Button[3][3];
    boolean player1 = true;
    private int roundCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!((Button) view).getText().toString().equals("")) {
                            return;
                        }

                        if (player1) {
                            ((Button) view).setText("X");
                        } else {
                            ((Button) view).setText("O");
                        }



                        if (checkForWin()) {
                            if (player1) {
                                player1Wins();
                            } else {
                                player2Wins();
                            }
                        }
                        else if (roundCount == 9) {
                            draw();
                        }
                        else {
                            player1 = !player1;
                        }
                    }
                });

            }
        Button resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
            }
        });
    }

//    @Override
//    public void onClick(View view) {
//        if (!((Button) view).getText().toString().equals("")) {
//            return;
//        }
//
//        if (player1) {
//            ((Button) view).setText("X");
//        } else {
//            ((Button) view).setText("O");
//        }
//
//
//
//        if (checkForWin()) {
//            if (player1) {
//                player1Wins();
//            } else {
//                player2Wins();
//            }
//        }
//        else if (roundCount == 9) {
//            draw();
//        }
//        else {
//            player1 = !player1;
//        }
//    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {

        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins() {
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

       roundCount = 0;
        player1 = true;
    }

  }

