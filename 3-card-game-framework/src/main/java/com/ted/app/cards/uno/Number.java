package com.ted.app.cards.uno;

public enum Number {

    ACE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10");


    private String symbol;

    Number(String symbol) {
        setSymbol(symbol);
    }

    //==========================================================//

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
