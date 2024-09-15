package org.nsu;

import java.util.Scanner;

/**
 * class child of game. have 2 field, init from 2 args: number of round and deck.
 */
class Round extends Game {

    Cards playerCards = new Cards();
    Cards dealerCards = new Cards();

    /**
     * init 2 cards for player and 1 card for dealer.
     *
     * @param number - number of round.
     * @param deck   - do not create new deck, take deck from field of Game.
     */
    public Round(int number, Deck deck) {
        if (deck == null) {
            return;
        }

        this.deck = deck;
        System.out.printf("Раунд %d\nВ игре %d\\52 карт\n", number, this.deck.size);
        wait(1);
        playerCards.append(deck.popCard());
        dealerCards.append(deck.popCard());
        playerCards.append(deck.popCard());
    }

    /**
     * Print all cards from hands.
     */
    public void printCards() {
        System.out.print("          Ваши карты:  ".concat(playerCards.cards2String()) + "\n");
        System.out.print("        Карты диллера: ".concat(dealerCards.cards2String()) + "\n");
    }

    /**
     * ask player and scan answer.
     *
     * @return answer
     */
    public int ask() {
        System.out.print("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * function where is played every round.
     *
     * @return win player (true - player win, false - dealer win)
     */
    public boolean start() {
        System.out.print("Дилер раздал карты\n");
        printCards();
        if (playerCards.sum == 21) {
            System.out.print("Блэкджджек!\n");
            return true;
        }
        System.out.print("Ваш ход\n");
        while (ask() == 1) {
            if (movePlayer()) {
                return false;
            } else if (playerCards.sum == 21) {
                System.out.print("Блэкджджек!\n");
                return true;
            }
        }
        System.out.print("Ход дилера\n");
        moveDealer();
        if (playerCards.sum == 21) {
            System.out.print("Блэкджджек!\n");
            return false;
        }
        return dealerCards.sum > 21 || playerCards.sum >= dealerCards.sum;
    }

    /**
     * void function for base step of round. Take card from deck, print card and add card to hand.
     *
     * @param msg   message from player/dealer.
     * @param cards cards from hand player/dealer.
     */
    public void move(String msg, Cards cards) {
        if (cards == null) {
            return;
        }
        Card newCard = deck.popCard();
        System.out.print("+------------------------------------------------\n");
        System.out.print("| " + msg + newCard.toString() + "\n");
        System.out.print("+------------------------------------------------\n");
        wait(2);
        cards.append(newCard);
        printCards();
    }

    /**
     * Player move. call function move and return false if player lose.
     *
     * @return true - player do not win / false - player lose.
     */
    public boolean movePlayer() {
        move("Вы открыли карту ", playerCards);
        return playerCards.sum > 21;
    }

    /**
     * Dealer move. in cycle dealer take card. When sum(cards) >= 17 return;.
     */
    public void moveDealer() {
        while (dealerCards.sum < 17) {
            move("Дилер открывает карту ", dealerCards);
            wait(2);
        }
    }
}

/**
 * class Game when is played all game. have 4 field: score for player and dealer, number of round.
 * and deck.
 */
public class Game extends Utils {

    int playerScore;
    int dealerScore;
    int roundNumber;
    Deck deck;

    /**
     * init score = 0 and round = 1.
     */
    public Game() {
        playerScore = 0;
        dealerScore = 0;
        roundNumber = 1;
        //dont init deck
    }

    /**
     * main function all program. In cycle run rounds while in Deck >= n cards.
     */
    public void play() {
        System.out.print("Добро пожаловать в Блэкджек!\n");
        deck = new Deck();
        while (deck.size >= 13) {
            Round round = new Round(roundNumber++, deck);
            String resultMsg = "";
            if (round.start()) {
                playerScore++;
                resultMsg = resultMsg.concat("Вы выиграли раунд!");
            } else {
                dealerScore++;
                resultMsg = resultMsg.concat("Победа за дилером!");
            }
            System.out.printf(resultMsg + " Счет %d:%d\n", playerScore, dealerScore);
            wait(3);
        }
    }
}
