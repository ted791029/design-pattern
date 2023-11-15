package com.ted.app;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;

import java.util.List;

public class Rule {
    private Round round;

    private CardPattern cardPattern;

    public boolean isValid(List<Card> playCards){
        return false;
    }

    public boolean isContainClubsThree(List<Card> playCards){
        return false;
    }
}
