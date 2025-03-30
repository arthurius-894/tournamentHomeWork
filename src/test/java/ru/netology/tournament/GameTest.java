package ru.netology.tournament;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    Player player1 = new Player(1, "Arthur", 100);
    Player player2 = new Player(2, "Sarakod", 54);
    Player player3 = new Player(3, "Aushton", 54);
    Player player4 = new Player(4, "Aushton Cutcher", 88);

    Game game = new Game();

    @Test
    public void shouldAddItemsToTheArray() {

        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);

        Player[] players = {player1, player2, player3, player4};

        ArrayList<Player> expected = new ArrayList<>(List.of(players));
        ArrayList<Player> actual = game.findAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckThatThePlayerIsRegistered() {
        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);

        boolean expected = true;
        boolean actual = game.register(player1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckThatThePlayerIsNotRegistered() {
        game.add(player1);
        game.add(player2);
        game.add(player3);

        boolean expected = false;
        boolean actual = game.register(player4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindPlayerByName() {
        game.add(player1);
        game.add(player2);
        game.add(player3);

        Player expected = player1;
        Player actual = game.findByName("Arthur");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindPlayerByName() {
        game.add(player1);
        game.add(player2);
        game.add(player3);

        Player actual = game.findByName("Arthuri");

        Assertions.assertEquals(null, actual);
    }

    @Test
    public void shouldThrowNotRegisteredExceptionWithPlayer1() {
        game.add(player1);
        game.add(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Pidr", "Sarakod");
        });
    }

    @Test
    public void shouldThrowNotRegisteredExceptionWithPlayer2() {
        game.add(player1);
        game.add(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Arthur", "Tigr");
        });
    }

    @Test
    public void shouldWinPlayer1() {
        game.add(player1);
        game.add(player2);

        int expected = 1;
        int actual = game.round("Arthur", "Sarakod");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWinPlayer2() {
        game.add(player3);
        game.add(player4);

        int expected = 2;
        int actual = game.round("Aushton", "Aushton Cutcher");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeADraw() {
        game.add(player2);
        game.add(player3);

        int expected = 0;
        int actual = game.round("Aushton", "Sarakod");

        Assertions.assertEquals(expected, actual);
    }
}
