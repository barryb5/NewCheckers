package com.company;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        int turnNum = 0;
        game.init();
        while(!game.gameOver()) {
            turnNum = game.runOnce(turnNum);
        }
    }
}
