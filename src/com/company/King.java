package com.company;

public class King extends Piece{

    public King(Board board, Player.PlayerType type) {
        super(type, board);
    }

    @Override
    public String print()
    {
        if (this.type == Player.PlayerType.White)
            return "W";
        else
            return "B";
    }

    @Override
    public boolean move(int initialX, int initialY, int finalX, int finalY) {
        boolean isWhite = false;

        if (Board.checkerBoard[initialX][initialY].getPlayerType() == Player.PlayerType.White) {
            isWhite = true;
        }

        if (isWhite == true) {
            if (((finalY + 1) == initialY || (finalY - 1) == initialY) && ((finalX + 1) == initialX || (finalX - 1) == initialX)) {
                // Moves the piece
                Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
                Board.checkerBoard[initialX][initialY] = null;
                return true;
            } else if (((finalY + 2) == initialY || (finalY - 2) == initialY) && ((finalX + 2) == initialX || (finalX - 2) == initialX)) {
                // Taking a piece
                if (Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2].getPlayerType() != Player.PlayerType.Black) {
                    // If there's no piece to take
                    System.out.println("No piece to take");
                    return false;
                }
                else {
                    // Moves the piece and removes the opponent piece
                    Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
                    Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2] = null;
                    Board.checkerBoard[initialX][initialY] = null;
                    return true;
                }
            }
        } else {
            // Piece is black
            if (((finalY + 1) == initialY || (finalY - 1) == initialY) && ((finalX + 1) == initialX || (finalX - 1) == initialX)) {
                // Moves the piece
                Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
                Board.checkerBoard[initialX][initialY] = null;
                return true;
            } else if (((finalY + 2) == initialY || (finalY - 2) == initialY) && ((finalX + 2) == initialX || (finalX - 2) == initialX)) {
                // Taking a piece
                if (Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2].getPlayerType() != Player.PlayerType.White) {
                    // If there's no piece to take
                    System.out.println("No piece to take");
                    return false;
                }
                else {
                    // Moves the piece and removes the opponent piece
                    Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
                    Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2] = null;
                    Board.checkerBoard[initialX][initialY] = null;
                    return true;
                }
            } else {
                System.out.println("Not a viable move");
                return false;
            }
        }

        return false;
    }
}
