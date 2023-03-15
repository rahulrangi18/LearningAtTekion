package com.TekionCricketFinal.TekionCricketFinal.models;

public enum TeamStatus {
    BATTING, BOWLING, WON, LOST, DRAW;
    public static TeamStatus[] allValues() {
        return values();
    }
}
