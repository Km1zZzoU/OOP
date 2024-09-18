package org.nsu;

import java.util.ArrayList;

/**
 * array cards, have fields: cards, sum realsum (with/wthout  Ace).
 */
public class Cards {

    /**
     * field for cards, have method toString.
     */
    public ArrayList<Card> cards;
    /**
     * field for current sum for cards.
     */
    public Integer sum;
    int realSum;

    /**
     * Constructor, init cards = ArratList and sums.
     */
    public Cards() {
        cards = new ArrayList<>();
        sum = 0;
        realSum = 0;
    }

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
        if (realSum > 21 && card.value == 14) {
            card.value = 1;
            for (Card cardIter : cards) {
                if (cardIter.value == 14) {
                    cardIter.value -= 10;
                    sum -= 10;
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
        msgCards = msgCards.concat("] ==> (" + sum.toString() + ")");
        return msgCards;
    }
}
