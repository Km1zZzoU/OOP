package org.nsu.Test;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.nsu.*;

import org.junit.jupiter.api.Assertions;

class TestBJ {

    void assertEq(String name, Card card) {
        Assertions.assertEquals(name, card.toString());
    }

    @org.junit.jupiter.api.Test
    void testnames() {
        assertEq("Двойка Бубей (2)", new Card("Бубей", (byte) 2));
        assertEq("Двойка Черви (2)", new Card("Черви", (byte) 2));
        assertEq("Двойка Пик (2)", new Card("Пик", (byte) 2));
        assertEq("Двойка Крести (2)", new Card("Крести", (byte) 2));
        assertEq("Тройка Бубей (3)", new Card("Бубей", (byte) 3));
        assertEq("Тройка Черви (3)", new Card("Черви", (byte) 3));
        assertEq("Тройка Пик (3)", new Card("Пик", (byte) 3));
        assertEq("Тройка Крести (3)", new Card("Крести", (byte) 3));
        assertEq("Пятерка Бубей (5)", new Card("Бубей", (byte) 5));
        assertEq("Пятерка Черви (5)", new Card("Черви", (byte) 5));
        assertEq("Пятерка Пик (5)", new Card("Пик", (byte) 5));
        assertEq("Пятерка Крести (5)", new Card("Крести", (byte) 5));
    }

    @Test
    void testSuma() {
        Cards cards = new Cards();
        cards.append(new Card("Бубей", (byte) 2));
        cards.append(new Card("Черви", (byte) 5));
        cards.append(new Card("Бубей", (byte) 7));
        Assertions.assertEquals(cards.sum, 14);
        cards.append(new Card("Бубей", (byte) 14));
        Assertions.assertEquals(cards.sum, 15);
        cards.append(new Card("Черви", (byte) 14));
        Assertions.assertEquals(cards.sum, 16);
        Cards Aces = new Cards();
        Aces.append(new Card("Черви", (byte) 14));
        Assertions.assertEquals(Aces.sum, 11);
        Aces.append(new Card("Бубей", (byte) 14));
        Assertions.assertEquals(Aces.sum, 2);
    }

    @Test
    void testDeck() {
        Deck deck = new Deck();
        Assertions.assertEquals((int) deck.size, 52);
        Card card = deck.popCard();
        Assertions.assertEquals((int) deck.size, 51);
        Assertions.assertFalse(Arrays.asList(deck.cards).contains(card));
    }
}
