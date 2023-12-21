package com.ted.app.Player;

import com.ted.app.Card.Card;
import com.ted.app.CardPattern.CardPattern;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AIPlayer extends Player{
    public AIPlayer(int no) {
        super(no);
    }

    @Override
    public List<Card> play(Scanner scanner, Optional<CardPattern> topPlay) {
        if(topPlay.isEmpty()){

        }else {

        }
        return null;
    }
}
