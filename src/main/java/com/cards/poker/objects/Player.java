package com.cards.poker.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Object class to represent the player
 */
public class Player {
    private Card[] card;
    private Boolean[] selectedCards;

    public Player() {
        card = new Card[5];
        selectedCards = new Boolean[]{false, false, false, false, false};
    }

    /**
     * Draw five card from the deck and add to the player's hand
     * @param deck
     */
    public void drawHand(Deck deck) {
        for (int i = 0; i < 5; i++) {
            Card newCard = deck.draw();
            card[i] = newCard;
        }
    }

    /**
     * Discard the cards from the player's hand based on what was selected.
     * @param deck
     */
    public void discardCards(Deck deck) {
        List<Card> discardedCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (selectedCards[i] == true) {
                card[i] = deck.draw();
            }
        }
    }


    public Card[] getCards() {
        return card;
    }

    public Boolean[] getSelectedCards() {
        return selectedCards;
    }

}
