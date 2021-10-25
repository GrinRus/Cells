package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextClock;

import task.Stub;
import task.Task;


public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 3;
    private int HEIGHT = 3;
    private int z = 1;
    private int ob = HEIGHT * WIDTH;
    private int c = 0;
    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }
    void generate() {
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                cells[i][j].setTextColor(Color.WHITE);
                cells[i][j].setText("9");
            }
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
        c = 0;
        int tappedX = getX(tappedCell),i=0,j=0;
        int tappedY = getY(tappedCell);
        String check = cells[tappedX][tappedY].getText().toString();
        if(check == "9" && c == 0){
            if(z % 2 != 0){
                cells[tappedX][tappedY].setTextColor(Color.GREEN);
                cells[tappedX][tappedY].setText("x");
            }
            else {
                cells[tappedX][tappedY].setTextColor(Color.RED);
                cells[tappedX][tappedY].setText("o");
            }
            if(z > 4){
                for(i=0;i<HEIGHT;i++){
                    if(cells[i][0].getText().toString() == "x"
                            && cells[i][1].getText().toString() == cells[i][0].getText().toString()
                            && cells[i][1].getText().toString() == cells[i][2].getText().toString()
                            && cells[i][2].getText().toString()==cells[i][0].getText().toString()){
                        Task.showMessage(this, "Выйгрыш крестов");
                        z=0;
                        generate();
                        c++;
                    }
                    else if(cells[i][1].getText().toString() == cells[i][0].getText().toString()
                            && cells[i][1].getText().toString() == cells[i][2].getText().toString()
                            && cells[i][2].getText().toString()==cells[i][0].getText().toString()
                            && cells[i][0].getText().toString() == "o") {
                        Task.showMessage(this, "Выйгрыш нулей");
                        z=0;
                        generate();
                        c++;
                    }
                }
                for(j=0;j<WIDTH;j++){
                    if(cells[0][j].getText().toString() == "x"
                            && cells[1][j].getText().toString() == cells[2][j].getText().toString()
                            && cells[0][j].getText().toString() == cells[1][j].getText().toString()
                            && cells[0][j].getText().toString()==cells[2][j].getText().toString()){
                        Task.showMessage(this, "Выйгрыш крестов");
                        z=0;
                        generate();
                        c++;
                    }
                    else if(cells[0][j].getText().toString() == "o"
                            && cells[1][j].getText().toString() == cells[2][j].getText().toString()
                            && cells[0][j].getText().toString() == cells[1][j].getText().toString()
                            && cells[0][j].getText().toString()==cells[2][j].getText().toString()){
                        Task.showMessage(this, "Выйгрыш нулей");
                        z=0;
                        generate();
                        c++;
                    }
                }
                if(cells[0][0].getText().toString() == cells[1][1].getText().toString()
                        && cells[1][1].getText().toString() == cells[2][2].getText().toString()
                        && cells[2][2].getText().toString()==cells[0][0].getText().toString()
                        && cells[0][0].getText().toString() == "o"){
                    Task.showMessage(this, "Выйгрыш нулей");
                    z=0;
                    generate();
                    c++;
                }
                else if(cells[0][2].getText().toString() == cells[1][1].getText().toString()
                        && cells[1][1].getText().toString() == cells[2][0].getText().toString()
                        && cells[2][0].getText().toString()==cells[0][2].getText().toString()
                        && cells[2][0].getText().toString() == "x"){
                    Task.showMessage(this, "Выйгрыш крестов");
                    z=0;
                    generate();
                    c++;
                }
                else if(cells[0][2].getText().toString() == cells[1][1].getText().toString()
                        && cells[1][1].getText().toString() == cells[0][2].getText().toString()
                        && cells[2][0].getText().toString()==cells[0][2].getText().toString()
                        && cells[0][2].getText().toString() == "o"){
                    Task.showMessage(this, "Выйгрыш нулей");
                    z=0;
                    generate();
                    c++;
                }
                else if(cells[0][0].getText().toString() == cells[1][1].getText().toString()
                        && cells[1][1].getText().toString() == cells[2][2].getText().toString()
                        && cells[2][2].getText().toString()==cells[0][0].getText().toString()
                        && cells[0][0].getText().toString() == "x"){
                    Task.showMessage(this, "Выйгрыш крестов");
                    z=0;
                    generate();
                    c++;
                }
            }
            z++;
        }
        if(ob < z && c == 0){
            Task.showMessage(this, "Ничья");
            z=1;
            generate();
        }
    }
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
                cellsLayout.addView(cells[i][j]);
            }
    }

}
