package org.nsu;

/**
 * Card have type and value have method getCount, and override toString.
 */
public class Card {
    String type;
    public Byte value;
    static final byte AceOne = 1;
    static final byte two = 2;
    static final byte three = 3;
    static final byte four = 4;
    static final byte five = 5;
    static final byte six = 6;
    static final byte seven = 7;
    static final byte eight = 8;
    static final byte nine = 9;
    static final byte ten = 10;
    static final byte Jack = 11;
    static final byte Queen = 12;
    static final byte King = 13;
    static final byte Ace = 14;
    static final byte picture = 10;


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
    public byte getCount() {
        if (value == Ace) {
            return 11;
        }
        return value > picture ? picture : value;
    }

    /**
     * returns the correct string name on russian by value card.
     *
     * @return string
     */
    public String getName() {
        switch (value) {
            case two: {
                return "Двойка";
            }
            case three: {
                return "Тройка";
            }
            case four: {
                return "Четверка";
            }
            case five: {
                return "Пятерка";
            }
            case six: {
                return "Шестерка";
            }
            case seven: {
                return "Семерка";
            }
            case eight: {
                return "Восьмерка";
            }
            case nine: {
                return "Девятка";
            }
            case ten: {
                return "Десятка";
            }
            case Jack: {
                return "Валет";
            }
            case Queen: {
                return "Дама";
            }
            case King: {
                return "Король";
            }
            default: {
                return "Туз";
            }
        }
    }


    @Override
    public String toString() {
        return new Card(type, value).getName() + " " + type + " (" + getCount() + ")";
    }
}