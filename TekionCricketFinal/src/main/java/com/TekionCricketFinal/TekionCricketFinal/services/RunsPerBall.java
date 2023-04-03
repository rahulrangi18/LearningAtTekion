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
        if(run>=4)
        {
            if(run==4 && prob<70){
                run=2;
            }
            else if(run==6 && prob<80){
                run=2;
            }
            else if(run==5 && prob<95){
                //this is only possible when there is some extra runs like overthrow (probility is less for this)
                run=2;
            }
        }

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
}
