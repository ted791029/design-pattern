package com.ted.app;

import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.Card.Suit;
import com.ted.app.CardPattern.CardPattern;
import com.ted.app.Player.Player;

import java.util.ArrayList;
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

    public Round(int count, Optional<CardPattern> topPlay, Optional<Player> topPlayer, List<Player> players, PlayRule rule) {
        setCount(count);
        setTopPlay(topPlay);
        setTopPlayer(topPlayer);
        setPlayers(players);
        setRule(rule);
    }

    public void start(Scanner scanner) {
        System.out.printf("新的回合開始了。\n");
        if (count == 1 && topPlayer.isEmpty()) {
            Optional<Player> topPlayer = playerHasClubsThree();
            if (topPlayer.isEmpty()) throw new RuntimeException("查無梅花三玩家。");
            setTopPlayer(topPlayer);
        }
        int index = topPlayer.get().getNo() - 1;
        Optional<Player> winner = Optional.empty();
        while (passCount < 3) {
            if (winner.isPresent()) break;
            Player player = players.get(index);
            playerAction(player, scanner);
            index = (index + 1) % players.size();
            winner = getPlayerWithHandIsEmpty();
        }
    }

    private void showPlayCard(Player player, Optional<CardPattern> cardPatternOp) {
        if (cardPatternOp.isEmpty()) throw new RuntimeException("出牌牌型不該為空");
        CardPattern pattern = cardPatternOp.get();
        List<Card> cards = pattern.getCards();
        String cardStr = "";
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            cardStr += card.getSuitSymbol() + "[" + card.getRankSymbol() + "] ";
        }
        System.out.printf("玩家 %s 打出了 %s %s\n", player.getName(), pattern.getName(), cardStr);
    }

    private void playerAction(Player player, Scanner scanner) {
        System.out.printf("輪到%S了。\n", player.getName());
        List<Card> playCards = new ArrayList<>();
        boolean isAction = false;
        Optional<CardPattern> playPattern = Optional.empty();
        while (!isAction) {
            player.showHand();
            playCards = player.play(scanner, topPlay);
            playPattern = rule.getPlayPatternOp(playCards);
            isAction = playCardValid(playCards, playPattern);
        }
        if (playCards.size() > 0) showPlayCard(player, playPattern);
        updateInfo(player, playCards, playPattern);
    }

    private boolean playCardValid(List<Card> playCards, Optional<CardPattern> playPattern) {
        if (playCards.size() == 0) {
            return passValid();
        } else {
            return playValid(playPattern);
        }
    }

    private Optional<Player> playerHasClubsThree() {
        for (Player player : players) {
            if (player.handHasTheCard(new Card(Rank.THREE, Suit.CLUB))) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    private boolean passValid() {
        if (topPlay.isEmpty()) {
            System.out.printf("你不能在新的回合中喊 PASS\n");
            return false;
        }
        return true;
    }

    private boolean playValid(Optional<CardPattern> playPattern) {
        if (!rule.isValid(playPattern, this)) {
            return false;
        }
        return true;
    }

    private void updateInfo(Player player, List<Card> playCards, Optional<CardPattern> playPattern) {
        if (playCards.size() == 0) {
            System.out.printf("玩家 %s PASS\n", player.getName());
            passCount++;
        } else {
            player.handRemove(playCards);
            topPlay = playPattern;
            topPlayer = Optional.of(player);
            passCount = 0;
        }
    }

    private Optional<Player> getPlayerWithHandIsEmpty() {
        for (Player player : players) {
            if (player.isHandEmpty()) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    /**
     * getter & setter
     **/
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
