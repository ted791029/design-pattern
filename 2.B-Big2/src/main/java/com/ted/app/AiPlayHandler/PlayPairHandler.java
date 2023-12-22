package com.ted.app.AiPlayHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Pair;
import com.ted.app.CardPatternHandler.PairHandler;
import com.ted.app.Hand;

import java.util.ArrayList;
import java.util.List;

public class PlayPairHandler extends AIPlayHandler {
    public PlayPairHandler(AIPlayHandler next) {
        super(next, new PairHandler(null));
    }

    @Override
    protected boolean match(CardPattern topPlay) {
        return topPlay.getClass().equals(Pair.class);
    }

    @Override
    protected List<Card> doHandling(CardPattern topPlay, Hand hand) {
        List<Card> cards = hand.getCards();
        Pair toplayPair = new Pair(topPlay.getCards());
        for (int i = 0; i < cards.size(); i++) {
            Card card1 = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                Card card2 = cards.get(j);
                List<Card> playCards = new ArrayList<>();
                playCards.add(card1);
                playCards.add(card2);
                Pair handPair = new Pair(playCards);
                if (cardPatternHandler.match(playCards) && handPair.compare(toplayPair)) {
                    return playCards;
                }
            }
        }
        return new ArrayList<>();
    }
}
