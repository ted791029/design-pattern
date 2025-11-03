package ted.com.app.showdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Showdown {
    private Deck deck;
    private List<Player> players;

    private DiscardPile discardPile;

    private final int TOP_CARDS = 4;

    private final int PLAYER_SIZE = 4;

    private final int POINT = 1;

    public Showdown() {
        setDeck(new Deck());
        setPlayers(new ArrayList<>());
        setDiscardPile(new DiscardPile());
    }

    public void start(Scanner scanner) {
        System.out.println("遊戲開始");
        System.out.println("=======================================");
        selectNumberOfPlayers(scanner);
        playerNameHimSelf(scanner);
        shuffle();
        playerDrawCard();
        nextRound(scanner);
        Player winner = findWinner();
        System.out.printf("獲勝玩家為: %S\n", winner.getName());
        System.out.println("遊戲結束");
    }

    public void nextRound(Scanner scanner) {
        if (players.get(0).handIsEmpty()) return;
        show(scanner);
        List<Card> cards = discardPile.getTop(TOP_CARDS);
        Player hightestPlayer = showdown(cards);
        System.out.printf("此局獲勝玩家為: %S\n", hightestPlayer.getName());
        System.out.println("=======================================");
        addPoint(hightestPlayer, POINT);
        nextRound(scanner);
    }

    public void selectNumberOfPlayers(Scanner scanner) {
        System.out.println("請輸入有幾位玩家!");
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

    public void shuffle() {
        deck.shuffle();
    }

    public void playerDrawCard() {
        while (deck.size() > 0) {
            for (int i = 0; i < players.size(); i++) {
                if (deck.size() <= 0) return;
                Player player = players.get(i);
                Card card = deck.drawCard();
                player.addHandCard(card);
            }
        }
    }

    public void show(Scanner scanner) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.printHand();
            discardPile.add(player.show(scanner));
        }
    }

    public Player showdown(List<Card> cards) {
        Card hightestCard = cards.get(0);
        Player winner = players.get(0);
        for (int i = 1; i < cards.size(); i++) {
            Card target = cards.get(i);
            if (target.showdown(hightestCard) > 0) {
                hightestCard = target;
                winner = players.get(i);
            }
        }
        return winner;
    }

    public void addPoint(Player hightestPlayer, int point) {
        hightestPlayer.addPoint(point);
    }

    public Player findWinner() {
        Player winner = players.get(0);
        System.out.printf("%s的分數為: %d\n", winner.getName(), winner.getPoint());
        for (int i = 1; i < players.size(); i++) {
            Player target = players.get(i);
            System.out.printf("%s的分數為: %d\n", target.getName(), target.getPoint());
            winner = winner.findWinner(target);
        }
        return winner;
    }

    private void initPlayers(int number) {
        players = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            players.add(new HumanPlayer());
        }
        for (int i = number; i < PLAYER_SIZE; i++) {
            players.add(new AIPlayer(i + 1));
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
