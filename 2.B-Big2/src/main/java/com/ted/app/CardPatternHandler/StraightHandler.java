package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.Straight;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StraightHandler extends CardPatternHandler{

    public StraightHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    public boolean match(List<Card> playCards) {
        boolean sizeValid = playCards.size() == 5;
        boolean rankFormatValid = pattenRankFormatValid(playCards);
        return sizeValid && rankFormatValid;
    }

    @Override
    protected Optional<CardPattern> doHandling(List<Card> playCards) {
        return Optional.of(new Straight(playCards));
    }

    private boolean pattenRankFormatValid(List<Card> playCards) {
        Set<String> set = new HashSet<>();
        //最大為13
        int minRankScore = 14;
        int maxRankScore  = 0;
        for (Card card : playCards) {
            minRankScore = Math.min(minRankScore, card.getRankScore());
            maxRankScore = Math.max(maxRankScore, card.getRankScore());
            set.add(card.getRank().getSymbol());
        }
        //Straight 由5種不同的點數組成
        boolean formatValid = set.size() == 5;
        //Straight 範圍誤差為5
        boolean scoreValid = (maxRankScore - minRankScore) + 1 == 5;
        return formatValid && scoreValid;
    }
}
