package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;


import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 10;
    private int HEIGHT = 10;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

    }


    @Override
    public boolean onLongClick(View v) {
        //Эту строку нужно удалить
        Stub.show(this, "Добавьте код в функцию активности onLongClick() - реакцию на долгое нажатие на клетку");
        return false;
    }

    @Override
    public void onClick(View v) {
        //Эту строку нужно удалить
        Button tappedCell = (Button) v;

        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        int color = ((ColorDrawable)cells[tappedX][tappedY].getBackground()).getColor();


        for (int i = 0; i < HEIGHT; i ++) {
            if (color == Color.WHITE) {cells[i][tappedY].setBackgroundColor(Color.BLACK);}
            else if (color == Color.BLACK) {cells[i][tappedY].setBackgroundColor(Color.WHITE);}
        }
        for (int j = 0; j < WIDTH; j ++) {
            if (color == Color.WHITE) {cells[tappedX][j].setBackgroundColor(Color.BLACK);}
            else if (color == Color.BLACK) {cells[tappedX][j].setBackgroundColor(Color.WHITE);}
        }

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
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                if (Math.floor(Math.random() * (1 - 0 + 1)) + 0 == 0) cells[i][j].setBackgroundColor(Color.BLACK);
                else cells[i][j].setBackgroundColor(Color.WHITE);

            cellsLayout.addView(cells[i][j]);
            }
    }

}