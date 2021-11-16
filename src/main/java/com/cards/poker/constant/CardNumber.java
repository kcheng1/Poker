package com.cards.poker.constant;

/**
 * The card number as well as a letter to determine which image to show
 */
public enum CardNumber {
    TWO(2, "2", "2"),
    THREE(3, "3", "3"),
    FOUR(4, "4", "4"),
    FIVE(5, "5", "5"),
    SIX(6, "6", "6"),
    SEVEN(7, "7", "7"),
    EIGHT(8, "8", "8"),
    NINE(9, "9", "9"),
    TEN(10, "T", "10"),
    JACK(11, "J", "Jack"),
    QUEEN(12, "Q", "Queen"),
    KING(13, "K", "King"),
    ACE(14, "A", "Ace");

    private final int number;
    private final String letter;
    private final String cardName;

    CardNumber(int number, String letter, String cardName) {
        this.number = number;
        this.letter = letter;
        this.cardName = cardName;
    }

    public int getNumber() {
        return number;
    }

    public String getLetter() {
        return letter;
    }

    public String getCardName() {
        return cardName;
    }
}
