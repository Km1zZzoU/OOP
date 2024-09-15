package org.nsu;

import java.util.Random;

/**
 * class Deck.
 * Have 2 fields: size and ccards[].
 * Have method for pop card.
 */
public class Deck extends Utils{

    /**
     * size of Deck
     */
    public Byte size;
    /**
     * current cards of deck
     */
    public Card[] cards;

    /**
     * Construct cards where made all cards and append to field cards.
     */
    public Deck() {
        cards = new Card[52];
        size = 52;
        String[] types = {"Бубей", "Черви", "Пик", "Крести"};
        Byte[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        for (int i = 0; i < size; i++) {
            cards[i] = new Card(types[i % 4], values[i / 4]);
        }
    }

    /**
     * delete card and copy all cards after on index++
     *
     * @param index of delCard
     */
    public void deleteCard(int index) {
        size--;
        for (int i = index; i < size; i++) {
            cards[i] = cards[i + 1];
        }
    }

    /**
     * pop and delete card with random
     *
     * @return random card
     */
    public Card popCard() {
        Random rand = new Random();
        int index = rand.nextInt(size);
        Card returnCard = cards[index];
        deleteCard(index);
        return returnCard;
    }
}
