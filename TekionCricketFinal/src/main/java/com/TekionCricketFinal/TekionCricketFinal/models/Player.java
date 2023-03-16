package com.TekionCricketFinal.TekionCricketFinal.models;
import lombok.Data;

@Data
public class Player {
    private String playerName;
    private Role role;
    private int runs = 0;
    private int wickets = 0;
    private int balls = 0;

    public Player(String playerName, Role role) {
        this.playerName = playerName;
        this.role = role;
    }
}
