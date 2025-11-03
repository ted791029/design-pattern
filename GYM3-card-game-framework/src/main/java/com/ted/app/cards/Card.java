package com.ted.app.cards;

import com.ted.app.cards.pocker.PokerCard;
import com.ted.app.cards.uno.UnoCard;

public class Card {
    public int showdown(Card card){
        return 0;
    }

    public boolean isCanShow(Card card){
        return true;
    }

    public boolean isPokerCard(){
        return getClass() == PokerCard.class;
    }

    public boolean isUnoCard(){
        return getClass() == UnoCard.class;
    }
}
