package org.nsu;

import java.util.ArrayList;

/**
 * class for storing and handling cards in the hands of a dealer or player.
 * usage into Round.
 */
public class HandCards {

    /**
     * field for cards, have method toString.
     */
    public ArrayList<Card> cards = new ArrayList<>();
    /**
     * field for current sum for cards.
     */
    public Integer sum = 0;
    int realSum = 0;

    /**
     * function for append card to cards and to calculate sum and realSum.
     *
     * @param card card
     */
    public void append(Card card) {
        if (card == null) {
            return;
        }
        realSum += card.getCount();
        if (realSum > 21 && card.value == Card.Ace) {
            card.value = Card.AceOne;
            for (Card cardIter : cards) {
                if (cardIter.value == Card.Ace) {
                    cardIter.value = Card.AceOne;
                    sum -= (Card.Ace - Card.AceOne);
                }
            }
        }
        sum += card.getCount();
        cards.add(card);
    }

    /**
     * method cards to string.
     *
     * @return string
     */
    public String cards2String() {
        String msgCards = "[";
        for (int i = 0; i < cards.size(); i++) {
            msgCards = msgCards.concat(cards.get(i).toString());
            if (i != cards.size() - 1) {
                msgCards = msgCards.concat(", ");
            }
        }
        if (cards.size() == 1) {
            msgCards = msgCards.concat(", <закрытая карта>");
        }
        msgCards = msgCards.concat("] ==> (" + sum.toString() + ")");
        return msgCards;
    }
}
