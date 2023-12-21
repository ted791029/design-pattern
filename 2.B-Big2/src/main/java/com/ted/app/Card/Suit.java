package com.ted.app.Card;

public enum Suit {

    SPADE("S", 3),
    HEART("H", 2),
    DIAMOND("D", 1),
    CLUB("C", 0);

    private String symbol;
    private int score;

    Suit(String symbol, int score) {
        setSymbol(symbol);
        setScore(score);
    }

    public boolean compare(Suit suit){
        return score > suit.score;
    }

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
