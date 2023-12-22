package com.ted.app;

import com.ted.app.AiPlayHandler.*;
import com.ted.app.Card.Card;
import com.ted.app.Card.Rank;
import com.ted.app.Card.Suit;
import com.ted.app.CardPattern.*;
import com.ted.app.CardPatternHandler.*;
import com.ted.app.Player.HumanPlayer;
import com.ted.app.Player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Scanner scanner;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        scanner = new Scanner(System.in);
    }

    /**EndToEnd**/


    /**
     * Unit
     **/
    @Test
    public void givenHasTwoCardWithDifferentRank_WhenCompare_ThenGetResult() {

        List<Card> cards = givenHasTwoCardWithDifferentRank();

        boolean result = whenCompare(cards.get(0), cards.get(1));

        thenGetTrue(result);
    }

    @Test
    public void givenHasTwoCardWithSameRank_WhenCompare_ThenGetResult() {

        List<Card> cards = givenHasTwoCardWithSameRank();

        boolean result = whenCompare(cards.get(0), cards.get(1));

        thenGetTrue(result);
    }

    @Test
    public void givenHasTwoSameCardPattern_WhenCompare_ThenGetResult() {
        List<CardPattern> patterns;
        boolean result = false;
        //Single
        patterns = givenHasTwoSinglePattern();
        result = whenCompare(patterns.get(0), patterns.get(1));
        thenGetTrue(result);
        //Pair
        patterns = givenHasTwoPairPattern();
        result = whenCompare(patterns.get(0), patterns.get(1));
        thenGetTrue(result);
        //Straight
        patterns = givenHasTwoStraightPattern();
        result = whenCompare(patterns.get(0), patterns.get(1));
        thenGetTrue(result);
        //FullHouse
        patterns = givenHasTwoFullHousePattern();
        result = whenCompare(patterns.get(0), patterns.get(1));
        thenGetTrue(result);
    }

    @Test
    public void givenHasCards_WhenCardPatternHandlerDo_ThenGetCardPattern() {
        CardPatternHandler handler = cardPatternHandlerInit();
        List<Card> cards;
        Optional<CardPattern> patternOp = Optional.empty();
        //Single
        cards = givenHasCards("Single");
        patternOp = whenCardPatternHandle(handler, cards);
        thenGetCardPattern(patternOp, Optional.of(Single.class));
        //Pair
        patternOp = Optional.empty();
        cards = givenHasCards("Pair");
        patternOp = whenCardPatternHandle(handler, cards);
        thenGetCardPattern(patternOp, Optional.of(Pair.class));
        //Straight
        patternOp = Optional.empty();
        cards = givenHasCards("Straight");
        patternOp = whenCardPatternHandle(handler, cards);
        thenGetCardPattern(patternOp, Optional.of(Straight.class));
        //FullHouse
        patternOp = Optional.empty();
        cards = givenHasCards("FullHouse");
        patternOp = whenCardPatternHandle(handler, cards);
        thenGetCardPattern(patternOp, Optional.of(FullHouse.class));
        //Not Pattern
        patternOp = Optional.empty();
        cards = givenHasCards("");
        patternOp = whenCardPatternHandle(handler, cards);
        thenGetCardPattern(patternOp, Optional.empty());
    }

    @Test
    public void givenDeckInOrder_WhenShuffle_ThenGetShuffledDeck() {
        Deck deck = new Deck();
        deck.shuffle();

        thenGetShuffledDeck(deck);
    }

    @Test
    public void givenPlayCards_WhenPassPlayRule_ThenShouldPlaySuccess() {
        List<Card> playCards;
        Optional<CardPattern> topPlay;
        boolean result;
        //First Round
        playCards = givenPlayCards("Three");
        topPlay = Optional.empty();
        result = whenPassPlayRule(playCards, 1, topPlay);
        thenShouldPlaySuccess(result);
        //Other Round
        playCards = givenPlayCards("Pattern");
        List<Card> topPlayCard = new ArrayList<>();
        topPlayCard.add(new Card(Rank.KING, Suit.DIAMOND));
        topPlayCard.add(new Card(Rank.KING, Suit.HEART));
        topPlayCard.add(new Card(Rank.KING, Suit.CLUB));
        topPlayCard.add(new Card(Rank.THREE, Suit.SPADE));
        topPlayCard.add(new Card(Rank.THREE, Suit.DIAMOND));
        topPlay = Optional.of(new FullHouse(topPlayCard));
        result = whenPassPlayRule(playCards, 2, topPlay);
        thenShouldPlaySuccess(result);
    }

    @Test
    public void givenPlayCards_WhenFailedPlayRule_ThenShouldPlayFailed() {
        List<Card> playCards;
        Optional<CardPattern> topPlay;
        List<Card> topPlayCard;
        boolean result;
        //Not Contain Clubs_Three
        playCards = givenPlayCards("Pattern");
        topPlay = Optional.empty();
        result = whenPassPlayRule(playCards, 1, topPlay);
        thenShouldPlayFailed(result);
        //Not bigger than topPlay
        playCards = givenPlayCards("Pattern");
        topPlayCard = new ArrayList<>();
        topPlayCard.add(new Card(Rank.TWO, Suit.DIAMOND));
        topPlayCard.add(new Card(Rank.TWO, Suit.HEART));
        topPlayCard.add(new Card(Rank.TWO, Suit.CLUB));
        topPlayCard.add(new Card(Rank.THREE, Suit.SPADE));
        topPlayCard.add(new Card(Rank.THREE, Suit.DIAMOND));
        topPlay = Optional.of(new FullHouse(topPlayCard));
        result = whenPassPlayRule(playCards, 2, topPlay);
        thenShouldPlayFailed(result);
        // Not valid pattern
        playCards = givenPlayCards("");
        topPlayCard = new ArrayList<>();
        topPlayCard.add(new Card(Rank.TWO, Suit.DIAMOND));
        topPlayCard.add(new Card(Rank.TWO, Suit.HEART));
        result = whenPassPlayRule(playCards, 2, topPlay);
        thenShouldPlayFailed(result);
    }

    @Test
    public void givenPlayerHasHand_WhenPlayCards_ThenGetPlayCards() {
        Hand hand = givenPlayerHasHand();
        Player player = new HumanPlayer(0);
        player.setHand(hand);
        //第一張
        int playIndex1 = 0;
        //最後一張
        int playIndex2 = hand.size() - 1;
        List<Card> playCard = whenPlayCards(player, playIndex1, playIndex2);
        thenGetPlayCards(hand, playCard);
    }

    @Test
    public void givenAIPlayerHasHand_WhenAIPlayerPlay_ThenPlaySuccess() {
        AIPlayHandler handler = playHandlerInit();
        Hand hand = givenAIPlayerHasHand();
        List<Card> cards;
        CardPattern topPlay;
        List<Card> playCards;
        List<Card> targetCards;
        //Single
        cards = givenHasCards("Single");
        topPlay = new Single(cards);
        playCards = whenAIPlayerPlay(handler, topPlay, hand);
        targetCards = getTargetCards("Single");
        thenPlaySuccess(playCards, targetCards);
        //Pair
        cards = givenHasCards("Pair");
        topPlay = new Pair(cards);
        playCards = whenAIPlayerPlay(handler, topPlay, hand);
        targetCards = getTargetCards("Pair");
        thenPlaySuccess(playCards, targetCards);
        //Straight
        cards = givenHasCards("Straight");
        topPlay = new Straight(cards);
        playCards = whenAIPlayerPlay(handler, topPlay, hand);
        targetCards = getTargetCards("Straight");
        thenPlaySuccess(playCards, targetCards);
        //FullHouse
        cards = givenHasCards("FullHouse");
        topPlay = new FullHouse(cards);
        playCards = whenAIPlayerPlay(handler, topPlay, hand);
        targetCards = getTargetCards("FullHouse");
        thenPlaySuccess(playCards, targetCards);
    }


    private List<Card> givenHasTwoCardWithDifferentRank() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        return cards;
    }

    private List<Card> givenHasTwoCardWithSameRank() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.CLUB));
        return cards;
    }

    private List<CardPattern> givenHasTwoSinglePattern() {
        List<CardPattern> patterns = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        patterns.add(new Single(cards));
        cards = new ArrayList<>();
        cards.add(new Card(Rank.THREE, Suit.CLUB));
        patterns.add(new Single(cards));
        return patterns;
    }

    private List<CardPattern> givenHasTwoPairPattern() {
        List<CardPattern> patterns = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.EIGHT, Suit.SPADE));
        cards.add(new Card(Rank.EIGHT, Suit.CLUB));
        patterns.add(new Pair(cards));
        cards = new ArrayList<>();
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.HEART));
        patterns.add(new Pair(cards));
        return patterns;
    }

    private List<CardPattern> givenHasTwoStraightPattern() {
        List<CardPattern> patterns = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.CLUB));
        cards.add(new Card(Rank.FOUR, Suit.HEART));
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cards.add(new Card(Rank.SIX, Suit.CLUB));
        patterns.add(new Straight(cards));
        cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.HEART));
        cards.add(new Card(Rank.JACK, Suit.HEART));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB));
        cards.add(new Card(Rank.KING, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        patterns.add(new Straight(cards));
        return patterns;
    }

    private List<CardPattern> givenHasTwoFullHousePattern() {
        List<CardPattern> patterns = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.JACK, Suit.SPADE));
        cards.add(new Card(Rank.JACK, Suit.CLUB));
        cards.add(new Card(Rank.JACK, Suit.HEART));
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cards.add(new Card(Rank.FIVE, Suit.CLUB));
        patterns.add(new FullHouse(cards));
        cards = new ArrayList<>();
        cards.add(new Card(Rank.SIX, Suit.SPADE));
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.SIX, Suit.CLUB));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        patterns.add(new FullHouse(cards));
        return patterns;
    }

    private Hand givenAIPlayerHasHand() {
        List<Card> cards = new ArrayList<>();
        Hand hand = new Hand();
        //Straight
        cards.add(new Card(Rank.FIVE, Suit.SPADE));
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.SEVEN, Suit.CLUB));
        cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
        cards.add(new Card(Rank.NINE, Suit.SPADE));
        //FullHouse
        cards.add(new Card(Rank.TEN, Suit.HEART));
        cards.add(new Card(Rank.TEN, Suit.SPADE));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
        cards.add(new Card(Rank.QUEEN, Suit.SPADE));
        //Pair
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.SPADE));
        //Single
        cards.add(new Card(Rank.TWO, Suit.SPADE));
        hand.setCards(cards);
        return hand;
    }

    private List<Card> givenHasCards(String type) {
        List<Card> cards = new ArrayList<>();
        switch (type) {
            case ("Single"):
                cards.add(new Card(Rank.TWO, Suit.HEART));
                break;
            case ("Pair"):
                cards.add(new Card(Rank.ACE, Suit.HEART));
                cards.add(new Card(Rank.ACE, Suit.CLUB));
                break;
            case ("Straight"):
                cards.add(new Card(Rank.FIVE, Suit.CLUB));
                cards.add(new Card(Rank.SIX, Suit.SPADE));
                cards.add(new Card(Rank.SEVEN, Suit.DIAMOND));
                cards.add(new Card(Rank.EIGHT, Suit.SPADE));
                cards.add(new Card(Rank.NINE, Suit.HEART));
                break;
            case ("FullHouse"):
                cards.add(new Card(Rank.TEN, Suit.DIAMOND));
                cards.add(new Card(Rank.TEN, Suit.CLUB));
                cards.add(new Card(Rank.JACK, Suit.SPADE));
                cards.add(new Card(Rank.JACK, Suit.DIAMOND));
                cards.add(new Card(Rank.JACK, Suit.HEART));
                break;
            default:
                cards.add(new Card(Rank.TWO, Suit.SPADE));
                cards.add(new Card(Rank.KING, Suit.DIAMOND));
        }
        return cards;
    }

    private List<Card> givenPlayCards(String type) {
        List<Card> playerCards = new ArrayList<>();
        switch (type) {
            case ("Three"):
                playerCards.add(new Card(Rank.THREE, Suit.CLUB));
                playerCards.add(new Card(Rank.THREE, Suit.DIAMOND));
                break;
            case ("Pattern"):
                playerCards.add(new Card(Rank.ACE, Suit.SPADE));
                playerCards.add(new Card(Rank.ACE, Suit.DIAMOND));
                playerCards.add(new Card(Rank.ACE, Suit.CLUB));
                playerCards.add(new Card(Rank.FOUR, Suit.SPADE));
                playerCards.add(new Card(Rank.FOUR, Suit.DIAMOND));
                break;
            default:
                playerCards.add(new Card(Rank.TWO, Suit.SPADE));
                playerCards.add(new Card(Rank.KING, Suit.DIAMOND));
        }
        return playerCards;
    }

    private Hand givenPlayerHasHand() {
        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Rank.FOUR, Suit.SPADE),
                        new Card(Rank.THREE, Suit.DIAMOND),
                        new Card(Rank.TEN, Suit.HEART),
                        new Card(Rank.JACK, Suit.CLUB),
                        new Card(Rank.ACE, Suit.DIAMOND)
                )
        );
        Hand hand = new Hand();
        hand.setCards(cards);
        return hand;
    }

    private boolean whenCompare(Card card1, Card card2) {
        return card1.compare(card2);
    }

    private boolean whenCompare(CardPattern pattern1, CardPattern pattern2) {
        return pattern1.compare(pattern2);
    }

    private Optional<CardPattern> whenCardPatternHandle(CardPatternHandler handler, List<Card> cards) {
        return handler.handle(cards);
    }

    private boolean whenPassPlayRule(List<Card> playCards, int count, Optional<CardPattern> topPlay) {
        PlayRule playRule = new PlayRule();
        List<Player> players = getPlayers("阿一", "阿二", "阿三", "阿四");
        Round round = new Round(count, topPlay, Optional.of(players.get(0)), players, playRule);
        Optional<CardPattern> playPatternOp = playRule.getPlayPatternOp(playCards);
        return playRule.isValid(playPatternOp, round);
    }

    public List<Card> whenPlayCards(Player player, int... cardIndex) {
        String input = toInputString(cardIndex);
        scanner = systemSetIn(input);
        player.showHand();
        return player.play(scanner, Optional.empty());
    }

    public List<Card> whenAIPlayerPlay(AIPlayHandler handler, CardPattern pattern, Hand hand) {
        return handler.handle(pattern, hand);
    }

    private void thenGetTrue(boolean result) {
        assertTrue(result);
    }

    private void thenGetCardPattern(Optional<CardPattern> pattern, Optional<Class<?>> tClass) {
        if (!tClass.isPresent()) {
            assertTrue(pattern.isEmpty());
            return;
        }
        if (!pattern.isPresent()) {
            assertTrue(false);
            return;
        }
        assertTrue(tClass.get().isInstance(pattern.get()));
    }

    private void thenGetShuffledDeck(Deck deck) {
        int count = 0;
        Card card = deck.getCards().get(0);
        Card targetCard = new Card(Rank.ACE, Suit.SPADE);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;

        card = deck.getCards().get(15);
        targetCard = new Card(Rank.THREE, Suit.HEART);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;

        card = deck.getCards().get(25);
        targetCard = new Card(Rank.KING, Suit.HEART);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;

        card = deck.getCards().get(35);
        targetCard = new Card(Rank.TEN, Suit.DIAMOND);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;

        card = deck.getCards().get(45);
        targetCard = new Card(Rank.SIX, Suit.CLUB);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;

        card = deck.getCards().get(51);
        targetCard = new Card(Rank.KING, Suit.CLUB);
        if (card.getRank() == targetCard.getRank() && card.getSuit() == targetCard.getSuit()) count++;
        assertFalse(count == 5);
    }

    private void thenShouldPlaySuccess(boolean result) {
        assertTrue(result);
    }

    private void thenShouldPlayFailed(boolean result) {
        assertFalse(result);
    }

    private void thenGetPlayCards(Hand hand, List<Card> playCards) {
        Card fristCard = playCards.get(0);
        assertEquals(fristCard.getRank(), Rank.FOUR);
        assertEquals(fristCard.getSuit(), Suit.SPADE);
        Card lastCard = playCards.get(1);
        assertEquals(lastCard.getRank(), Rank.ACE);
        assertEquals(lastCard.getSuit(), Suit.DIAMOND);
    }

    private void thenPlaySuccess(List<Card> playCards, List<Card> targetCards) {
        for (int i = 0; i < playCards.size(); i++) {
            assertEquals(playCards.get(i).getRank(), targetCards.get(i).getRank());
            assertEquals(playCards.get(i).getSuit(), targetCards.get(i).getSuit());
        }
    }

    private AIPlayHandler playHandlerInit() {
        return new PlaySingleHandler(
                new PlayPairHandler(
                        new PlayStraightHandler(
                                new PlayFullHouseHandler(
                                        null
                                )
                        )
                )
        );
    }


    private CardPatternHandler cardPatternHandlerInit() {
        return new SingleHandler(
                new PairHandler(
                        new StraightHandler(
                                new FullHouseHandler(
                                        null
                                )
                        )
                )
        );
    }

    private List<Player> getPlayers(String... names) {
        List<Player> players = new ArrayList<>();
        int no = 0;
        for (String name : names) {
            Player player = new HumanPlayer(no++);
            player.setName(name);
            players.add(player);
        }
        return players;
    }

    private List<Card> getTargetCards(String type) {
        List<Card> cards = new ArrayList<>();
        switch (type) {
            case ("Single"):
                cards.add(new Card(Rank.TWO, Suit.SPADE));
                break;
            case ("Pair"):
                cards.add(new Card(Rank.ACE, Suit.DIAMOND));
                cards.add(new Card(Rank.ACE, Suit.SPADE));
                break;
            case ("Straight"):
                cards.add(new Card(Rank.FIVE, Suit.SPADE));
                cards.add(new Card(Rank.SIX, Suit.HEART));
                cards.add(new Card(Rank.SEVEN, Suit.CLUB));
                cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
                cards.add(new Card(Rank.NINE, Suit.SPADE));
                break;
            case ("FullHouse"):
                cards.add(new Card(Rank.TEN, Suit.HEART));
                cards.add(new Card(Rank.TEN, Suit.SPADE));
                cards.add(new Card(Rank.QUEEN, Suit.CLUB));
                cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
                cards.add(new Card(Rank.QUEEN, Suit.SPADE));
                break;
        }
        return cards;
    }

    private String toInputString(String... data) {
        String input = "";
        for (int i = 0; i < data.length; i++) {
            input += data[i];
            if (i < data.length - 1) input += " ";
        }
        return input;
    }

    private String toInputString(int... data) {
        String input = "";
        for (int i = 0; i < data.length; i++) {
            input += data[i];
            if (i < data.length - 1) input += " ";
        }
        return input;
    }

    private Scanner systemSetIn(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        return new Scanner(System.in);
    }

}