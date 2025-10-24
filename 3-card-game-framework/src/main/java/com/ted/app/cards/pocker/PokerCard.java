package com.ted.app.cards.pocker;

import com.ted.app.cards.Card;

public class PokerCard extends Card {

    private Rank rank;

    private Suit suit;

    public PokerCard(Rank rank, Suit suit) {
        setRank(rank);
        setSuit(suit);
    }

    @Override
    public int showdown(Card card){

        if(!card.isPokerCard()) {
            throw new RuntimeException("卡片非撲克牌");
        }

        Rank sourceRank = getRank();
        Suit sourceSuit = getSuit();
        PokerCard target = (PokerCard) card;
        Rank targetRank = target.getRank();
        Suit targetSuit = target.getSuit();

        if(sourceRank.showdown(targetRank) == 0){
            return sourceSuit.showdown(targetSuit);
        }
        return sourceRank.showdown(targetRank);
    }

    @Override
    public String toString(){
        Rank rank = getRank();
        Suit suit = getSuit();
        return suit.getSymbol() + rank.getSymbol();
    }

    //==========================================================//

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
