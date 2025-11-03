package com.ted.app;

import com.ted.app.games.CardGame;
import com.ted.app.games.Showdown;
import com.ted.app.games.Uno;
import com.ted.app.players.AIPlayer;
import com.ted.app.players.HumanPlayer;
import com.ted.app.players.Player;
import com.ted.app.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CardGame game = null;

        String msg = "請輸入要玩的遊戲，1: 比大小 2: 簡易Uno";
        int gameNumber = ScannerUtil.getInputInteger(msg, 1, 2);

        switch (gameNumber){
            case 1:
                game = new Showdown();
                break;
            case 2:
                game = new Uno();
                break;
        }

        List<Player> players = new ArrayList<>();
        msg = "請輸入要玩的人數，最少1人，最多4人";
        int playerCount = ScannerUtil.getInputInteger(msg, 1, 4);

        for(int i = 0; i < playerCount; i++){
            players.add(new HumanPlayer(game));
        }

        int size = 4 - playerCount;

        for(int i = 0; i < size; i++){
            players.add(new AIPlayer(game));
        }

        game.setPlayers(players);
        game.start();
    }
}
