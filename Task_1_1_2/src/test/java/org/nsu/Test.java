package org.nsu;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

class Test {

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

    @org.junit.jupiter.api.Test
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
        Cards aces = new Cards();
        aces.append(new Card("Черви", (byte) 14));
        Assertions.assertEquals(aces.sum, 11);
        aces.append(new Card("Бубей", (byte) 14));
        Assertions.assertEquals(aces.sum, 2);
    }

    @org.junit.jupiter.api.Test
    void testDeck() {
        Deck deck = new Deck(1);
        Assertions.assertEquals((int) deck.size, 52);
        Card card = deck.popCard();
        Assertions.assertEquals((int) deck.size, 51);
        Assertions.assertFalse(Arrays.asList(deck.cards).contains(card));
    }

    @org.junit.jupiter.api.Test
    void testCard2String() {
        Cards cards = new Cards();
        cards.append(new Card("Бубей", (byte) 2));
        cards.append(new Card("Пик", (byte) 13));
        cards.append(new Card("Пик", (byte) 14));
        Assertions.assertEquals(cards.cards2String(), "[Двойка Бубей (2), Король Пик (10), Туз Пик (1)] ==> (13)");
        Assertions.assertEquals(13, cards.sum);
        Assertions.assertEquals(23, cards.realSum);
    }

    @org.junit.jupiter.api.Test
    void testUtils() {
        Assertions.assertEquals(Utils.get((byte) 2), "Двойка");
        Assertions.assertEquals(Utils.get((byte) 7), "Семерка");
        Assertions.assertEquals(Utils.get((byte) 11), "Валет");
        Assertions.assertEquals(Utils.get((byte) 14), "Туз");
    }
}
