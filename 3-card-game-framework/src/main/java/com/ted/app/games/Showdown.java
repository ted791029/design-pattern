package com.ted.app.games;

import com.ted.app.DiscardPile;
import com.ted.app.cards.Card;
import com.ted.app.cards.pocker.PokerCard;
import com.ted.app.cards.pocker.Rank;
import com.ted.app.cards.pocker.Suit;
import com.ted.app.players.Player;
import com.ted.app.util.ExceptionUtil;

import java.util.List;
import java.util.Objects;

public class Showdown extends CardGame{


    public Showdown() {
        super(13);
    }

    private Player cardsShowdown(List<Card> cards){

        Objects.requireNonNull(cards, ExceptionUtil.getExceptionFormatMsg("比較卡片們不可為空"));

        List<Player> players = getPlayers();
        int maxIndex = 0;
        int size = cards.size();
        Card maxCard = cards.get(maxIndex);

        for(int i = 1; i < size; i++){

            Card curCard = cards.get(i);
            if(maxCard.showdown(curCard) > 0){
                continue;
            }else if(maxCard.showdown(curCard) < 0){
                maxCard = curCard;
                maxIndex = i;
            }else {
                throw new RuntimeException("比較出錯");
            }
        }

        Player player = players.get(maxIndex);
        return player;
    }

    @Override
    protected void discardPileInit() {
        DiscardPile discardPile = getDiscardPile();
        for(Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                PokerCard card = new PokerCard(rank, suit);
                discardPile.add(card);
            }
        }
    }

    @Override
    protected void nextRound() throws GameEndException {
        playersShowCards();
        DiscardPile discardPile = getDiscardPile();
        List<Card> cards = discardPile.getTopCards(4);
        Player player = cardsShowdown(cards);
        player.addPoint(1);
        Player noHandCard = getPlayerWithEmptyHand();

        if(noHandCard == null){
            nextRound();
        }
    }

    @Override
    protected void showWinner() {
        List<Player> players = getPlayers();
        Player winner = players.get(0);
        int size = players.size();

        for(int i = 1; i < size; i++){
            Player curPlayer = players.get(i);
            if(winner.getPoint() < curPlayer.getPoint()){
                winner = curPlayer;
            }
        }

        System.out.println("贏家是: " + winner.getName());
    }
}
