package com.ted.app.Card;

public enum Suit {

    SPADE("黑桃", 3),
    HEART("愛心", 2),
    DIAMOND("菱形", 1),
    CLUB("梅花", 0);

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
