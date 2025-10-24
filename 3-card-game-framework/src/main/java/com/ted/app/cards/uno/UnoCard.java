package com.ted.app.cards.uno;

import com.ted.app.cards.Card;
import com.ted.app.cards.pocker.PokerCard;
import com.ted.app.cards.pocker.Rank;
import com.ted.app.cards.pocker.Suit;

public class UnoCard extends Card {

    private Color color;

    private Number number;

    public UnoCard(Color color, Number number) {
        setColor(color);
        setNumber(number);
    }

    @Override
    public boolean isCanShow(Card card){
        if(!card.isUnoCard()){
            throw new RuntimeException("卡片非Uno牌");
        }

        Number sourceNumber = getNumber();
        Color sourceColor = getColor();
        UnoCard target = (UnoCard) card;
        Number targetNumber = target.getNumber();
        Color targetColor = target.getColor();
        return sourceNumber.getSymbol() == targetNumber.getSymbol() || sourceColor.getSymbol() == targetColor.getSymbol();
    }

    @Override
    public String toString(){
        Number number = getNumber();
        Color color = getColor();
        return color.getSymbol() + number.getSymbol();
    }

    //==========================================================//

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
