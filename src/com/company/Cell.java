package com.company;

public class Cell {
    private char ch; //THIS FIELD SHOWS CELL STATE( . - closed cell, numbers - amount of mines around the cell, / - empty cell, * - mine flag)
    private boolean isMine; // true - mine in the cell

    //default constructor
    Cell() {
        ch = '.';
        isMine = false;
    }

    //isMine setter
    public void setMine() {
        isMine = true;
    }

    //ch setter
    public void setChar(char ch) {
        this.ch = ch;
    }

    //ch getter
    public char getChar() {
        return this.ch;
    }

    //isMine setter
    public boolean getIsMine() {
        return this.isMine;
    }

}
