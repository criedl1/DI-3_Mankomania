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

    //Fail EndTurn Tests
    @Test
    public void failEndTurn1() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.endTurn(0));
    }



    //Wrong player Tests ##############################################
    //Base Tests
    @Test
    public void throwDiceWrongPlayer() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void doActionWrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.doAction(1));
    }
    @Test
    public void changeValueWrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.changeValue(1));
    }
    @Test
    public void endTurnWrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }
    @Test
    public void standardTest1WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }
    @Test
    public void standardTest2WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }
    @Test
    public void standardTest3WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }
    @Test
    public void standardTest4WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }

    //Double Call Fail Tests
    @Test
    public void startTurnDoubleWrongPlayer() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.startTurn(1));
    }
    @Test
    public void throwDiceDoubleWrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void endTurnDoubleWrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }

    //Fail Throw Tests
    @Test
    public void failThrowDice1WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void failThrowDice2WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void failThrowDice3WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void failThrowDice4WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.changeValue(0);
        serverLogic.doAction(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void failThrowDice5WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }
    @Test
    public void failThrowDice6WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.doAction(0);
        serverLogic.changeValue(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.throwDice(1));
    }

    //Fail DoAction Tests
    @Test
    public void failDoAction1WrongPlayer() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.doAction(1));
    }
    @Test
    public void failDoAction2WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.doAction(1));
    }

    //Fail DoAction Tests
    @Test
    public void failChangeValue1WrongPlayer() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.changeValue(1));
    }
    @Test
    public void failChangeValue2WrongPlayer() {
        serverLogic.startTurn(0);
        serverLogic.throwDice(0);
        serverLogic.endTurn(0);
        Assert.assertFalse(serverLogic.changeValue(1));
    }

    //Fail EndTurn Tests
    @Test
    public void failEndTurn1WrongPlayer() {
        serverLogic.startTurn(0);
        Assert.assertFalse(serverLogic.endTurn(1));
    }
}