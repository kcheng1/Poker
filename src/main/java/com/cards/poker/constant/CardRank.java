package com.cards.poker.constant;

/**
 * The card's ranking
 */
public enum CardRank {

    HIGH("High Card"),
    PAIR("Pair"),
    TWO_PAIR("Two Pairs"),
    THREE_CARD("Three Of A Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_CARD("Four Card"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");

    private String rankName;

    CardRank(String rankName)
    {
        this.rankName = rankName;
    }

    public String getRankName() {
        return rankName;
    }
}
