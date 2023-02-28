package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


public class TekionCricket
{
    @Contract(" -> new")
    public static @NotNull Match startMatch(){

        return new Match();
    }
}