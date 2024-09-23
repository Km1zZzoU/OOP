package org.nsu;

/**
 * class Game where is played all game.
 */
public class Game {

    private static int win = 1;
    private static int lose = -1;
    private static int draw = 0;

    static int playerScore;
    static int dealerScore;
    static int roundNumber = 0;
    /**
     * decks that will be used in the game.
     */
    public static Decks decks;

    /**
     * main function all program. In cycle run rounds while in Deck more then n cards.
     */
    public static void play() {
        System.out.print("Добро пожаловать в Блэкджек!\n");
        Utils.wait(1);
        decks = new Decks(Round.ask("На сколько колод желаете сыграть?\n"));
        while (decks.size >= 23) {
            Round round = new Round(++roundNumber, decks);
            String resultMsg = "";
            int res = round.start();
            if (res == win) {
                playerScore++;
                resultMsg = resultMsg.concat("Вы выиграли раунд!");
            } else if (res == draw) {
                resultMsg = resultMsg.concat("Ничья!");
            } else if (res == lose) {
                dealerScore++;
                resultMsg = resultMsg.concat("Победа за дилером!");
            }
            System.out.printf(resultMsg + " Счет %d:%d\n", playerScore, dealerScore);
            Utils.wait(2);
        }
    }
}