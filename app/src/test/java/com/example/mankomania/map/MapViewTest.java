package com.example.mankomania.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapViewTest {

    @Test
    public void testgetMoneyFinanzamt() {
        Player player = new Player();
        int x = player.setMoney(100);
        assertEquals(false, x);
    }
}