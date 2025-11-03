package com.ted.app.Player;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.Deck;
import com.ted.app.Hand;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Player {

    private Hand hand;
    private String name;

    private int no;

    public Player(int no) {
        setNo(no);
        hand = new Hand();
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public void addHand(Card card) {
        hand.add(card);
    }

    public void deal(Deck deck) {
        Card card = deck.deal();
        addHand(card);
    }

    public boolean handHasTheCard(Card card) {
        return hand.hasTheCard(card);
    }

    public List<Card> handPlay(List<Integer> indexes) {
        return hand.play(indexes);
    }

    public void handRemove(List<Card> playCards) {
        hand.remove(playCards);
    }

    public void nameSelf(String name) {
        setName(name);
    }

    public boolean nameValid(String name) {
        Pattern p = Pattern.compile("A-Z+a-z+0-9");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public abstract List<Card> play(Scanner scanner, Optional<CardPattern> topPlay);

    public void showHand() {
        hand.show();
    }


    /**
     * getter & setter
     **/
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
