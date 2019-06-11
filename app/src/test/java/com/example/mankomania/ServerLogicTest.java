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
}