package com.ted.app.players;

import com.ted.app.Deck;
import com.ted.app.Hand;
import com.ted.app.cards.Card;
import com.ted.app.games.CardGame;
import com.ted.app.util.ExceptionUtil;

import java.util.List;

public abstract class Player {

    private CardGame game;

    private Hand hand;

    private String name;

    private int point;

    public Player(CardGame game) {
        setGame(game);
        setHand(new Hand());
    }

    public void addPoint(int score) {
        int point = getPoint();
        point += score;
        setPoint(point);
        String playerName = getName();
        System.out.println(playerName + " 增加了" + score + "點，共" + point +"點");
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        Hand hand = getHand();
        hand.add(card);
    }

    public List<Card> getCanShowCards(){
        CardGame game = getGame();
        Hand hand = getHand();
        List<Card> result = hand.getCanShowCards(game);
        return result;
    }

    public boolean isHandEmpty(){
        Hand hand = getHand();
        return hand.isEmpty();
    }

    public abstract void nameHimsSelf(int index);

    public abstract Card show();

    //==========================================================//

    public CardGame getGame() {
        return game;
    }

    public void setGame(CardGame game) {
        this.game = game;
    }

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {

        if(point < 0){
            throw new RuntimeException(ExceptionUtil.getExceptionFormatMsg("分數不可小於0"));
        }

        this.point = point;
    }
}
