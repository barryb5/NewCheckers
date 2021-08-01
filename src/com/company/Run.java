package com.company;

import java.util.Scanner;

public class Run {

    public void run() {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        Player p1 = new Player(Player.PlayerType.White);
        Player p2 = new Player(Player.PlayerType.Black);

        int moveNumber = 0;

        board.resetBoard();
        board.printBoard();

        while (board.checkWinner() == false) {
            if (moveNumber % 2 == 0) {
                System.out.println("White turn");
                System.out.println("Enter your coordinates in the form of: initialX,initialY,finalX,finalY");
                String input = scanner.nextLine();
                String[] inputs = input.split(",");
                if (board.move(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[0]), Integer.parseInt(inputs[3]), Integer.parseInt(inputs[2]), p1) == false) {
                    System.out.println("That's not a move try again");
                    moveNumber--;
                }
            } else {
                System.out.println("Black turn");
                System.out.println("Enter your coordinates in the form of: initialX,initialY,finalX,finalY");
                String input = scanner.nextLine();
                String[] inputs = input.split(",");
                if (board.move(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[0]), Integer.parseInt(inputs[3]), Integer.parseInt(inputs[2]), p2) == false) {
                    System.out.println("That's not a move try again");
                    moveNumber--;
                }
            }
            board.printBoard();
            moveNumber++;
        }
    }
}
