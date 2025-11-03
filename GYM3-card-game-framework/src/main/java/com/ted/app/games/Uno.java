package com.ted.app.games;

import com.ted.app.Deck;
import com.ted.app.DiscardPile;
import com.ted.app.cards.Card;
import com.ted.app.cards.uno.Color;
import com.ted.app.cards.uno.Number;
import com.ted.app.cards.uno.UnoCard;
import com.ted.app.players.Player;
import com.ted.app.util.ExceptionUtil;

import java.util.List;
import java.util.Objects;

public class Uno extends CardGame{
    public Uno() {
        super(5);
    }

    @Override
    protected void discardPileInit() {
        DiscardPile discardPile = getDiscardPile();

        for(Number number : Number.values()){
            for(Color color : Color.values()){
                UnoCard card = new UnoCard(color, number);
                discardPile.add(card);
            }
        }
    }

    @Override
    public boolean isCardCanShow(Card card){

        Objects.requireNonNull(card, ExceptionUtil.getExceptionFormatMsg("比較卡片不可為空"));

        DiscardPile discardPile = getDiscardPile();

        if(discardPile.size() == 0){
            return true;
        }

        List<Card> discardPileCards = discardPile.getTopCards(1);
        Card lastCard = discardPileCards.get(0);
        return lastCard.isCanShow(card);
    }

    @Override
    protected  boolean isPlayerCanShow(Player player){

        Objects.requireNonNull(player, ExceptionUtil.getExceptionFormatMsg("玩家不可為空"));

        List<Card> cards = player.getCanShowCards();
        return cards.size() > 0;
    }

    @Override
    protected void nextRound() throws GameEndException {
        printLastCard();
        playersShowCards();
        nextRound();
    }

    @Override
    protected void playerNotShowToDo(Player player){

        Objects.requireNonNull(player, ExceptionUtil.getExceptionFormatMsg("玩家不可為空"));

        Deck deck  = getDeck();

        if(deck.isEmpty()){
            System.out.println("重新洗牌");
            deck.shuffle();
        }

        player.drawCard(deck);
    }

    private void printLastCard(){
        DiscardPile discardPile = getDiscardPile();

        if(discardPile.size() == 0){
            return;
        }

        List<Card> discardPileCards = discardPile.getTopCards(1);
        Card lastCard = discardPileCards.get(0);
        System.out.println("棄牌堆最上一層牌為: " + lastCard);
    }

    @Override
    protected void showWinner() {
        Player winner = getPlayerWithEmptyHand();
        System.out.println("贏家是: " + winner.getName());
    }
}
