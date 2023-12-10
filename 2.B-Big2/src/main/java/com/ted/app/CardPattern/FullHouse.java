package com.ted.app.CardPattern;

import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.CardPatternHandler.CardPatternHandler;
import com.ted.app.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHouse extends CardPattern{
    public FullHouse(List<Card> cards) {
        super(cards);
    }

    @Override
    protected List<Card> getCardsToCompare() {
        List<Card> threeSameCards = getThreeCards();
        return threeSameCards;
    }

    private List<Card> getThreeCards() {
        Map<Rank, List<Card>> map = new HashMap<>();
        for(Card card : super.cards){
            Rank key = card.getRank();
            if(map.containsKey(key)){
                List<Card> temp = map.get(key);
                temp.add(card);
            }else {
                List<Card> temp = new ArrayList<>();
                temp.add(card);
                map.put(key, temp);
            }
        }
        List<Card> result = null;
        for(Rank key : map.keySet()){
            List<Card> temp = map.get(key);
            if(temp.size() == 3) {
                result = temp;
                break;
            }
        }
        return result;
    }
}
