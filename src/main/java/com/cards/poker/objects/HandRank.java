package com.cards.poker.objects;

import com.cards.poker.constant.CardNumber;
import com.cards.poker.constant.CardRank;
import com.cards.poker.constant.Suit;

import java.util.Arrays;

/**
 * A hand rank to determine the player's hand
 */
public class HandRank implements  Comparable<HandRank>{

    private CardRank rank;
    private CardNumber highestCard;
    private Card[] cards;

    public HandRank(Card[] cards) {
        this.cards = cards;
        getHandRank();
    }

    /**
     * Called to check the hand rank based on standard poker rule
     */
    private void getHandRank() {
        Arrays.sort(cards);
        boolean hasFlush = checkFlush(cards);
        boolean hasStraight = checkStraight(cards);
        CardNumber highestNumber = cards[4].getNumber();
        if(hasFlush && hasStraight && cards[4].getNumber().equals(CardNumber.ACE)) {
            if(cards[0].getNumber().equals(CardNumber.TEN)) {
                rank = CardRank.ROYAL_FLUSH;
                highestCard = highestNumber;
            }else{
                rank = CardRank.STRAIGHT_FLUSH;
                highestCard = CardNumber.FIVE;
            }
        }else if(hasFlush && hasStraight) {
            rank = CardRank.STRAIGHT_FLUSH;
            highestCard = highestNumber;
        }else if(hasFlush) {
            rank = CardRank.FLUSH;
            highestCard = highestNumber;
        }else if(hasStraight) {
            if(cards[0].getNumber() == CardNumber.TWO) {
                highestCard = CardNumber.FIVE;
            }else {
                highestCard = highestNumber;
            }
            rank = CardRank.STRAIGHT;
        }else {
            getSimiliarCard(cards);
        }
    }

    /**
     * Check for flush
     * @param cards to check the rank
     * @return true when the hand is a flush
     */
    private boolean checkFlush(Card[] cards) {
        Suit startSuit = cards[0].getSuit();
        for(int i = 1; i < 5; i++) {
            if(cards[i].getSuit() != startSuit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for straight and let the ACE be lowest or biggest card
     * @param cards to check the rank
     * @return true when the hand is a straight
     */
    private boolean checkStraight(Card[] cards) {
        int number = cards[0].getNumber().getNumber();
        if(cards[0].getNumber().equals(CardNumber.TWO) &&
                cards[1].getNumber().equals(CardNumber.THREE) &&
                cards[2].getNumber().equals(CardNumber.FOUR) &&
                cards[3].getNumber().equals(CardNumber.FIVE) &&
                cards[4].getNumber().equals(CardNumber.ACE)) {
            return true;
        }
        for(int i = 1; i< 5; i++) {
            if(number + i != cards[i].getNumber().getNumber()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for similiar cards such as four of the same, three of the same, two pair, or pair
     * @param cards to check the rank
     * @return the hand's rank and highest card
     */
    private void getSimiliarCard(Card[] cards) {
        CardNumber currentNumber = cards[0].getNumber();
        int numberOfCards = 1;
        for(int i = 1; i < 5; i++) {
            if(cards[i].getNumber() == currentNumber) {
                numberOfCards++;
            }else if(cards[i].getNumber() != currentNumber) {
                if(numberOfCards == 4) {
                    break;
                }else if(numberOfCards == 3) {
                    rank = CardRank.THREE_CARD;
                    highestCard = currentNumber;
                }else if(numberOfCards == 2) {
                    if(rank == null) {
                        rank = CardRank.PAIR;
                        highestCard = currentNumber;
                    }else {
                        rank = CardRank.TWO_PAIR;
                        highestCard = currentNumber;
                    }
                }
                numberOfCards = 1;
                currentNumber = cards[i].getNumber();
            }
        }
        if(numberOfCards == 4) {
            rank = CardRank.FOUR_CARD;
            highestCard = currentNumber;
        }else if(numberOfCards == 3) {
            if(rank == null) {
                rank = CardRank.THREE_CARD;
                highestCard = currentNumber;
            }else if(rank == CardRank.PAIR) {
                rank = CardRank.FULL_HOUSE;
                highestCard = currentNumber;
            }
        }else if(numberOfCards == 2) {
            if(rank == null) {
                rank = CardRank.PAIR;
                highestCard = currentNumber;
            }else if(rank == CardRank.THREE_CARD) {
                rank = CardRank.FULL_HOUSE;
            }else{
                rank = CardRank.TWO_PAIR;
                highestCard = currentNumber;
            }
        }else if(rank == null){
            rank = CardRank.HIGH;
            highestCard =currentNumber;
        }
    }

    @Override
    public int compareTo(HandRank o) {
        if(rank.equals(o.getRank())) {
            if(highestCard == null) {
                return -1;
            }else if(o.getHighestCard() == null) {
                return 1;
            }
            return highestCard.compareTo(o.highestCard);
        }else{
            return rank.compareTo(o.getRank());
        }
    }

    @Override
    public String toString() {
        if(highestCard == CardNumber.ACE) {
            return "Hand Rank: " + rank.getRankName() + " Highest card: Ace";
        }
        return "Hand Rank: " + rank.getRankName() + " Highest card: "+ highestCard.getCardName();
    }

    public CardRank getRank() {
        return rank;
    }

    public void setRank(CardRank rank) {
        this.rank = rank;
    }

    public CardNumber getHighestCard() {
        return highestCard;
    }

    public void setHighestCard(CardNumber highestCard) {
        this.highestCard = highestCard;
    }

}
