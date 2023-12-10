package com.ted.app;

import com.ted.app.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> cards;

    public Hand(){
        cards = new ArrayList<>();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void add(Card card){
        int index = 0;
        for(Card temp : cards){
            if(temp.compare(card)) break;
            index++;
        }
        cards.add(index, card);
    }

    public List<Card> play(List<Integer> indexes){
        List<Card> cards = getCards(indexes);
        remove(cards);
        return cards;
    }

    public void show(){
        for(int i = 0; i < cards.size(); i++){
            System.out.printf("%-10d", i);
        }
        System.out.printf("\n");
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            System.out.printf("%s[%s]   ", card.getSuitSymbol(), card.getRankSymbol());
        }
        System.out.printf("\n");
    }

    private List<Card> getCards(List<Integer> indexes){
        List<Card> cards = new ArrayList<>();
        for(int index : indexes){
            if(index < 0 || index >= cards.size()) throw new RuntimeException("輸入超過手牌範圍");
            cards.add(this.cards.get(index));
        }
        return cards;
    }

    private void remove(List<Card> cards){
        for (Card card : cards){
            this.cards.remove(card);
        }
    }
}
