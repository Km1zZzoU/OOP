package org.nsu;

import java.util.Random;

/**
 * class Deck.
 * needed for generate deck and actions with deck.
 */
public class Decks {
    private final String[] types = {"Бубей", "Черви", "Пик", "Крести"};
    private final Byte[] values = {Card.two, Card.three, Card.four, Card.five, Card.six, Card.seven, Card.eight, Card.nine,
        Card.ten, Card.Jack, Card.Queen, Card.King, Card.Ace};

    /**
     * size of Deck.
     */
    public Integer size;
    /**
     * current cards of deck.
     */
    public Card[] cards;

    /**
     * Construct cards where made all cards and append to field cards.
     * @param count count of Decks
     */
    public Decks(int count) {
        size = 52 * count;
        cards = new Card[size];
        for (int i = 0; i < size; i++) {
            cards[i] = new Card(types[i % 4], values[(i % 52) / 4]);
        }
    }

    /**
     * delete a card from the deck
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
     * pop and delete a random card.
     * if deck is empty return nil.
     * @return random card.
     */
    public Card popCard() {
        if (size == 0) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(size);
        Card returnCard = cards[index];
        deleteCard(index);
        return returnCard;
    }
}
