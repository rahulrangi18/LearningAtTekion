package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Player;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Role;

import java.util.Random;

class RunsPerBall{
    public static int getRuns(Player player)
    {
        Random rand = new Random();
        if(player.getRole()== Role.BATSMAN || player.getRole()==Role.WICKET_KEEPER){
            int result = rand.nextInt(-1, 10);
            return result>6?rand.nextInt(1,2):result;
        }
        else if(player.getRole()==Role.ALL_ROUNDER){
            return rand.nextInt(-1, 7);
        }
        else{
            return rand.nextInt(-1, 3);
        }
    }
}
