package ru.netology.tournament;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }


    public boolean register(Player player) {
        return players.contains(player);
    }

    public ArrayList<Player> findAll() {
        return players;
    }

    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        if (findByName(playerName1) == null) {
            throw new NotRegisteredException("Player " + playerName1 + " not registered");
        }
        if (findByName(playerName2) == null) {
            throw new NotRegisteredException("Player " + playerName2 + " not registered");
        } else {
            if (findByName(playerName1).getStrength() > findByName(playerName2).getStrength()) {
                return 1;
            }
            if (findByName(playerName1).getStrength() < findByName(playerName2).getStrength()) {
                return 2;
            } else {
                return 0;
            }
        }
    }
}
