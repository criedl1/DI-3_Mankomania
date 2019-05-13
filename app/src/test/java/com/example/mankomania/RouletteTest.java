package com.example.mankomania;

import com.example.mankomania.roulette.ColorActivity;
import com.example.mankomania.roulette.ColorEnum;
import com.example.mankomania.roulette.DozenActivity;
import com.example.mankomania.roulette.FieldClass;
import com.example.mankomania.roulette.NumberActivity;
import com.example.mankomania.roulette.RouletteClass;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RouletteTest {

    FieldClass[] fieldClassArray;
    RouletteClass roulette = new RouletteClass();
    NumberActivity na;
    ColorActivity ca;
    DozenActivity da;
    int i;

    @Before
    public void setUp() {
        fieldClassArray = roulette.setUpFields();
        na = new NumberActivity();
        ca = new ColorActivity();
        da = new DozenActivity();
        i = roulette.spinIt();
    }

    @Test
    public void testSetUpRoulette() {
        assertEquals(37, fieldClassArray.length);
    }

    @Test
    public void testFields() {
        assertEquals(ColorEnum.RED, fieldClassArray[1].getColor());
        assertEquals(32, fieldClassArray[1].getValue());
        assertEquals(ColorEnum.BLACK, fieldClassArray[14].getColor());
        assertEquals(11, fieldClassArray[14].getValue());

    }

    //TODO: Ab hier gibt es noch einen kleinen Denkfehler, spinWheel dreht Roulette neu!

    @Test
    public void testNumberActivityWin() {
        na.spinWheel(i);
        assertEquals(145000, na.getMoney());
    }

    @Test
    public void testNumberActivityLost() {
        if(i != 3){
        na.spinWheel(3);}
        else{
            na.spinWheel(4); //checks if randomNumber isn't 3
        }
        assertEquals(- 50000, na.getMoney());
    }

    @Test
    public void testColorActivityWin(){
        ca.spinWheel(fieldClassArray[i].getColor());

        assertEquals(30000, ca.getMoney());
    }

    @Test
    public void testColorActivityLost(){
        if(fieldClassArray[i].getColor()== ColorEnum.BLACK|| fieldClassArray[i].getColor()== ColorEnum.GREEN){
            ca.spinWheel(ColorEnum.RED);
        }
        else{
            ca.spinWheel(ColorEnum.BLACK);
        }
        assertEquals(-5000, ca.getMoney());
    }

    @Test
    public void testDozenActivityWin(){
        int actualDozen;
        if(fieldClassArray[i].getValue() <= 12){
            actualDozen = 1;
        }
        else if(fieldClassArray[i].getValue() <= 24){
            actualDozen = 2;
        }
        else {
            actualDozen = 3;
        }

        da.spinWheel(actualDozen);
        assertEquals(80000, da.getMoney());
    }

    @Test
    public void testDozenActivityLost(){
        int actualDozen;
        int choosenDozen;
        if(fieldClassArray[i].getValue() <= 12){
            actualDozen = 1;
        }
        else if(fieldClassArray[i].getValue() <= 24){
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

        da.spinWheel(choosenDozen);
        assertEquals(- 20000, da.getMoney());
    }
}


