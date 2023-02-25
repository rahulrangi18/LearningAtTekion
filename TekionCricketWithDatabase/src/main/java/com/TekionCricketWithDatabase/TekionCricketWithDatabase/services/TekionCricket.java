package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Player;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Role;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.services.Match;

import java.util.Random;

public class CricketMain
{
    public static Match startMatch(){
        return new Match();
    }
}