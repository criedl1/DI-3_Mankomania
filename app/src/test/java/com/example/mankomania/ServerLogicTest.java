package com.example.mankomania;

import com.example.mankomania.dice.ResultSplitter;
import com.example.mankomania.network.logic.ServerLogic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServerLogicTest {
    ServerLogic serverLogic;

    @Before
    public void setUp() {
        serverLogic = new ServerLogic();
    }

    //Single Tests
    @Test
    public void singleThrowDice() {
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void singleDoAction() {
        Assert.assertFalse(serverLogic.doAction(0));
    }
    @Test
    public void singleChangeValue() {
        Assert.assertFalse(serverLogic.changeValue(0));
    }
    @Test
    public void singleEndTurn() {
        Assert.assertFalse(serverLogic.endTurn(0));
    }


    //Base Tests
    @Test
    public void startTurn() {
        Assert.assertTrue(serverLogic.startTurn(0));
    }
    @Test
    public void throwDice() {
        serverLogic.startTurn(0);
        Assert.assertTrue(serverLogic.throwDice(0));
    }
    @Test
    public void doAction() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertTrue(serverLogic.doAction(0));
    }
    @Test
    public void changeValue() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertTrue(serverLogic.changeValue(0));
    }
    @Test
    public void endTurn() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertTrue(serverLogic.endTurn(0));
    }
    @Test
    public void standardTest1() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        Assert.assertTrue(serverLogic.endTurn(0));
    }
    @Test
    public void standardTest2() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        Assert.assertTrue(serverLogic.endTurn(0));
    }
    @Test
    public void standardTest3() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        serverLogic.doAction(0);
        Assert.assertTrue(serverLogic.endTurn(0));
    }
    @Test
    public void standardTest4() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        Assert.assertTrue(serverLogic.endTurn(0));
    }

    //Double Call Fail Tests
    @Test
    public void startTurnDouble() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.startTurn(0));
    }
    @Test
    public void throwDiceDouble() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void endTurnDouble() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.endTurn(0));
    }

    //Fail Throw Tests
    @Test
    public void failThrowDice1() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void failThrowDice2() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void failThrowDice3() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void failThrowDice4() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void failThrowDice5() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }
    @Test
    public void failThrowDice6() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.throwDice(0));
    }

    //Fail DoAction Tests
    @Test
    public void failDoAction1() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.doAction(0));
    }
    @Test
    public void failDoAction2() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.doAction(0));
    }

    //Fail DoAction Tests
    @Test
    public void failChangeValue1() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.changeValue(0));
    }
    @Test
    public void failChangeValue2() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.changeValue(0));
    }
}