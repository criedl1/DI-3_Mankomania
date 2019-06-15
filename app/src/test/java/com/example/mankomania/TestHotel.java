package com.example.mankomania;

import com.example.mankomania.map.Player;
import com.example.mankomania.map.hotels.Hotel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestHotel {
    @Parameterized.Parameters
    public static Iterable data() {
        return Arrays.asList(new Object[][]{
                {new Player(), 0, new Player(), 1}
        });
    }

    private Player p1;
    private Player p2;
    private int player1ID;
    private int player2ID;
    private Hotel hotel;

    public TestHotel(Player p1, int p1ID, Player p2, int p2ID) {
        this.p1 = p1;
        this.player1ID = p1ID;
        this.p2 = p2;
        this.player2ID = p2ID;

    }

    @Before
    public void setUp() {
        this.hotel = new Hotel(1, "SOME_HOTEL");
    }

    @After
    public void tearDown() {
        this.hotel = null;
    }

    @Test
    public void testSoldNotSold() {
        Assert.assertFalse(hotel.isSold());
    }

    @Test
    public void testSoldSold() {
        hotel.setOwner(p1);
        assertTrue(hotel.isSold());
    }

    @Test
    public void testSetOwnerNoOwner() {
        assertTrue(hotel.setOwner(p1));
    }

    @Test
    public void testSetOwnerHasOwner() {
        hotel.setOwner(p1);
        assertFalse(hotel.setOwner(p2));
    }

    @Test
    public void testHotelName() {
        assertEquals("SOME_HOTEL", hotel.getHotelName());
    }

    @Test
    public void testGetOwner() {
        hotel.setOwner(p2);
        assertEquals(p2, hotel.getOwner());
    }




}
