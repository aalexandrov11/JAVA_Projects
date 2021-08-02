package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines");
        System.out.println("Press 2 for ADVANCED (24 * 24 Cells and 99 Mines)");
        Game.difficulty(scan.nextInt());

        int[] moves = new int[1];
        moves[0] = Game.boardSize * Game.boardSize - Game.minesCount;
        char[][] currentBoard = new char[Game.boardSize][Game.boardSize];
        char[][] board = new char[Game.boardSize][Game.boardSize];

        Game.init(currentBoard, board);
        Game.minesPlacing(Game.minesCount, board);
        //Game.show(board);// only for testing the game

        boolean playingTheGame = false;
        int a, b;
        boolean firstMove = true;

        while(playingTheGame == false){
            Game.gameBoardPrint(currentBoard);
            System.out.print("Enter your move, (row, column) \n-> ");
            a = scan.nextInt();
            b = scan.nextInt();
            if(firstMove == true){// secure first move is not a mine
                if(board[a][b] == '*'){
                    Game.secureFirst(a, b, board);
                }
            }
            firstMove = false;

            playingTheGame = Game.minesweeper(a, b, moves, currentBoard, board);
            if (playingTheGame == false && moves[0] == 0) {
                Game.gameBoardPrint(currentBoard);
                System.out.println("You won!");
                playingTheGame = true;
            }
        }
        scan.close();
    }
}
