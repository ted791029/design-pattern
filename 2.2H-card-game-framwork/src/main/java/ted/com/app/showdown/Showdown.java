package ted.com.app.showdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Showdown {
    private Deck deck;
    private List<Player> players;

    private DiscardPile discardPile;

    public Showdown(){
        setDeck(new Deck());
        setPlayers(new ArrayList<>());
        setDiscardPile(new DiscardPile());
    }

    public void start() {

    }

    public void nextRound() {

    }

    public void selectNumberOfPlayers(Scanner scanner) {
        System.out.print("請輸入有幾位玩家!");
        int number = Integer.parseInt(scanner.nextLine());
        initPlayers(number);
    }

    public void playerNameHimSelf(Scanner scanner) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player instanceof AIPlayer) break;
            System.out.println("請輸入玩家姓名: ");
            String name = scanner.nextLine();
            player.nameHimsSelf(name);
        }
    }

    public void playerDrawCard() {
        while (deck.getCards().size() > 0) {
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                Card card = deck.drawCard();
                player.addHandCard(card);
            }
        }
    }
    public void show(Scanner scanner){
        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            player.printHand();
            discardPile.add(player.show(scanner));
        }
    }


    private void initPlayers(int number) {
        players = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            players.add(new HumanPlayer());
        }
        for (int i = number; i <= 4; i++) {
            players.add(new AIPlayer());
        }
    }

    /**
     * getter & setter
     **/
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

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }
}
