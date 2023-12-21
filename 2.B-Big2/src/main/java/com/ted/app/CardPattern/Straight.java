package com.ted.app.CardPattern;

import com.ted.app.Card.Card;

import java.util.List;

public class Straight extends CardPattern{
    public Straight(List<Card> cards) {
        super(cards, "順子", 5, 5);
    }
}
