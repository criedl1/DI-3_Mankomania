package com.example.mankomania;


import android.widget.ImageView;
import android.widget.TextView;
import com.example.mankomania.map.Player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMap {

    @Test
    public void testMoney() {
        Player player = new Player();
        player.initFields(new ImageView(null), new TextView(null));
        player.setMoney(12345);
        assertEquals(12345, player.getMoney());
    }

    @Test
    public void testTemporaryField() {
        Player player = new Player();
        player.setTemporaryField(1);
        assertEquals(1, player.getTemporaryField());
    }

    @Test
    public void testPosition() {
        Player player = new Player();
        player.setPosition(5);
        assertEquals(5, player.getCurrentField());
    }
    


}
