package com.company;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Board board, Player.PlayerType type) {
        super(type, board);
    }

    @Override
    public String print() {
        if (this.type == Player.PlayerType.White)
            return "w";
        else
            return "b";
    }

    @Override
    public boolean move(int initRow, int initCol, int finalRow, int finalCol) {
        {
            boolean isWhite = (Board.checkerBoard[initRow][initCol].getPlayerType() == Player.PlayerType.White) ? true : false;
            boolean returnTrue = false;

            int theoMovedX = (isWhite ? (initRow + 1) : (initRow - 1));
            int theoJumpX = (isWhite ? (initRow + 2) : (initRow - 2));

            if (((finalCol + 1) == initCol || (finalCol - 1) == initCol) && finalRow == theoMovedX) {
                // Moves the piece
                Board.checkerBoard[finalRow][finalCol] = Board.checkerBoard[initRow][initCol];
                Board.checkerBoard[initRow][initCol] = null;
                returnTrue = true;
            } else if (((finalCol + 2) == initCol || (finalCol - 2) == initCol) && finalRow == theoJumpX) {
                // Taking a piece
                if (Board.checkerBoard[(finalRow + initRow) / 2][(finalCol + initCol) / 2].getPlayerType() != Player.PlayerType.Black) {
                    // If there's no piece to take
                    System.out.println("No piece to take");
                    return false;
                } else {
                    // Moves the piece and removes the opponent piece
                    Board.checkerBoard[finalRow][finalCol] = Board.checkerBoard[initRow][initCol];
                    Board.checkerBoard[(finalRow + initRow) / 2][(finalCol + initCol) / 2] = null;
                    Board.checkerBoard[initRow][initCol] = null;
                    returnTrue = true;
                }
            }

            if (returnTrue == true) {
                if (isWhite == true && finalRow == 7) {
                    Board.checkerBoard[finalRow][finalCol] = new King(board, Player.PlayerType.White);
                } else if (isWhite == false && finalRow == 0) {
                    Board.checkerBoard[finalRow][finalCol] = new King(board, Player.PlayerType.Black);
                }
                return true;
            }

            return false;
        }
    }

    public ArrayList<Integer> futureMoves(int initRow, int initCol) {
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>(0);
        boolean isWhite = (Board.checkerBoard[initRow][initCol].getPlayerType() == Player.PlayerType.White) ? true : false;

        int theoMovedX = (isWhite ? (initRow + 1) : (initRow - 1));
        int theoJumpX = (isWhite ? (initRow + 2) : (initRow - 2));

        // Checks left side
        if (Board.checkerBoard[initCol - 1][theoMovedX] == null) {
            possibleMoves.add(initCol - 1);
            possibleMoves.add(theoMovedX);
        }
        // Checks right side
        if (Board.checkerBoard[initCol + 1][theoMovedX] == null) {
            possibleMoves.add(initCol + 1);
            possibleMoves.add(theoMovedX);
        }
        // Checks left side
        if (Board.checkerBoard[initCol - 2][theoJumpX] == null) {
            possibleMoves.add(initCol - 2);
            possibleMoves.add(theoMovedX);
        }
        // Checks right side
        if (Board.checkerBoard[initCol + 2][theoJumpX] == null) {
            possibleMoves.add(initCol + 2);
            possibleMoves.add(theoMovedX);
        }
        return possibleMoves;
    }
}

//    @Override
//    public boolean move(int initialX, int initialY, int finalX, int finalY) {
//
//        boolean isWhite = false;
//        boolean returnTrue = false;
//
//        if (Board.checkerBoard[initialX][initialY].getPlayerType() == Player.PlayerType.White) {
//            isWhite = true;
//        }
//
//        if (isWhite == true) {
//            if (((finalY + 1) == initialY || (finalY - 1) == initialY) && finalX == (initialX + 1)) {
//                // Moves the piece
//                Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
//                Board.checkerBoard[initialX][initialY] = null;
//                returnTrue = true;
//            } else if (((finalY + 2) == initialY || (finalY - 2) == initialY) && finalX == (initialX + 2)) {
//                // Taking a piece
//                if (Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2].getPlayerType() != Player.PlayerType.Black) {
//                    // If there's no piece to take
//                    System.out.println("No piece to take");
//                    return false;
//                }
//                else {
//                    // Moves the piece and removes the opponent piece
//                    Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
//                    Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2] = null;
//                    Board.checkerBoard[initialX][initialY] = null;
//                    returnTrue = true;
//                }
//            }
//        } else {
//            // Piece is black
//            if (((finalY + 1) == initialY || (finalY - 1) == initialY) && finalX == (initialX - 1)) {
//                // Moves the piece
//                Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
//                Board.checkerBoard[initialX][initialY] = null;
//                returnTrue = true;
//            } else if (((finalY + 2) == initialY || (finalY - 2) == initialY) && finalX == (initialX - 2)) {
//                // Taking a piece
//                if (Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2].getPlayerType() != Player.PlayerType.White) {
//                    // If there's no piece to take
//                    System.out.println("No piece to take");
//                    return false;
//                }
//                else {
//                    // Moves the piece and removes the opponent piece
//                    Board.checkerBoard[finalX][finalY] = Board.checkerBoard[initialX][initialY];
//                    Board.checkerBoard[(finalX + initialX) / 2][(finalY + initialY) / 2] = null;
//                    Board.checkerBoard[initialX][initialY] = null;
//                    returnTrue = true;
//                }
//            } else {
//                System.out.println("Not a viable move");
//                return false;
//            }
//        }
//
//        if (returnTrue == true) {
//            if (isWhite == true && finalX == 7) {
//                Board.checkerBoard[finalX][finalY] = new King(board, Player.PlayerType.White);
//            } else if (isWhite == false && finalX == 0) {
//                Board.checkerBoard[finalX][finalY] = new King(board, Player.PlayerType.Black);
//            }
//            return true;
//        }
//
//        return false;
//    }

