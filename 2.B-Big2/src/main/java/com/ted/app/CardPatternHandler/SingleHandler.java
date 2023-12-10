package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Single;
import com.ted.app.CardPattern.Straight;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SingleHandler extends CardPatternHandler{

    public SingleHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Card> playCards) {
        boolean sizeValid = playCards.size() == 1;
        return sizeValid;
    }

    @Override
    protected Optional<CardPattern> doHandling(List<Card> playCards) {
        return Optional.of(new Single(playCards));
    }
}
