package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Player;
import com.TekionCricketFinal.TekionCricketFinal.models.Role;

import java.util.Random;

class RunsPerBall{
    public static int getRuns(Player player)
    {
        Random rand = new Random();
        int prob= rand.nextInt(0,101);
        int run=rand.nextInt(-1,7);

        if(player.getRole()== Role.BATSMAN || player.getRole()==Role.WICKET_KEEPER){
            return (prob>55 && run==-1)?0:run;
        }
        else if(player.getRole()==Role.ALL_ROUNDER){
            return (prob>65 && run==-1)?0:run;
        }
        else{
            return (prob>90 && run==-1)?0:run;
        }
    }
//    public static int getRuns(Player player)
//    {
//        Random rand = new Random();
//        if(player.getRole()== Role.BATSMAN || player.getRole()==Role.WICKET_KEEPER){
//            int result = rand.nextInt(-1, 10);
//            return result>6?rand.nextInt(1,2):result;
//        }
//        else if(player.getRole()==Role.ALL_ROUNDER){
//            return rand.nextInt(-1, 7);
//        }
//        else{
//            return rand.nextInt(-1, 3);
//        }
//    }
}
