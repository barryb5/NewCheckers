package com.company;

import java.util.ArrayList;

public abstract class Piece { Board board;
    public Player.PlayerType type;

    public Piece(Player.PlayerType type, Board board) {
        this.type = type;
        this.board = board;
    }

    public Player.PlayerType getPlayerType() {
        return this.type;
    }

    public abstract String print();

    public abstract boolean move(int initRow, int initCol, int finalRow, int finalCol);

    public abstract ArrayList<Integer> futureMoves(int initRow, int initCol);
}
