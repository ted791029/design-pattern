package com.ted.app.cards.uno;

public enum Color {

    Red("紅"),
    BLUE("藍"),
    YELLOW("黃"),
    GREEN("綠");


    private String symbol;

    Color(String symbol) {
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
