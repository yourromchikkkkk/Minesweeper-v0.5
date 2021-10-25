package com.company;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        Field game = new Field(9 , obj.nextInt());
        game.playGame();
    }
}
