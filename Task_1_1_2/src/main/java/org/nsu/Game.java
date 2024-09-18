package org.nsu;

/**
 * class Game when is played all game. have 4 field: score for player and dealer, number of round.
 * and deck.
 */
public class Game {

    static int playerScore;
    static int dealerScore;
    static int drawScore;
    static int roundNumber;
    public static Deck deck;

    /**
     * init score = 0 and round = 1.
     */
    public Game() {
        playerScore = 0;
        dealerScore = 0;
        drawScore = 0;
        roundNumber = 1;
        //dont init deck
    }

    /**
     * main function all program. In cycle run rounds while in Deck more then n cards.
     */
    public static void play() {
        System.out.print("Добро пожаловать в Блэкджек!\n");
        Utils.wait(1);
        deck = new Deck(Round.ask("На сколько колод желаете сыграть?\n"));
        while (deck.size >= 23) {
            Round round = new Round(roundNumber++, deck);
            String resultMsg = "";
            int res = round.start();
            if (res == 1) {
                playerScore++;
                resultMsg = resultMsg.concat("Вы выиграли раунд!");
            } else if (res == 0) {
                drawScore++;
                resultMsg = resultMsg.concat("Ничья!");
            } else if (res == -1) {
                dealerScore++;
                resultMsg = resultMsg.concat("Победа за дилером!");
            }
            System.out.printf(resultMsg + " Счет %d:%d:%d\n", playerScore, drawScore, dealerScore);
            Utils.wait(2);
        }
    }
}