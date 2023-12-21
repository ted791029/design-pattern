package com.ted.app;

import com.ted.app.Player.AIPlayer;
import com.ted.app.Player.HumanPlayer;
import com.ted.app.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Big2 {
    Deck deck;
    List<Player> players;
    List<Round> rounds;

    private final int PLAYER_SIZE = 4;

    public Big2(){
        rounds = new ArrayList<>();
        deck = new Deck();
    }

    public void start(Scanner scanner){
        System.out.printf("遊戲開始\n");
        System.out.printf("=======================================\n");
        init(scanner);
        Optional<Player> winner = Optional.empty();
        int roundCount = 1;
        PlayRule rule = new PlayRule();
        while (winner.isEmpty()){
            Optional<Player> topPlayer = getLastTopPlayer();
            Round round = new Round(roundCount++, Optional.empty(), topPlayer, players, rule);
            round.start(scanner);
            addRound(round);
            winner = getPlayerWithHandIsEmpty();
        }
        System.out.printf("=======================================\n");
        System.out.printf("遊戲結束，遊戲的勝利者為 %s\n", winner.get().getName());
    }

    private void addRound(Round round){
        rounds.add(round);
    }

    private void deal(){
        int playerIndex = 0;
        while (deck.size() > 0){
            Player player = players.get(playerIndex);
            player.deal(deck);
            playerIndex =  (playerIndex + 1) % players.size();
        }
    }

    private Optional<Player> getLastTopPlayer(){
        if(rounds.size() == 0){
            return Optional.empty();
        }
        Round lastRound = rounds.get(rounds.size() - 1);
        return lastRound.getTopPlayer();
    }

    private Optional<Player> getPlayerWithHandIsEmpty(){
        for(Player player : players){
            if(player.isHandEmpty()){
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    private void init(Scanner scanner){
        selectNumberOfPlayers(scanner);
        playersNameSelf(scanner);
        deck.shuffle();
        deal();
    }

    private void initPlayers(int number) {
        players = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            players.add(new HumanPlayer(i + 1));
        }
        for (int i = number; i < PLAYER_SIZE; i++) {
            players.add(new AIPlayer(i + 1));
        }
    }

    private void playersNameSelf(Scanner scanner){
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player instanceof AIPlayer) break;
            System.out.println("請輸入玩家姓名: ");
            String name = scanner.nextLine();
            player.nameSelf(name);
        }
    }

    private void selectNumberOfPlayers(Scanner scanner) {
        System.out.println("請輸入有幾位玩家!");
        int number = Integer.parseInt(scanner.nextLine());
        initPlayers(number);
    }

    /**getter & setter**/
    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
