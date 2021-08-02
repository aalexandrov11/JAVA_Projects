package com.company;

import java.util.Random;

public class Game {
    public static int boardSize;
    public static int minesCount;

    public static int adjacentMines(int row, int col, char[][] board){
        /* positions where pos 5 is the current cell
        1 2 3
        4 5 6
        7 8 9
        */

        int count = 0;

        //pos 1
        if(col - 1 >= 0 && col - 1 < boardSize && row - 1 >= 0 && row - 1 < boardSize){
            if(board[row - 1][col - 1] == '*'){
                count++;
            }
        }

        //pos 2
        if(col >= 0 && col < boardSize && row - 1 >= 0 && row - 1  < boardSize){
            if(board[row - 1][col] == '*'){
                count++;
            }
        }

        //pos 3
        if(col + 1 >= 0 && col + 1 < boardSize && row - 1 >= 0 && row - 1 < boardSize){
            if(board[row - 1][col + 1] == '*'){
                count++;
            }
        }

        //pos 4
        if(col - 1 >= 0 && col - 1 < boardSize && row >= 0 && row < boardSize){
            if(board[row][col - 1] == '*'){
                count++;
            }
        }

        //pos 6
        if(col + 1 >= 0 && col + 1 < boardSize && row >= 0 && row < boardSize){
            if(board[row][col + 1] == '*'){
                count++;
            }
        }

        //pos 7
        if(col - 1 >= 0 && col - 1 < boardSize && row + 1 >= 0 && row + 1 < boardSize){
            if(board[row + 1][col - 1] == '*'){
                count++;
            }
        }

        //pos 8
        if(col >= 0 && col < boardSize && row + 1 >= 0 && row + 1 < boardSize){
            if(board[row + 1][col] == '*'){
                count++;
            }
        }

        //pos 9
        if(col + 1 >= 0 && col + 1 < boardSize && row  + 1 >= 0 && row + 1 < boardSize){
            if(board[row + 1][col + 1] == '*'){
                count++;
            }
        }

        return count;
    }

    public static void gameBoardPrint(char[][] currentBoard){
        System.out.println("Current Status of Board: ");
        System.out.print("   ");

        for(int i = 0; i < boardSize; i++){
            if(i < 9) {
                System.out.print(i + "  ");
            }else{
                System.out.print(i + " ");
            }
        }
        System.out.print("\n");

        for(int i = 0; i < boardSize; i++){
            if(i <= 9) {
                System.out.print(i + "  ");
            }else{
                System.out.print(i + " ");
            }
            for(int j = 0; j < boardSize; j++){
                if(j < 9) {
                    System.out.print(currentBoard[i][j] + "  ");
                }else{
                    System.out.print(currentBoard[i][j] + "  ");
                }
            }
            System.out.print("\n");
        }
    }

    public static boolean minesweeper(int row, int col, int[] moves, char[][] currentBoard, char[][] board){
        if (currentBoard[row][col] != '-'){
            return false;
        }

        if(board[row][col] == '*'){// when a mine is opened
            currentBoard[row][col] = '*';
            for(int i = 0; i < boardSize; i++){
                for(int j = 0; j < boardSize; j++) {
                    if(board[i][j] == '*'){
                        currentBoard[i][j] = '*';
                    }
                }
            }
            gameBoardPrint(currentBoard);
            System.out.println();
            System.out.println("You lost!");
            return true;
        }else{ // sum of adjacent mines and placing them on board

            int count = adjacentMines(row, col, board);
            moves[0]--;
            currentBoard[row][col] = (char) (count + '0');//ascii of 0 is 48, adding count to it will give actual value

            if(count == 0){
                //pos 1
                if(col - 1 >= 0 && col - 1 < boardSize && row - 1 >= 0 && row - 1 < boardSize){
                    if(board[row - 1][col - 1] != '*'){
                        minesweeper(row - 1, col - 1, moves, currentBoard, board);
                    }
                }

                //pos 2
                if(col >= 0 && col < boardSize && row - 1 >= 0 && row - 1  < boardSize){
                    if(board[row - 1][col] != '*'){
                        minesweeper(row - 1, col, moves, currentBoard, board);
                    }
                }

                //pos 3
                if(col + 1 >= 0 && col + 1 < boardSize && row - 1 >= 0 && row - 1 < boardSize){
                    if(board[row - 1][col + 1] != '*'){
                        minesweeper(row - 1, col + 1, moves, currentBoard, board);
                    }
                }

                //pos 4
                if(col - 1 >= 0 && col - 1 < boardSize && row >= 0 && row < boardSize){
                    if(board[row][col - 1] != '*'){
                        minesweeper(row, col - 1, moves, currentBoard, board);
                    }
                }

                //pos 6
                if(col + 1 >= 0 && col + 1 < boardSize && row >= 0 && row < boardSize){
                    if(board[row][col + 1] != '*'){
                        minesweeper(row, col + 1, moves, currentBoard, board);
                    }
                }

                //pos 7
                if(col - 1 >= 0 && col - 1 < boardSize && row + 1 >= 0 && row + 1 < boardSize){
                    if(board[row + 1][col - 1] != '*'){
                        minesweeper(row + 1, col - 1, moves, currentBoard, board);
                    }
                }

                //pos 8
                if(col >= 0 && col < boardSize && row + 1 >= 0 && row + 1 < boardSize){
                    if(board[row + 1][col] != '*'){
                        minesweeper(row + 1, col, moves, currentBoard, board);
                    }
                }

                //pos 9
                if(col + 1 >= 0 && col + 1 < boardSize && row  + 1 >= 0 && row + 1 < boardSize){
                    if(board[row + 1][col + 1] != '*'){
                        minesweeper(row + 1, col + 1, moves, currentBoard, board);
                    }
                }
            }
            return false;
        }
    }

    public static void minesPlacing(int mines, char[][] board){
        int placed = 0;

        Random r = new Random();
        while(placed < mines){
            int a = r.nextInt(boardSize);
            int b = r.nextInt(boardSize);

            if(board[a][b] == '*'){
                continue;
            }
            board[a][b] = '*';
            placed++;
        }
    }

    public static void init(char[][] currentBoard, char[][] board){
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                currentBoard[i][j] = board[i][j] = '-';
            }
        }
    }

    public static void secureFirst(int row, int col, char[][] board){
        minesPlacing(1, board);
        board[row][col] = '-';
    }

    public static void difficulty(int x){

        if(x == 0){
            boardSize = 9;
            minesCount = 10;
        }

        if(x == 1){
            boardSize = 16;
            minesCount = 40;
        }

        if(x == 2){
            boardSize = 24;
            minesCount = 99;
        }
    }

//    public static void show(char[][] board){// showing all the mines
//        gameBoardPrint(board);
//    }
}
