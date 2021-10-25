package com.company;
import java.util.Random;
import java.util.Scanner;

public class Field {
    private int size;
    Cell[][]field; // play field size*size
    private int countOfMines;

    // constructor with parameters
    Field(int s, int count) {
        this.size = s;
        this.countOfMines = count;
        field = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    //countOfMines setter
    public void setCountOfMines(int count) {
        this.countOfMines = count;
    }

    //countOfMines getter
    public int getCountOfMines() {
        return countOfMines;
    }

    //size setter
    public int getSize() {
        return size;
    }

    //function to set mine in the cell
    public void setMine(int rows, int columns) {
        field[rows][columns].setMine();
    }

    //function to replace '.' with count of mines around the cell
    private void useless(int row, int colum, int count) {
        if (count == 1) {
            field[row][colum].setChar('1');
        } else if (count == 2) {
            field[row][colum].setChar('2');
        } else if (count == 3) {
            field[row][colum].setChar('3');
        } else if (count == 4) {
            field[row][colum].setChar('4');
        } else if (count == 5) {
            field[row][colum].setChar('5');
        } else if (count == 6) {
            field[row][colum].setChar('6');
        } else if (count == 7) {
            field[row][colum].setChar('7');
        } else if (count == 8) {
            field[row][colum].setChar('8');
        } else {
            field[row][colum].setChar('.');
        }
    }

    //function to check if the cell has mine
    private int checkCell(int row, int column) {
        if (field[row][column].getIsMine()) {
            return 1;
        } else {
            return 0;
        }
    }

    //function to check all cells around the cell, return count of mines around the cell
    private int defaultCheck(int row, int column) {
        int count = 0;
        count += checkCell(row - 1, column - 1);
        count += checkCell(row - 1, column);
        count += checkCell(row - 1, column + 1);
        count += checkCell(row, column - 1);
        count += checkCell(row, column + 1);
        count += checkCell(row + 1, column -1);
        count += checkCell(row + 1, column);
        count += checkCell(row + 1, column + 1);

        return count;
    }

    //function with implementation of exceptional cases
    private void setNum(int row, int column) {
        int count = 0;
        if (row == 0 && column == 0) {
            count += checkCell(row, column + 1);
            count += checkCell(row + 1, column);
            count += checkCell(row + 1, column + 1);
        } else if (row == 0 && column == 8) {
            count += checkCell(row, column - 1);
            count += checkCell(row + 1, column - 1);
            count += checkCell(row + 1, column);
        } else if (row == 8 && column == 0) {
            count += checkCell(row - 1, column);
            count += checkCell(row - 1, column + 1);
            count += checkCell(row, column + 1);
        } else if (row == 8 && column == 8) {
            count += checkCell(row, column - 1);
            count += checkCell(row - 1, column - 1);
            count += checkCell(row - 1, column);
        } else if (row == 0) {
            count += checkCell(row, column - 1);
            count += checkCell(row + 1, column - 1);
            count += checkCell(row + 1, column);
            count += checkCell(row + 1, column + 1);
            count += checkCell(row, column + 1);
        } else if (row == 8) {
            count += checkCell(row, column - 1);
            count += checkCell(row - 1, column - 1);
            count += checkCell(row - 1, column);
            count += checkCell(row - 1, column + 1);
            count += checkCell(row, column + 1);
        } else if (column == 0) {
            count += checkCell(row - 1, column);
            count += checkCell(row - 1, column + 1);
            count += checkCell(row, column + 1);
            count += checkCell(row + 1, column + 1);
            count += checkCell(row + 1, column);
        } else if (column == 8) {
            count += checkCell(row - 1, column);
            count += checkCell(row - 1, column - 1);
            count += checkCell(row, column - 1);
            count += checkCell(row + 1, column - 1);
            count += checkCell(row + 1, column);
        } else {
            count += defaultCheck(row, column);
        }
        if (count != 0) {
            useless(row, column, count);
        }
    }

    //function which create field with random placed mines
    private void createField() {
        Random rand = new Random();
        int randRow;
        int randColumn;
        for (int i = getCountOfMines(); i > 0;) {
            randRow = rand.nextInt(size);
            randColumn = rand.nextInt(size);
            if (!field[randRow][randColumn].getIsMine()) {
                field[randRow][randColumn].setMine();
                i--;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!field[i][j].getIsMine()) {
                    setNum(i, j);
                }
            }
        }
    }

    //function which show field state
    private void showField() {
        System.out.println("   |1|2|3|4|5|6|7|8|9|");
        System.out.println("___|_|_|_|_|_|_|_|_|_|");
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1);
            System.out.print("  |");
            for (int j = 0; j < size; j++) {
                if (j == size - 1) {
                    System.out.print(field[i][j].getChar());
                    System.out.println('|');
                } else {
                    System.out.print(field[i][j].getChar());
                    System.out.print('|');
                }
            }
        }
        System.out.println("----------------------");
    }

    //function which set mine flag
    private int setMineFlag(int row, int column) {
        if (field[row - 1][column  - 1].getChar() == '.'){
            field[row - 1][column - 1].setChar('*');
            return 0;
        } else if (field[row - 1][column - 1].getChar() == '*') {
            field[row - 1][column - 1].setChar('.');
            return 0;
        } else {
            System.out.println("There is a number here!");
            return -1;
        }
    }

    //function which check if game is over
    private boolean checkGame() {
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j].getIsMine() && field[i][j].getChar() == '*') {
                    temp++;
                } else if (!field[i][j].getIsMine() && field[i][j].getChar() == '*'){
                    return false;
                }
            }
        }
        return temp == countOfMines;
    }

    //
    public void playGame() {
        Scanner obj = new Scanner(System.in);
        createField();
        int temp = 0;
        boolean isOver = false;
        do {
            showField();
            System.out.print("Set/delete mine marks (x and y coordinates): ");
            temp = setMineFlag(obj.nextInt(), obj.nextInt());
            if (temp == -1) {
                System.out.print("Set/delete mine marks (x and y coordinates): ");
                temp = setMineFlag(obj.nextInt(), obj.nextInt());
            }
            isOver = checkGame();
        } while(!isOver);

        System.out.println("Congratulations! You found all mines!");
    }

}
