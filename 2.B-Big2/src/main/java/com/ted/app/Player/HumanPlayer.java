package com.ted.app.Player;

import com.ted.app.Card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(int no) {
        super(no);
    }

    @Override
    public List<Card> play(Scanner scanner) {
        String line = scanner.nextLine();
        List<Integer> indexes = getIndexes(line);
        return super.handPlay(indexes);
    }

    private List<Integer> getIndexes(String line){
        String[] arr = line.split(" ");
        List<Integer> indexes = new ArrayList<>();
        for(String str : arr){
            indexes.add(Integer.valueOf(str));
        }
        return indexes;
    }
}
