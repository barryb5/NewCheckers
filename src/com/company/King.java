package com.company;

import java.util.ArrayList;

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
    public boolean move(int initRow, int initCol, int finalRow, int finalCol) {
        if (((finalCol + 1) == initCol || (finalCol - 1) == initCol) && ((finalRow + 1) == initRow || (finalRow - 1) == initRow)) {
            // Moves the piece
            Board.checkerBoard[finalRow][finalCol] = Board.checkerBoard[initRow][initCol];
            Board.checkerBoard[initRow][initCol] = null;
            return true;
        } else if (((finalCol + 2) == initCol || (finalCol - 2) == initCol) && ((finalRow + 2) == initRow || (finalRow - 2) == initRow)) {
            // Taking a piece
            Player.PlayerType opposite = (Board.checkerBoard[initRow][initCol].getPlayerType() == Player.PlayerType.White) ? Player.PlayerType.White : Player.PlayerType.Black;
            if (Board.checkerBoard[(finalRow + initRow) / 2][(finalCol + initCol) / 2].getPlayerType() != opposite) {
                // If there's no piece to take
                System.out.println("No piece to take");
                return false;
            }
            else {
                // Moves the piece and removes the opponent piece
                Board.checkerBoard[finalRow][finalCol] = Board.checkerBoard[initRow][initCol];
                Board.checkerBoard[(finalRow + initRow) / 2][(finalCol + initCol) / 2] = null;
                Board.checkerBoard[initRow][initCol] = null;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> futureMoves(int initRow, int initCol) {
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>(0);
        // Checks left side
        if (Board.checkerBoard[initCol - 1][initRow + 1] == null) {
            possibleMoves.add(initCol - 1);
            possibleMoves.add(initRow + 1);
        }
        if (Board.checkerBoard[initCol - 1][initRow - 1] == null) {
            possibleMoves.add(initCol - 1);
            possibleMoves.add(initRow - 1);
        }
        // Checks right side
        if (Board.checkerBoard[initCol + 1][initRow + 1] == null) {
            possibleMoves.add(initCol + 1);
            possibleMoves.add(initRow + 1);
        }
        if (Board.checkerBoard[initCol - 1][initRow - 1] == null) {
            possibleMoves.add(initCol - 1);
            possibleMoves.add(initRow - 1);
        }
        // Checks left side
        if (Board.checkerBoard[initCol - 2][initRow + 1] == null) {
            possibleMoves.add(initCol - 2);
            possibleMoves.add(initRow + 2);
        }
        if (Board.checkerBoard[initCol - 2][initRow - 1] == null) {
            possibleMoves.add(initCol - 2);
            possibleMoves.add(initRow - 2);
        }
        // Checks right side
        if (Board.checkerBoard[initCol + 2][initRow + 1] == null) {
            possibleMoves.add(initCol + 2);
            possibleMoves.add(initRow + 2);
        }
        if (Board.checkerBoard[initCol - 2][initRow - 1] == null) {
            possibleMoves.add(initCol - 2);
            possibleMoves.add(initRow - 2);
        }
        return possibleMoves;
    }
}
