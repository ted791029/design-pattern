package com.ted.app;

import com.ted.app.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    List<Card> cards;

    public DiscardPile(){
        setCards(new ArrayList<>());
    }

    public void add(Card card) {
        List<Card> cards = getCards();
        cards.add(card);
    }

    public List<Card> getTopCards(int count) {
        List<Card> result = new ArrayList<>();
        List<Card> cards = getCards();
        int size = size();
        int start = size - count;

        if(size == 0){
            return result;
        }

        for(int i = start; i < size; i++){
            result.add(cards.get(i));
        }

        return result;
    }

    public int size(){
        List<Card> cards = getCards();
        return cards.size();
    }

    //==========================================================//

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }


}
