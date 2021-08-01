package com.company;

public class Board {
    final static int size = 8;
    final static public Piece[][] checkerBoard = new Piece[size][size];

    Board() {
        resetBoard();
    }

    public void printBoard() {
        System.out.print("i ");
        for (int r = 0; r < size; ++r)
            System.out.print("  " + r + " ");
        System.out.println();
        System.out.print("  -");
        for (int r = 0; r < size; ++r)
            System.out.print(" - -");
        System.out.println();
        for (int r = 0; r < size; ++r) {
            System.out.print(r + " | ");
            for (int c = 0; c < size; ++c)
            {
                if (checkerBoard[r][c] != null)
                    System.out.print(checkerBoard[r][c].print() + " | ");
                else
                    System.out.print("  | ");
            }
            System.out.println();
            if (r != size - 1)
                System.out.print("  |");
            else
                System.out.print("  -");
            for (int c = 0; c < size; ++c) {
                System.out.print(" - ");
                if (r == size - 1)
                    System.out.print("-");
                else if (c == size - 1)
                    System.out.print("|");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }

    public void resetBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r == 0 || r == 2) {
                    if (c % 2 != 0) {
                        checkerBoard[r][c] = new Pawn(this, Player.PlayerType.White);
                    }
                } else if (r == 1) {
                    if (c % 2 == 0) {
                        checkerBoard[r][c] = new Pawn(this, Player.PlayerType.White);
                    }
                } else if (r == 6) {
                    if (c % 2 != 0) {
                        checkerBoard[r][c] = new Pawn(this, Player.PlayerType.Black);
                    }
                } else if (r == 5 || r == 7) {
                    if (c % 2 == 0) {
                        checkerBoard[r][c] = new Pawn(this, Player.PlayerType.Black);
                    }
                } else {
                    checkerBoard[r][c] = null;
                }
            }
        }
    }

    public void wipeBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                checkerBoard[r][c] = null;
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

    public boolean move(int initRow, int initCol, int finalRow, int finalCol, Player player) {
        if ((initRow < 0) ||
                (initCol < 0) ||
                (initRow > size - 1) ||
                (initCol > size - 1) ||
                (finalRow < 0) ||
                (finalCol < 0) ||
                (finalRow > size - 1) ||
                (finalCol > size - 1) ||
                (checkerBoard[initRow][initCol] == null) ||
                (checkerBoard[initRow][initCol].getPlayerType() != player.getPlayerType()) ||
                // Thinks there's something in the final spot when there isn't idk why
                (checkerBoard[finalRow][finalCol] != null)) {
            return false;
        }

        return checkerBoard[initRow][initCol].move(initRow, initCol, finalRow, finalCol);
    }

    public int getScore(Player.PlayerType type) {
        int score = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (checkerBoard[r][c].getPlayerType() == type) {
                    score++;
                }
            }
        }

        return score;
    }
}
