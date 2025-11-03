package com.ted.app.players;

import com.ted.app.Hand;
import com.ted.app.cards.Card;
import com.ted.app.games.CardGame;

import java.util.List;

import static com.ted.app.games.CardGame.random;

public class AIPlayer extends Player{
    public AIPlayer(CardGame game) {
        super(game);
    }

    @Override
    public void nameHimsSelf(int index) {
        setName("AI" + (index + 1));
    }


    @Override
    public Card show() {
        List<Card> cards = getCanShowCards();
        int size = cards.size();
        int bound = size - 1;
        int index = bound == 0 ? 0 : random.nextInt(bound);
        Card card = cards.get(index);

        String playerName = getName();
        System.out.println(playerName + " 出了" + card);

        Hand hand = getHand();
        hand.remove(card);

        return card;
    }
}
