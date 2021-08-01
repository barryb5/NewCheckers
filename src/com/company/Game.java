package com.company;

import java.util.Scanner;

public class Game {
    Board m_board = new Board();
    Scanner m_scanner = new Scanner(System.in);
    Player m_p1 = new Player(Player.PlayerType.White);
    Player m_p2 = new Player(Player.PlayerType.Black);

    public void init() {
        m_board.resetBoard();
        m_board.printBoard();
    }

    public boolean gameOver() {
        return m_board.checkWinner();
    }

    public boolean whiteMove() {
        System.out.println("White turn");
        System.out.println("Enter your coordinates in the form of: initRow,initCol,finalRow,finalCol");
        String input = m_scanner.nextLine();
        String[] inputs = input.split(",");
        if (m_board.move(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]), m_p1) == false) {
            System.out.println("That's not a move try again");
            return false;
        }
        m_board.printBoard();
        return true;
    }

    public boolean blackMove() {
        System.out.println("Black turn");
        System.out.println("Enter your coordinates in the form of: initRow,initCol,finalRow,finalCol");
        String input = m_scanner.nextLine();
        String[] inputs = input.split(",");
        if (m_board.move(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]), m_p2) == false) {
            System.out.println("That's not a move try again");
            return false;
        }
        m_board.printBoard();
        return true;
    }

    public int runOnce(int turnNum) {
        while (whiteMove() == false) {}
        while (blackMove() == false) {}
        return ++turnNum;
    }
}
