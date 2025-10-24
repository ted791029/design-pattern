package com.ted.app;

import com.ted.app.cards.Card;
import com.ted.app.games.CardGame;
import com.ted.app.util.ValidUtil;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand(){
        setCards(new ArrayList<>());
    }

    public void add(Card card){
        List<Card> cards = getCards();
        cards.add(card);
    }

    public boolean isEmpty(){
        List<Card> cards = getCards();
        return cards.isEmpty();
    }

    public List<Card> getCanShowCards(CardGame game){
        List<Card> cards = getCards();
        List<Card> result = new ArrayList<>();

        for (Card card : cards){
            if(game.isCardCanShow(card)){
                result.add(card);
            }
        }

        return result;
    }

    public void print() {
        List<Card> cards = getCards();
        int size = cards.size();

        for(int i = 0; i < size; i++){
            Card card = cards.get(i);
            System.out.print(i + ": " + card + " ");
        }
        System.out.println();
    }

    public void remove(Card card){
        List<Card> cards = getCards();
        cards.remove(card);
    }

    public Card show(int index){
        List<Card> cards = getCards();
        int size = size();

        if(!ValidUtil.isInRange(0, size - 1, index)){
            throw new RuntimeException("超出手牌範圍");
        }

        Card card = cards.get(index);
        return card;
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


