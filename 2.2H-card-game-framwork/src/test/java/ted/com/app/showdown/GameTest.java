package ted.com.app.showdown;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

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
        int NUMBER_OF_PLAYERS = givenHasPlayers();

        whenSelsctNumberOfPlayers(NUMBER_OF_PLAYERS);

        thenHasTwoHumanAndTwoAI(game.getPlayers());
    }

    @Test
    public void givenGameHasPlayers_WhenPlayerNameHimSelf_ThenNamedSuccess() {
        Player playerA = new HumanPlayer();
        Player playerB = new HumanPlayer();
        Player playerC = new HumanPlayer();
        Player playerD = new HumanPlayer();
        givenGameHasPlayers(playerA, playerB, playerC, playerD);

        String[] names = {"大寶", "二寶", "三寶", "四寶"};
        whenPlayerNameHimSelf(names);

        thenNamedSuccess(names);

    }

    @Test
    public void givenDeckInOrder_WhenShuffle_ThenGetShuffledDeck() {
        giveDeckInOrder();

        whenShuffle();

        thenGetShuffledDeck(game.getDeck());
    }

    @Test
    public void givenGameHasPlayersAndShuffledDeck_WhenPlayerDrawCard_ThenPlayerHasHandAndDeckIsEmpty() {
        givenGameHasPlayersAndShuffledDeck();

        whenPlayerDrawCard();

        int HAND_SIZE = 13;
        int DECK_IS_EMPTY = 0;
        thenPlayerHasHandAndDeckIsEmpty(HAND_SIZE, DECK_IS_EMPTY);

    }

    @Test
    public void givenPlayerHasHand_WhenPlayerShowCard_ThenTableHasFourCardAndHandIsDecreasing() {
        givenPlayerHasHand();
        int[] indexArr = {2, 9, 12, 8};
        whenPlayerShowCard(indexArr);
        int DISCARD_PILE_SIZE = 4;
        int HAND_SIZE = 12;
        thenDiscardPileHasFourCardAndHandIsDecreasing(DISCARD_PILE_SIZE, HAND_SIZE);

    }

    private int givenHasPlayers() {
        int NUMBER_OF_PLAYERS = 2;
        return NUMBER_OF_PLAYERS;
    }

    private void givenGameHasPlayers(Player... player) {
        game.setPlayers(new ArrayList<>(List.of(player)));
    }

    private void giveDeckInOrder() {
        game.setDeck(new Deck());
    }

    private void givenGameHasPlayersAndShuffledDeck() {
        Player playerA = new HumanPlayer();
        Player playerB = new HumanPlayer();
        Player playerC = new HumanPlayer();
        Player playerD = new HumanPlayer();
        givenGameHasPlayers(playerA, playerB, playerC, playerD);
        giveDeckInOrder();
        whenShuffle();
    }

    private void givenPlayerHasHand() {
        Player playerA = new HumanPlayer("大寶");
        Player playerB = new HumanPlayer("二寶");
        Player playerC = new HumanPlayer("三寶");
        Player playerD = new HumanPlayer("四寶");
        givenGameHasPlayers(playerA, playerB, playerC, playerD);
        initPlayersHand();
    }

    private void whenSelsctNumberOfPlayers(int numberOfPlayers) {
        String input = toInputString(String.valueOf(numberOfPlayers));
        scanner = systemSetIn(input);
        game.selectNumberOfPlayers(scanner);
    }

    private void whenShuffle() {
        game.getDeck().shuffle();
    }

    private void whenPlayerNameHimSelf(String[] names) {
        String input = toInputString(names);
        scanner = systemSetIn(input);
        game.playerNameHimSelf(scanner);
    }

    private void whenPlayerDrawCard() {
        game.playerDrawCard();
    }

    private void whenPlayerShowCard(int[] indexArr) {
        String input = toInputString(indexArr);
        scanner = systemSetIn(input);
        game.show(scanner);
    }

    private void thenHasTwoHumanAndTwoAI(List<Player> players) {
        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
        assertTrue(players.get(2) instanceof AIPlayer);
        assertTrue(players.get(2) instanceof AIPlayer);
    }

    private void thenNamedSuccess(String[] names) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            String name = names[i];
            assertEquals(name, player.getName());
        }
    }

    private void thenGetShuffledDeck(Deck deck) {
        int count = 0;
        Card card = deck.getCards().get(0);
        Card targetCard = new Card(Suit.SPADE, Rank.ACE);
        if (card.isSame(targetCard)) count++;

        card = deck.getCards().get(15);
        targetCard = new Card(Suit.HEART, Rank.THREE);
        if (card.isSame(targetCard)) count++;

        card = deck.getCards().get(25);
        targetCard = new Card(Suit.HEART, Rank.KING);
        if (card.isSame(targetCard)) count++;

        card = deck.getCards().get(35);
        targetCard = new Card(Suit.DIAMOND, Rank.TEN);
        if (card.isSame(targetCard)) count++;

        card = deck.getCards().get(45);
        targetCard = new Card(Suit.CLUB, Rank.SIX);
        if (card.isSame(targetCard)) count++;

        card = deck.getCards().get(51);
        targetCard = new Card(Suit.CLUB, Rank.KING);
        if (card.isSame(targetCard)) count++;
        assertFalse(count == 5);
    }

    private void thenPlayerHasHandAndDeckIsEmpty(int handSize, int deckSize) {
        //牌堆為空
        assertEquals(game.getDeck().size(), deckSize);
        //手牌為13張
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            assertEquals(player.getHand().size(), handSize);
        }
    }

    private void thenDiscardPileHasFourCardAndHandIsDecreasing(int discardPileSize, int handSize) {
        assertEquals(game.getDiscardPile().size(), discardPileSize);

        assertEquals(game.getDiscardPile().getCards().get(0).toString(), new Card(Suit.SPADE, Rank.JACK).toString());
        assertEquals(game.getDiscardPile().getCards().get(1).toString(), new Card(Suit.DIAMOND, Rank.FOUR).toString());
        assertEquals(game.getDiscardPile().getCards().get(2).toString(), new Card(Suit.CLUB, Rank.QUEEN).toString());
        assertEquals(game.getDiscardPile().getCards().get(3).toString(), new Card(Suit.HEART, Rank.EIGHT).toString());

        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            assertEquals(player.getHand().size(), handSize);
        }
    }

    private String toInputString(String... data) {
        String input = "";
        for (int i = 0; i < data.length; i++) {
            input += data[i];
            if (i < data.length - 1) input += "\n";
        }
        return input;
    }

    private String toInputString(int... data) {
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

    private void initPlayersHand() {
        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.SPADE, Rank.JACK),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.HEART, Rank.FIVE),
                        new Card(Suit.SPADE, Rank.EIGHT),
                        new Card(Suit.SPADE, Rank.TWO),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.SPADE, Rank.SIX),
                        new Card(Suit.CLUB, Rank.EIGHT),
                        new Card(Suit.CLUB, Rank.SIX),
                        new Card(Suit.DIAMOND, Rank.QUEEN)
                )
        );
        Hand hand = new Hand();
        hand.setCards(cards);
        game.getPlayers().get(0).setHand(hand);

        cards = new ArrayList<>(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.SPADE, Rank.FIVE),
                        new Card(Suit.DIAMOND, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.NINE),
                        new Card(Suit.SPADE, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.FIVE),
                        new Card(Suit.SPADE, Rank.KING),
                        new Card(Suit.HEART, Rank.QUEEN),
                        new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.DIAMOND, Rank.FOUR),
                        new Card(Suit.DIAMOND, Rank.THREE),
                        new Card(Suit.SPADE, Rank.THREE),
                        new Card(Suit.CLUB, Rank.THREE)
                )
        );
        hand = new Hand();
        hand.setCards(cards);
        game.getPlayers().get(1).setHand(hand);

        cards = new ArrayList<>(
                List.of(
                        new Card(Suit.DIAMOND, Rank.SIX),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.SPADE, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.CLUB, Rank.TWO),
                        new Card(Suit.HEART, Rank.SIX),
                        new Card(Suit.SPADE, Rank.TEN),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.JACK),
                        new Card(Suit.SPADE, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.KING),
                        new Card(Suit.CLUB, Rank.QUEEN)
                )
        );
        hand = new Hand();
        hand.setCards(cards);
        game.getPlayers().get(2).setHand(hand);

        cards = new ArrayList<>(
                List.of(
                        new Card(Suit.HEART, Rank.TWO),
                        new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.SPADE, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        new Card(Suit.HEART, Rank.SEVEN),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        new Card(Suit.DIAMOND, Rank.FIVE),
                        new Card(Suit.DIAMOND, Rank.SIX),
                        new Card(Suit.HEART, Rank.EIGHT),
                        new Card(Suit.HEART, Rank.THREE),
                        new Card(Suit.HEART, Rank.FOUR),
                        new Card(Suit.HEART, Rank.KING),
                        new Card(Suit.SPADE, Rank.QUEEN)
                )
        );
        hand = new Hand();
        hand.setCards(cards);
        game.getPlayers().get(3).setHand(hand);
    }

}