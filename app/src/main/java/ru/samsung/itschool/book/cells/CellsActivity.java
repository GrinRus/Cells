package ru.samsung.itschool.book.cells;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;


import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 10;
    private int HEIGHT = 10;
    private int cnt = 0;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("-");
            }
    }

    @Override
    public boolean onLongClick(View v) {
        //Эту строку нужно удалить
        Stub.show(this, "Добавьте код в функцию активности onLongClick() - реакцию на долгое нажатие на клетку");
        return false;
    }

    @Override
    public void onClick(View v) {

        Button tappedCell = (Button) v;

        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

            setText(tappedX, tappedY);
    }


    public void setText(int x, int y) {
        if (cnt % 2 == 0) {
            if (cells[x][y].getText().toString() == "-") {
                cells[x][y].setText("X");
                cnt++;
            }
        } else {
            if (cells[x][y].getText().toString() == "-") {
                cells[x][y].setText("0");
                cnt++;
            }
        }
        if (checkWin(x, y)) {
            youWin();
        }
        if (cnt == 9){
            Stub.show(this, "Ничья!");
            cnt = 0;
            makeCells();
            generate();
        }
    }

    public void youWin() {
        Stub.show(this, "Победа!");
        cnt = 0;
        makeCells();
        generate();
    }

    public boolean checkd(int x, int y, String check) {
        System.out.println(check);
        System.out.println("x: " + (x) + " y: " + (y));
        if (((String) cells[x][y].getTag()).split(",")[1] == "1") {
            if (((String) cells[x][y].getTag()).split(",")[0] == "1") {
                if ((cells[x + 1][y - 1].getText().toString() == check && cells[x - 1][y + 1].getText().toString() == check)
                        || (cells[x + 1][y + 1].getText().toString() == check && cells[x - 1][y - 1].getText().toString() == check)) {
                    return true;
                }
            }
        }
        if (((String) cells[x][y].getTag()).split(",")[1] == "0") {
            if (((String) cells[x][y].getTag()).split(",")[0] == "2") {
                if (cells[x - 1][y + 1].getText().toString() == check && cells[x - 2][y + 2].getText().toString() == check) {
                    return true;
                }
            }
            if (((String) cells[x][y].getTag()).split(",")[0] == "0") {
                if (cells[x + 1][y + 1].getText().toString() == check && cells[x + 2][y + 2].getText().toString() == check) {
                    return true;
                }
            }
        }
        if (((String) cells[x][y].getTag()).split(",")[1] == "2") {
            if (((String) cells[x][y].getTag()).split(",")[0] == "2") {
                if (cells[x - 1][y - 1].getText().toString() == check && cells[x - 2][y - 2].getText().toString() == check) {
                    return true;
                }
            }
            if (((String) cells[x][y].getTag()).split(",")[0] == "0") {
                if (cells[x + 1][y - 1].getText().toString() == check && cells[x + 2][y - 2].getText().toString() == check) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkWin(int x, int y) {
        String check = "";
        int xx = 0, yy = 0;
        if (cells[x][y].getText().toString() == "X") {
            check = "X";
        } else {
            check = "0";
        }
        if (checkd(x, y, check)) {
            return true;
        }
        for (int ix = 0; ix < 3; ix++) {
            if (cells[ix][y].getText().toString() == check) {
                xx++;
            }
        }
        if (xx == 3) {
            return true;
        }
        for (int iy = 0; iy < 3; iy++) {
            if (cells[x][iy].getText().toString() == check) {
                yy++;
            }
        }
        if (yy == 3) {
            return true;
        }
        return false;
    }
    /*
     * NOT FOR THE BEGINNERS
     * ==================================================
     */

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(3);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}