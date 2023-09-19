package ted.com.app.showdown;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    private Showdown game;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        game = new Showdown();
    }

    @AfterEach
    void tearDown() {
        game = null;
        scanner = new Scanner(System.in);
    }


    @Test
    public void givenHasPlayerAandPlayerB_WhenSelectNumberOfPlayersASTwo_ThenHasTwoHumanAndTwoAI() {
        int NUMBER_OF_PLAYERS = this.givenHasPlayers();
        this.whenSelsctNumberOfPlayers(NUMBER_OF_PLAYERS);
        this.thenHasTwoHumanAndTwoAI(game.getPlayers());
    }

    @Test
    public void givenGameHasPlayers_WhenPlayerNameHimSelf_ThenNamedSuccess() {
        String[] names = {"大寶", "二寶", "三寶", "四寶"};
        Player playerA = new HumanPlayer();
        Player playerB = new HumanPlayer();
        Player playerC = new HumanPlayer();
        Player playerD = new HumanPlayer();
        this.givenGameHasPlayers(playerA, playerB, playerC, playerD);
        this.whenPlayerNameHimSelf(names);
        this.thenNamedSuccess(names);

    }

    @Test
    public void givenDeckInOrder_WhenShuffle_ThenGetShuffledDeck() {
        this.giveDeckInOrder();
        this.whenShuffle();
        this.thenGetShuffledDeck(game.getDeck());
    }

    private int givenHasPlayers() {
        int NUMBER_OF_PLAYERS = 2;
        return NUMBER_OF_PLAYERS;
    }

    private void givenGameHasPlayers(Player... players) {
        game.setPlayers(List.of(players));
    }

    private void giveDeckInOrder() {
        game.setDeck(new Deck());
    }

    private void whenSelsctNumberOfPlayers(int numberOfPlayers) {
        String input = this.toInputString(String.valueOf(numberOfPlayers));
        scanner = systemSetIn(input);
        game.selectNumberOfPlayers(scanner);
    }

    private void whenShuffle() {
        game.getDeck().shuffle();
    }

    private void whenPlayerNameHimSelf(String[] names) {
        String input = this.toInputString(names);
        scanner = systemSetIn(input);
        game.playerNameHimSelf(scanner);
    }

    private void thenHasTwoHumanAndTwoAI(List<Player> players) {
        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
        assertTrue(players.get(2) instanceof AIPlayer);
        assertTrue(players.get(2) instanceof AIPlayer);
    }

    private void thenGetShuffledDeck(Deck deck) {
        Card card = deck.getCards().get(0);
        Card targetCard = new Card(Suit.SPADE, Rank.ACE);
        assertTrue(!card.isSame(targetCard));

        card = deck.getCards().get(15);
        targetCard = new Card(Suit.HEART, Rank.THREE);
        assertTrue(!card.isSame(targetCard));

        card = deck.getCards().get(25);
        targetCard = new Card(Suit.HEART, Rank.KING);
        assertTrue(!card.isSame(targetCard));

        card = deck.getCards().get(35);
        targetCard = new Card(Suit.DIAMOND, Rank.TEN);
        assertTrue(!card.isSame(targetCard));

        card = deck.getCards().get(45);
        targetCard = new Card(Suit.CLUB, Rank.SIX);
        assertTrue(!card.isSame(targetCard));

        card = deck.getCards().get(51);
        targetCard = new Card(Suit.CLUB, Rank.KING);
        assertTrue(!card.isSame(targetCard));
    }

    private void thenNamedSuccess(String[] names) {

    }

    private String toInputString(String... data) {
        String input = "";
        for (int i = 0; i < data.length; i++) {
            input += data[i];
            if (i < data.length - 1) input += "\n";
        }
        return input;
    }

    private Scanner systemSetIn(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        return new Scanner(System.in);
    }

}