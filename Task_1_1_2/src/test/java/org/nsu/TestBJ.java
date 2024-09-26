package org.nsu;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;

class TestBJ {

    @Nested
    class TestCard {

        void assertEq(String name, Card card) {
            Assertions.assertEquals(name, card.toString());
        }

        @org.junit.jupiter.api.Test
        void testValue() {
            Assertions.assertEquals(new Card("", (byte) 2).getName(), "Двойка");
            Assertions.assertEquals(new Card("", (byte) 7).getName(), "Семерка");
            Assertions.assertEquals(new Card("", (byte) 11).getName(), "Валет");
            Assertions.assertEquals(new Card("", (byte) 14).getName(), "Туз");
        }

        @org.junit.jupiter.api.Test
        void testNames() {
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
    }

    @Nested
    class TestHandCards {

        @org.junit.jupiter.api.Test
        void testSuma() {
            HandCards cards = new HandCards();
            cards.append(new Card("Бубей", Card.two));
            cards.append(new Card("Черви", Card.five));
            cards.append(new Card("Бубей", Card.seven));
            Assertions.assertEquals(cards.sum, 14);
            cards.append(new Card("Бубей", Card.Ace));
            Assertions.assertEquals(cards.sum, 15);
            cards.append(new Card("Черви", Card.Ace));
            Assertions.assertEquals(cards.sum, 16);
            HandCards aces = new HandCards();
            aces.append(new Card("Черви", Card.Ace));
            Assertions.assertEquals(aces.sum, 11);
            aces.append(new Card("Бубей", Card.Ace));
            Assertions.assertEquals(aces.sum, 2);
        }

        @org.junit.jupiter.api.Test
        void testCard2String() {
            HandCards cards = new HandCards();
            cards.append(new Card("Бубей", (byte) 2));
            cards.append(new Card("Пик", (byte) 13));
            cards.append(new Card("Пик", (byte) 14));
            Assertions.assertEquals(cards.cards2String(), "[Двойка Бубей (2), Король Пик (10), Туз Пик (1)] ==> (13)");
            Assertions.assertEquals(13, cards.sum);
            Assertions.assertEquals(23, cards.realSum);
        }
    }

    @Nested
    class TestDeck {

        @org.junit.jupiter.api.Test
        void testDeck() {
            Decks decks = new Decks(1);
            Assertions.assertEquals((int) decks.size, 52);
            Card card = decks.popCard();
            Assertions.assertEquals((int) decks.size, 51);
            Assertions.assertFalse(Arrays.asList(decks.cards).contains(card));
            for (int i = 0; i < 60; i++) {
                card = decks.popCard();
            }
            Assertions.assertNull(card);
        }
    }

    @Nested
    class TestRound {

        @org.junit.jupiter.api.Test
        void testRound() {
            Round round = new Round(1, new Decks(1));
            try {
                Assertions.assertEquals(round.decks.size, 52 - 3);
                Assertions.assertEquals(round.playerCards.cards.size(), 2);
                Assertions.assertEquals(round.dealerCards.cards.size(), 1);
                round.printCards();
                round.move("1", round.playerCards);
                round.move("2", round.dealerCards);
                round.moveDealer();
                round.movePlayer();
            } catch (Exception e) {
                Assertions.fail();
            }
            Round round2 = new Round(1, new Decks(1));
            try {
                Assertions.assertEquals(round2.decks.size, 52 - 3);
                Assertions.assertEquals(round2.playerCards.cards.size(), 2);
                Assertions.assertEquals(round2.dealerCards.cards.size(), 1);
                round2.printCards();
                round2.move("1", round2.playerCards);
                round2.move("2", round2.dealerCards);
                round2.moveDealer();
                round2.movePlayer();
            } catch (Exception e) {
                Assertions.fail();
            }
        }
    }
}
