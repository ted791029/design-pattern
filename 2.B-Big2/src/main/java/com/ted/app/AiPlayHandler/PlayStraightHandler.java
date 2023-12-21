package com.ted.app.AiPlayHandler;

import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPatternHandler.CardPatternHandler;
import com.ted.app.Hand;

import java.util.Optional;

public class PlayStraightHandler extends AIPlayHandler {
    public PlayStraightHandler(AIPlayHandler next, CardPatternHandler cardPatternHandler) {
        super(next, cardPatternHandler);
    }

    @Override
    protected boolean match(CardPattern topPlay) {
        return false;
    }

    @Override
    protected Optional<CardPattern> doHandling(CardPattern topPlay, Hand hand) {
        return Optional.empty();
    }
}
