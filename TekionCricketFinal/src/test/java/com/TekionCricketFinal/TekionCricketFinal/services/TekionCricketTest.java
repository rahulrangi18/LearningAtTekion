package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TekionCricketTest {

    @Test
    public void testStartMatch() {
        Match match = TekionCricket.startMatch();
        assertNotNull(match, "Match should not be null");
    }
}
