package com.ted.app;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.Player.Player;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Round {
    private int count;
    private Optional<CardPattern> topPlay;
    private Optional<Player> topPlayer;
    private int passCount = 0;
    private List<Player> players;
    private PlayRule rule;

    public Round(int count, Optional<CardPattern> topPlay, Optional<Player> topPlayer, List<Player> players, PlayRule rule){
        setCount(count);
        setTopPlay(topPlay);
        setTopPlayer(topPlayer);
        setPlayers(players);
        setRule(rule);
    }

    public void start(Scanner scanner){
        System.out.printf("新的回合開始了。\n");
        if(count == 1) setTopPlayer(Optional.of(players.get(0)));
        while (passCount < 3){
            int index = topPlayer.get().getNo() - 1;
            index = (index + 1) % players.size();
            Player player = players.get(index);
            playerAction(player, scanner);
        }
    }

    private void playerAction(Player player, Scanner scanner){
        boolean isAction = false;
        while (!isAction){
            player.showHand();
            List<Card> playCards = player.play(scanner);
            if(playCards.size() == 0){
                isAction = passValid(player);
            }else {
                isAction = playValid(player, playCards);
            }
        }
    }

    private boolean passValid(Player player){
        if(topPlay.isEmpty()){
            System.out.printf("你不能在新的回合中喊 PASS\n");
            return false;
        }
        System.out.printf("玩家 %s PASS\n", player.getName());
        passCount++;
        return true;
    }

    private boolean playValid(Player player, List<Card> playCards){
        Optional<CardPattern> playPatternOp = rule.getPlayPatternOp(playCards);
        if(!rule.isValid(playCards, playPatternOp)){
            System.out.printf("此牌型不合法，請再嘗試一次。\n");
            return false;
        }
        topPlay = playPatternOp;
        topPlayer = Optional.of(player);
        passCount = 0;
        return true;
    }

    /**getter & setter **/
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Optional<CardPattern> getTopPlay() {
        return topPlay;
    }

    public void setTopPlay(Optional<CardPattern> topPlay) {
        this.topPlay = topPlay;
    }

    public Optional<Player> getTopPlayer() {
        return topPlayer;
    }

    public void setTopPlayer(Optional<Player> topPlayer) {
        this.topPlayer = topPlayer;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public PlayRule getRule() {
        return rule;
    }

    public void setRule(PlayRule rule) {
        this.rule = rule;
    }
}
