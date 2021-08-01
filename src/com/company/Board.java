package com.company;

public class Board {
    final static int size = 8;
    final static public Piece[][] checkerBoard = new Piece[size][size];

    Board() {
        resetBoard();
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < size; ++i)
            System.out.print("  " + i + " ");
        System.out.println();
        System.out.print("  -");
        for (int i = 0; i < size; ++i)
            System.out.print(" - -");
        System.out.println();
        for (int i = 0; i < size; ++i) {
            System.out.print(i + " | ");
            for (int j = 0; j < size; ++j)
            {
                if (checkerBoard[i][j] != null)
                    System.out.print(checkerBoard[i][j].print() + " | ");
                else
                    System.out.print("  | ");
            }
            System.out.println();
            if (i != size - 1)
                System.out.print("  |");
            else
                System.out.print("  -");
            for (int j = 0; j < size; ++j) {
                System.out.print(" - ");
                if (i == size - 1)
                    System.out.print("-");
                else if (j == size - 1)
                    System.out.print("|");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == 2) {
                    if (j % 2 != 0) {
                        checkerBoard[i][j] = new Pawn(this, Player.PlayerType.White);
                    }
                } else if (i == 1) {
                    if (j % 2 == 0) {
                        checkerBoard[i][j] = new Pawn(this, Player.PlayerType.White);
                    }
                } else if (i == 6) {
                    if (j % 2 != 0) {
                        checkerBoard[i][j] = new Pawn(this, Player.PlayerType.Black);
                    }
                } else if (i == 5 || i == 7) {
                    if (j % 2 == 0) {
                        checkerBoard[i][j] = new Pawn(this, Player.PlayerType.Black);
                    }
                } else {
                    checkerBoard[i][j] = null;
                }
            }
        }
    }

    public void wipeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                checkerBoard[i][j] = null;
            }
        }
    }

    public boolean checkWinner() {

        boolean whiteWon = true;
        boolean blackWon = true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkerBoard[i][j] != null) {
                    if ((checkerBoard[i][j].getPlayerType()) == Player.PlayerType.White) {
                        blackWon = false;
                    }
                    if ((checkerBoard[i][j].getPlayerType()) == Player.PlayerType.Black) {
                        whiteWon = false;
                    }
                }
            }
        }

        if (blackWon == true) {
            System.out.println("Black has won");
            return true;
        } else if (whiteWon == true) {
            System.out.println("White has won");
            return true;
        } else {
            System.out.println("Nobody has won yet");
            return false;
        }
    }

    public boolean move(int initialX, int initialY, int finalX, int finalY, Player player) {
        if ((initialX < 0) ||
                (initialY < 0) ||
                (initialX > size - 1) ||
                (initialY > size - 1) ||
                (finalX < 0) ||
                (finalY < 0) ||
                (finalX > size - 1) ||
                (finalY > size - 1) ||
                (checkerBoard[initialX][initialY] == null) ||
                (checkerBoard[initialX][initialY].getPlayerType() != player.getPlayerType()) ||
                (checkerBoard[finalX][finalY] != null)) {
            return false;
        }

        return checkerBoard[initialX][initialY].move(initialX, initialY, finalX, finalY);
    }

    public int getScore(Player.PlayerType type) {
        int score = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkerBoard[i][j].getPlayerType() == type) {
                    score++;
                }
            }
        }

        return score;
    }
}
