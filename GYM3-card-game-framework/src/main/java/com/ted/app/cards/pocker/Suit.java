package com.ted.app.cards.pocker;

public enum Suit {

    SPADE("黑桃", 3),
    HEART("愛心", 2),
    DIAMOND("鑽石", 1),
    CLUB("梅花", 0);

    private String symbol;
    private int score;

    Suit(String symbol, int score) {
        setSymbol(symbol);
        setScore(score);
    }

    public int showdown(Suit suit) {
        if(score > suit.score){
            return 1;
        }else if(score < suit.score){
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
