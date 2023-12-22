package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Single;

import java.util.List;
import java.util.Optional;

public class SingleHandler extends CardPatternHandler {

    public SingleHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    public boolean match(List<Card> playCards) {
        boolean sizeValid = playCards.size() == 1;
        return sizeValid;
    }

    @Override
    protected Optional<CardPattern> doHandling(List<Card> playCards) {
        return Optional.of(new Single(playCards));
    }
}
