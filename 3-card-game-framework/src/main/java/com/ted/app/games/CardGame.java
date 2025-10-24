package com.ted.app.games;


import com.ted.app.Deck;
import com.ted.app.DiscardPile;
import com.ted.app.cards.Card;
import com.ted.app.players.Player;

import java.util.List;
import java.util.Random;

public abstract class CardGame {

    public CardGame(int firstHandSize) {
        DiscardPile discardPile = new DiscardPile();
        Deck deck = new Deck(discardPile);
        setDeck(deck);
        setDiscardPile(discardPile);
        setFirstHandSize(firstHandSize);
    }

    private Deck deck;

    private DiscardPile discardPile;

    private int firstHandSize;

    private List<Player> players;

    public static final Random random = new Random();


    protected abstract void discardPileInit();

    //@Nullable
    protected Player getPlayerWithEmptyHand(){

        List<Player> players = getPlayers();

        for(Player player : players){
            boolean isHandEmpty = player.isHandEmpty();

            if(isHandEmpty){
                return player;
            }
        }

        return null;
    }

    public boolean isCardCanShow(Card card){
        return true;
    }

    protected  boolean isPlayerCanShow(Player player){
        return true;
    }

    protected void playerNotShowToDo(Player player){

    }

    private void playersDrawCards(){
        List<Player> players = getPlayers();
        Deck deck = getDeck();
        int firstHandSize = getFirstHandSize();

        for(int i = 0; i < firstHandSize; i++){
            for(Player player : players){
                player.drawCard(deck);
            }
        }
    }

    private void playersNameHimSelf(){
        List<Player> players = getPlayers();
        int size = players.size();

        for(int i = 0; i < size; i++){
            Player player = players.get(i);
            player.nameHimsSelf(i);
        }
    }

    protected void playersShowCards() throws GameEndException {
        List<Player> players = getPlayers();
        DiscardPile discardPile = getDiscardPile();

        for(Player player : players){

            if(isPlayerCanShow(player)){
                Card card = player.show();

                if(player.isHandEmpty()){
                    throw new GameEndException();
                }

                discardPile.add(card);
            }else{
                String playerName = player.getName();
                System.out.println(playerName + "無牌可出");
                playerNotShowToDo(player);
            }
        }
    }

    protected abstract void nextRound() throws GameEndException;

    protected abstract void showWinner();

    public void start(){
        discardPileInit();
        playersNameHimSelf();
        Deck deck = getDeck();
        deck.shuffle();
        playersDrawCards();

        //遊戲結束會丟例外
        try {
            nextRound();
        }catch (GameEndException e){
            e.toString();
        }

        showWinner();
    }

    //==========================================================//

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }

    public int getFirstHandSize() {
        return firstHandSize;
    }

    public void setFirstHandSize(int firstHandSize) {
        this.firstHandSize = firstHandSize;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static class GameEndException extends Exception{};
}
