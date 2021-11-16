package com.cards.poker.objects;

import com.cards.poker.constant.CardNumber;
import com.cards.poker.constant.Suit;

/**
 * An object to represent a card which contains a Suit and a Number
 */
public class Card implements Comparable<Card> {

    private Suit suit;
    private CardNumber cardNumber;

    public Card(Suit suit, CardNumber cardNumber) {
        this.suit = suit;
        this.cardNumber = cardNumber;
    }

    @Override
    public int compareTo(Card o) {
        return cardNumber.compareTo(o.getNumber());
    }

    public String toString() {
        return cardNumber + " of " + suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    public void setNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }
}
