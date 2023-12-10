package com.ted.app.Card;

public class Card {
    private Rank rank;

    private Suit suit;


    public Card(Rank rank, Suit suit){
        setRank(rank);
        setSuit(suit);
    }

    public boolean compare(Card card){
        if(rank == card.rank){
            return suit.compare(card.suit);
        }
        return rank.compare(card.rank);
    }

    public int getRankScore(){
        return rank.getScore();
    }

    public String getRankSymbol(){
        return rank.getSymbol();
    }

    public String getSuitSymbol(){
        return suit.getSymbol();
    }

    /**getter & setter**/

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
