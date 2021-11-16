package com.cards.poker.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cards.poker.constant.CardNumber;
import com.cards.poker.constant.Suit;

/**
 * A Deck of cards
 */
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        createDeck();
    }

    /**
     * Populate the deck with 52 standard cards
     */
    private void createDeck() {
        for (Suit suit : Suit.values()) {
            for (CardNumber number : CardNumber.values()) {
                Card card = new Card(suit, number);
                cards.add(card);
            }
        }
        shuffle();
    }

    /**
     * Shuffle the deck to randomize the card
     */
    private void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Remove a card from the deck and return it for more logic
     * @return The first card in the deck and remove it
     */
    public Card draw() {
        return cards.remove(0);
    }
}
