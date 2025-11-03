package com.ted.app.cards.pocker;

public enum Rank {
    ACE("A", 11),
    TWO("2", 12),
    THREE("3", 0),
    FOUR("4", 1),
    FIVE("5", 2),
    SIX("6", 3),
    SEVEN("7", 4),
    EIGHT("8", 5),
    NINE("9", 6),
    TEN("10", 7),
    JACK("J", 8),
    QUEEN("Q", 9),
    KING("K", 10);

    private String symbol;
    private int score;


    Rank(String symbol, int score) {
        setScore(score);
        setSymbol(symbol);
    }

    public int showdown(Rank rank) {
        if(score > rank.score){
            return 1;
        }else if(score < rank.score){
            return -1;
        }
        return 0;
    }

    //==========================================================//
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
