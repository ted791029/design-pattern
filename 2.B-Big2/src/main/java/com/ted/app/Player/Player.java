package com.ted.app.Player;

import com.ted.app.Card.Card;
import com.ted.app.Deck;
import com.ted.app.Hand;

public abstract class Player {

    private Hand hand;
    private String name;

    public boolean isHandEmpty(){
        return false;
    }

    public void addHand(Card card){

    }

    public Card deal(Deck deck){
        return null;
    }

    public void nameSelf(){

    }

    public void pass(){

    }

    public abstract void play();

    /**getter & setter**/
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
