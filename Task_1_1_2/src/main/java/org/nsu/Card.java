package org.nsu;

/**
 * Card have type and value have method getCount, and override toString.
 */
public class Card {
    String type;
    byte value;

    /**
     * Constructor Card from type and value.
     *
     * @param typeCard :
     * @param valueCard : 1 - 14 (1 - overAce, 11 - j ... 14 - Ace)
     */
    public Card(String typeCard, byte valueCard) {
        super();
        type = typeCard;
        value = valueCard;
    }

    /**
     * return count of card.
     *
     * @return count of card.
     */
    byte getCount() {
        if (value == 14) {
            return 11;
        }
        return value > 10 ? 10 : value;
    }

    @Override
    public String toString() {
        return Utils.get(value) + " " + type + " (" + getCount() + ")";
    }
}