package com.TekionCricketFinal.TekionCricketFinal.services;
import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


public class TekionCricket
{
    @Contract(" -> new")
    public static @NotNull Match startMatch(){

        return new Match();
    }
}