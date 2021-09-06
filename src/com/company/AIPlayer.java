package com.company;

import java.util.ArrayList;

public class AIPlayer {
    private final Player.PlayerType playerType;

    Board m_board = new Board();

    public AIPlayer(Player.PlayerType playerType) {
        this.playerType = playerType;
    }

    public enum PlayerType {
        Black,
    }

    public Player.PlayerType getPlayerType() {
        return playerType;
    }

    public boolean doFirstMove(Player.PlayerType playerType) {
        boolean isNull = true;

        for (int r = 0; r < m_board.SIZE; r++) {
            for (int c = 0; c < m_board.SIZE; c++) {

                if (m_board.checkerBoard[r][c] == null) {
                    isNull = true;
                }

                if (isNull == false) {
                    if (m_board.checkerBoard[r][c].getPlayerType() == playerType) {
                        ArrayList<Integer> vals = m_board.checkerBoard[r][c].futureMoves(r, c);
                        if (vals.get(0) != 20) {
                            m_board.checkerBoard[r][c].move(r, c, vals.get(0), vals.get(1));
                            m_board.printBoard();
                            return true;
                        }
                    }
                }
                isNull = false;
            }
        }

        return false;
    }
}
