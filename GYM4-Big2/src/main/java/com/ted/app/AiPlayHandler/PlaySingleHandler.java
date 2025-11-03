package com.ted.app.AiPlayHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Single;
import com.ted.app.CardPatternHandler.SingleHandler;
import com.ted.app.Hand;

import java.util.ArrayList;
import java.util.List;

public class PlaySingleHandler extends AIPlayHandler {
    public PlaySingleHandler(AIPlayHandler next) {
        super(next, new SingleHandler(null));
    }

    @Override
    protected boolean match(CardPattern topPlay) {
        return topPlay.getClass().equals(Single.class);
    }

    @Override
    protected List<Card> doHandling(CardPattern topPlay, Hand hand) {
        List<Card> cards = hand.getCards();
        Single toplaySingle = new Single(topPlay.getCards());
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            List<Card> playCards = new ArrayList<>();
            playCards.add(card);
            Single handSingle = new Single(playCards);
            if (cardPatternHandler.match(playCards) && handSingle.compare(toplaySingle)) {
                return playCards;
            }
        }
        return new ArrayList<>();
    }
}
