package org.nsu;


import java.util.Scanner;

/**
 * class for actions into round.
 * init new in cycle in game.
 */
public class Round {

    HandCards playerCards = new HandCards();
    HandCards dealerCards = new HandCards();
    Decks decks;

    /**
     * init 2 cards for player and 1 card for dealer.
     *
     * @param number - number of round.
     * @param decks   - do not create new decks, take decks from field of Game.
     */
    public Round(int number, Decks decks) {
        if (decks == null) {
            return;
        }

        this.decks = decks;
        System.out.printf("Раунд %d\nВ игре %d карт\n", number, decks.size);
        Utils.wait(1);
        playerCards.append(decks.popCard());
        dealerCards.append(decks.popCard());
        playerCards.append(decks.popCard());
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
     * @param question question
     * @return answer
     */
    public static int ask(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * function where is played every round.
     *
     * @return win player (true - player win, false - dealer win)
     */
    public int start() {
        System.out.print("Дилер раздал карты\n");
        printCards();
        if (playerCards.sum == 21) {
            System.out.print("Блэкджджек!\n");
            return 1;
        }
        System.out.print("Ваш ход\n");
        while (ask("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...") == 1) {
            if (movePlayer()) {
                return -1;
            } else if (playerCards.sum == 21) {
                System.out.print("Блэкджджек!\n");
                return 1;
            }
        }
        System.out.print("Ход дилера\n");
        moveDealer();
        if (dealerCards.sum == 21) {
            System.out.print("Блэкджджек!\n");
            return -1;
        }
        if (dealerCards.sum > 21 || playerCards.sum > dealerCards.sum) {
            return 1;
        } else if (playerCards.sum.equals(dealerCards.sum)) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * void function for base step of round. Take card from decks, print card and add card to hand.
     *
     * @param msg   message from player/dealer.
     * @param cards cards from hand player/dealer.
     */
    public void move(String msg, HandCards cards) {
        if (cards == null) {
            return;
        }
        Card newCard = decks.popCard();
        System.out.print("+------------------------------------------------\n");
        System.out.print("| " + msg + newCard.toString() + "\n");
        System.out.print("+------------------------------------------------\n");
        Utils.wait(1);
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
     * Dealer move. in cycle dealer take card. When sum(cards) more then 17 return;.
     */
    public void moveDealer() {
        while (dealerCards.sum < 17) {
            move("Дилер открывает карту ", dealerCards);
            Utils.wait(2);
        }
    }
}
