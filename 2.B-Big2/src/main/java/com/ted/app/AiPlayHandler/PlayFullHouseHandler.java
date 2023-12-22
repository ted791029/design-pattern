package com.ted.app.AiPlayHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.FullHouse;
import com.ted.app.CardPatternHandler.FullHouseHandler;
import com.ted.app.Hand;

import java.util.ArrayList;
import java.util.List;

public class PlayFullHouseHandler extends AIPlayHandler {
    public PlayFullHouseHandler(AIPlayHandler next) {
        super(next, new FullHouseHandler(null));
    }

    @Override
    protected boolean match(CardPattern topPlay) {
        return topPlay.getClass().equals(FullHouse.class);
    }

    @Override
    protected List<Card> doHandling(CardPattern topPlay, Hand hand) {
        List<Card> cards = hand.getCards();
        FullHouse toplayFullHouse = new FullHouse(topPlay.getCards());
        for (int i = 0; i < cards.size(); i++) {
            Card card1 = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                Card card2 = cards.get(j);
                for (int k = j + 1; k < cards.size(); k++) {
                    Card card3 = cards.get(k);
                    for (int l = k + 1; l < cards.size(); l++) {
                        Card card4 = cards.get(l);
                        for (int m = l + 1; m < cards.size(); m++) {
                            Card card5 = cards.get(m);
                            List<Card> playCards = new ArrayList<>();
                            playCards.add(card1);
                            playCards.add(card2);
                            playCards.add(card3);
                            playCards.add(card4);
                            playCards.add(card5);
                            FullHouse handFullHouse = new FullHouse(playCards);
                            if (cardPatternHandler.match(playCards) && handFullHouse.compare(toplayFullHouse)) {
                                return playCards;
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
