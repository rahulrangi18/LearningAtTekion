package com.TekionCricketWithDatabase.TekionCricketWithDatabase.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Scanner;

@ToString
@Getter
@Setter
public
class Team {
    private String name;

    private int PlayerInTeam;

    public int getPlayerInTeam() {
        return PlayerInTeam;
    }

    public void setPlayerInTeam(int playerInTeam) {
        this.PlayerInTeam = playerInTeam;
    }


    private ArrayList<Player> players;
    private TeamStatus status;
    private int runs = 0;
    private int wickets = 0;
    private int balls = 0;

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public Team() {
        // create random team name and 11 random players and add them to the team
        Scanner input = new Scanner(System.in);
        System.out.println("========================================");
        System.out.print("Enter Your Team Name: ");
        this.name = input.next();
        System.out.print("Enter No. of Players in " + this.name + " ̱̱Team (suggested: 11): ");
        int n = input.nextInt();
        setPlayerInTeam(n);
        System.out.println(this.name + " is Playing with " + getPlayerInTeam() + " Players\n");
        this.players = new ArrayList<Player>();
        float b = ((float) 40 / 100) * n;
        float c = ((float) 60 / 100) * n;
        for (int i = 1; i <= getPlayerInTeam(); i++) {
            System.out.print("Enter Team " + this.name + " Player " + i + " Name: ");
            String nameTmp = input.next();
            Role role;
            if (i == 1)
                role = Role.WICKET_KEEPER;
            else if (i < (int) b)
                role = Role.BATSMAN;
            else if (i < (int) c)
                role = Role.ALL_ROUNDER;
            else
                role = Role.BOWLER;
            Player player = new Player(nameTmp, role);
            this.players.add(player);
        }
    }
}
