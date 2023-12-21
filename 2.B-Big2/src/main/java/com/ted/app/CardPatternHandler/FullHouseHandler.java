package com.ted.app.CardPatternHandler;

import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.CardPattern.FullHouse;

import java.util.*;

public class FullHouseHandler extends CardPatternHandler {

    public FullHouseHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    public boolean match(List<Card> playCards) {
        boolean sizeValid = playCards.size() == 5;
        boolean formatValid = pattenRankFormatValid(playCards);
        return sizeValid && formatValid;
    }

    @Override
    protected Optional<CardPattern> doHandling(List<Card> playCards) {
        return Optional.of(new FullHouse(playCards));
    }

    private boolean pattenRankFormatValid(List<Card> playCards) {
        Map<Rank, List<Card>> map = new HashMap();
        for (Card card : playCards) {
            Rank key = card.getRank();
            List<Card> temp = new ArrayList<>();
            if(map.containsKey(key)){
                temp = map.get(key);
                temp.add(card);
            }else {
                temp.add(card);
                map.put(key, temp);
            }
        }
        int maxCardsSize = 0;
        for(Rank rank : map.keySet()){
            int cardsSize = map.get(rank).size();
            maxCardsSize = Math.max(maxCardsSize, cardsSize);
        }
        //FullHouse 由2種不同的點數組成
        boolean formatValid = map.size() == 2;
        //FullHouse 分別為 3 + 2;
        boolean cardsSizeValid = maxCardsSize == 3;
        return formatValid && cardsSizeValid;
    }
}
