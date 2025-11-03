package com.ted.app.players;

import com.ted.app.Hand;
import com.ted.app.cards.Card;
import com.ted.app.games.CardGame;
import com.ted.app.util.ScannerUtil;

public class HumanPlayer extends Player{
    public HumanPlayer(CardGame game) {
        super(game);
    }

    @Override
    public void nameHimsSelf(int index) {
        String msg = "請輸入姓名";
        String inputStr = ScannerUtil.getInputStr(msg);
        setName(inputStr);
    }

    public void printHand() {
        Hand hand = getHand();
        hand.print();
    }

    @Override
    public Card show() {
        CardGame game = getGame();
        Hand hand = getHand();
        Card card;
        String playerName = getName();

        while (true){
            printHand();
            String msg = playerName + " 請選擇要出的牌";
            int index = ScannerUtil.getInputInteger(msg, 0, hand.size() - 1);
            card = hand.show(index);

            if(game.isCardCanShow(card)){
                break;
            }
            System.out.println("出的牌不合規定");
        }

        System.out.println(playerName + " 出了" + card);
        hand.remove(card);
        return card;
    }
}
