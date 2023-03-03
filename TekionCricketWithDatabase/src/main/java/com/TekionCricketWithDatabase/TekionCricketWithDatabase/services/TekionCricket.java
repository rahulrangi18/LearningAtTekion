package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


public class TekionCricket
{
    @Contract(" -> new")
    public static @NotNull Match startMatch(){

        return new Match();
    }
}