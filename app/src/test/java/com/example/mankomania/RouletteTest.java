package com.example.mankomania;

import com.example.mankomania.roulette.ColorEnum;
import com.example.mankomania.roulette.FieldClass;
import com.example.mankomania.roulette.RouletteLogic;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RouletteTest {

    RouletteLogic roulette;
    FieldClass[] fieldClassArray;
    int randomNumber;

    @Before
    public void setUp() {
        roulette = new RouletteLogic();
        fieldClassArray = roulette.getRoulette().getFieldClassArray();
        roulette.spinRoulette();
    }

    @Test
    public void testSetUpRoulette() {
        assertEquals(37, roulette.getRoulette().getFieldArrayLength());
    }

    @Test
    public void testFields() {
        assertEquals(ColorEnum.RED, fieldClassArray[1].getColor());
        assertEquals(32, fieldClassArray[1].getValue());
        assertEquals(ColorEnum.BLACK, fieldClassArray[14].getColor());
        assertEquals(11, fieldClassArray[14].getValue());
    }

    @Test
    public void testNumberWin() {
        roulette.spinRoulette();
        randomNumber = roulette.getRandomNumberFromRoulette();
        roulette.checkNumber(randomNumber);
        assertEquals(145000, roulette.getMoney());
    }

    @Test
    public void testNumberLost() {
        roulette.spinRoulette();
        randomNumber = roulette.getRandomNumberFromRoulette();
        if (randomNumber != 3) {
            roulette.checkNumber(3);
        } else {
            roulette.checkNumber(4);
        }
        assertEquals(-50000, roulette.getMoney());
    }

    @Test
    public void testColorWin(){
        roulette.spinRoulette();
        ColorEnum color = roulette.getColorFromRoulette();
        roulette.checkColor(color);
        assertEquals(30000, roulette.getMoney());
    }

    @Test
    public void testColorLost(){
        roulette.spinRoulette();
        if(roulette.getColorFromRoulette()== ColorEnum.BLACK|| fieldClassArray[randomNumber].getColor()== ColorEnum.GREEN){
            roulette.checkColor(ColorEnum.RED);
        }
        else{
            roulette.checkColor(ColorEnum.BLACK);
        }
        assertEquals(-5000, roulette.getMoney());
    }

    @Test
    public void testDozenWin(){
        int actualDozen;

        if(roulette.getRandomNumberFromRoulette() <= 12){
            actualDozen = 1;
        }
        else if(roulette.getRandomNumberFromRoulette() <= 24){
            actualDozen = 2;
        }
        else {
            actualDozen = 3;
        }

        roulette.checkDozen(actualDozen);
        assertEquals(80000, roulette.getMoney());
    }

    @Test
    public void testDozenLost(){
        int actualDozen;
        int choosenDozen;
        if(roulette.getRandomNumberFromRoulette() <= 12){
            actualDozen = 1;
        }
        else if(roulette.getRandomNumberFromRoulette()<= 24){
            actualDozen = 2;
        }
        else {
            actualDozen = 3;
        }

        if(actualDozen != 3){
            choosenDozen = 3;
        }
        else{
            choosenDozen = 2;
        }

        roulette.checkDozen(choosenDozen);
        assertEquals(- 20000, roulette.getMoney());
    }
}


