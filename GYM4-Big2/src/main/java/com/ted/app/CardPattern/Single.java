package com.ted.app.CardPattern;

import com.ted.app.Card.Card;

import java.util.List;

public class Single extends CardPattern {
    public Single(List<Card> cards) {
        super(cards, "單張", 1, 1);
    }
}
