package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PairHandler extends CardPatternHandler {

    public PairHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Card> playCards) {
        boolean sizeValid = playCards.size() == 2;
        boolean rankFormatValid = pattenRankFormatValid(playCards);
        return sizeValid && rankFormatValid;
    }

    @Override
    protected Optional<CardPattern> doHandling(List<Card> playCards) {
        return Optional.of(new Pair(playCards));
    }

    private boolean pattenRankFormatValid(List<Card> playCards) {
        Set<String> set = new HashSet<>();
        for (Card card : playCards) {
            set.add(card.getRank().getSymbol());
        }
        //Pair 由1種不同的點數組成
        boolean formatValid = set.size() == 1;
        return formatValid;
    }
}
